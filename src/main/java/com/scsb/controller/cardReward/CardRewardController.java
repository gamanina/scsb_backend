package com.scsb.controller.cardReward;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsb.config.Constants;
import com.scsb.config.DataOption;
import com.scsb.config.MessageConstants;
import com.scsb.model.Ldap;
import com.scsb.model.Manager;
import com.scsb.model.Sheet;
import com.scsb.model.SheetApproval;
import com.scsb.service.CheckRightService;
import com.scsb.service.CommonService;
import com.scsb.service.SendEmailService;
import com.scsb.service.SheetApprovalService;
import com.scsb.service.SheetService;
import com.scsb.util.LogUtil;

@Controller
@RequestMapping("/card/reward")
public class CardRewardController {
	
	@Autowired
    private CommonService commonService;
	@Autowired
	private SheetService sheetService;
	@Autowired
	private SheetApprovalService sheetApprovalService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private CardRewardValidator formValidator;
	
	@Autowired
	private DataOption dataOption;
	
	// TODO 刪除
	@Value("${is.test}")
	private boolean isTest;
	
	protected final String rightsString = Constants.PAGE_KEY_CARD_REWARD;
	protected final String sheetType = Constants.CARD_REWARD_SHEET_TYPE;
	protected final String reFlieName = "cardReward";
	protected final String viewAddUrl = "views/card/reward/add";

	private void setCommon(Model model, HttpServletRequest request, CardRewardForm form) throws Exception
	{
		// TODO 刪除
		if(isTest)
		{
	        Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
	        form.setApplicantId(manager.getLdap().getCn());
	        form.setApplicant(manager.getLdap().getGivenName());
	        form.setApplicantUnitId(manager.getLdap().getDepartmentNumber());
	        form.setApplicantUnit(manager.getLdap().getDepartmentNumberName());
	        form.setType(Constants.CARD_REWARD_SHEET_TYPE);// 加入表單類別，用以驗證預覽時是否填寫類別
	        form.setOnTimeDate("2021/01/01");
	        form.setOffTimeDate("2021/12/30");
	        
	        List<Ldap> approverList = (List<Ldap>) request.getSession().getAttribute(Constants.SESSION_APPROVERS);

	        model.addAttribute("approverList", approverList);
	        
			// 取得類別清單
			Map<String, String> categoryMap = dataOption.getSheetCategoryByIndex(DataOption.INDEX_CARD_REWARD_CATEGORYS);
			model.addAttribute("categoryMap", categoryMap);
		}
		else
		{
			// TODO LADP取出申請人名稱、單位，以及該部門所有人
			Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
			Ldap ldap = manager.getLdap();
			form.setApplicantId(ldap.getCn());
			form.setApplicant(ldap.getGivenName());
			form.setApplicantUnitId(manager.getLdap().getDepartmentNumber());
	        form.setApplicantUnit(manager.getLdap().getDepartmentNumberName());
			
			List<Ldap> approverList = (List<Ldap>) request.getSession().getAttribute(Constants.SESSION_APPROVERS);
			List<Ldap> prList = (List<Ldap>) request.getSession().getAttribute(Constants.SESSION_PUBLIC_RELATIONS);
			if (approverList != null)
			{
				model.addAttribute("approverList", approverList);
			}
			else
			{
				throw new Exception();
			}
			
		}
		/** 當前頁TASK ID **/
		model.addAttribute("taskId", rightsString);
	}
	
	@RequestMapping("/add")
	public String add(Model model, HttpServletRequest request, @ModelAttribute CardRewardForm form) 
	{
		try 
		{
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask) 
			{
				Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
				LogUtil.setActionLog("hasTask error: ", "rights: " + reFlieName + " manager: " + manager.getId());
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			setCommon(model, request, form);
		} 
		catch (Exception e) 
		{			
			// TODO 錯誤時應改倒轉至其他頁面
			LogUtil.setErrorLog(reFlieName + " add", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
		}
		
		return viewAddUrl;
	}
	
	@RequestMapping("/save")
	public String save(Model model, HttpServletRequest request, @ModelAttribute CardRewardForm form, BindingResult bindingResult) 
	{
		try 
		{
			Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask)
			{
				LogUtil.setActionLog("hasTask error: ", "rights: " + reFlieName + " manager: " + manager.getId());
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			
			setCommon(model, request, form);
			formValidator.validate(form, bindingResult);
			if (bindingResult.hasErrors()) 
			{
				return viewAddUrl;
			}
			
			// 創建表單
			Sheet sheet = new Sheet();
			BeanUtils.copyProperties(form, sheet);
			
			// 表單資料
			sheet.setType(sheetType);
			sheet.setStatus(Constants.SHEET_STATUS_PROCESSING);
			sheet.setApplicantUnitId(form.getApplicantUnitId());
			sheet.setApplicantUnit(form.getApplicantUnit());
			sheet.setStep(Constants.STEP_1);
			sheet.setCreator(form.getApplicantId());
			sheet.setModifier(form.getApplicantId());
			// 時間處理
			SimpleDateFormat dateParser = new SimpleDateFormat(Constants.TIME_FORMAT_YYYYMMDD_HHMMSS);
			String onTimeStr = form.getOnTimeDate() + " " +form.getOnTimeHour() + ":" + form.getOnTimeMin() + ":00";
			Date onDate = dateParser.parse(onTimeStr);
		    Timestamp onTimestamp = new Timestamp(onDate.getTime());
		    String offTimeStr = form.getOffTimeDate() + " " +form.getOffTimeHour() + ":" + form.getOffTimeMin() + ":00";
			Date offDate = dateParser.parse(offTimeStr);
		    Timestamp offTimestamp = new Timestamp(offDate.getTime());
		    sheet.setOnTime(onTimestamp);
		    sheet.setOffTime(offTimestamp);
		    
		    Sheet resultSheet = sheetService.save(sheet);
		    LogUtil.setActionLog(reFlieName + " save: ", "Sheet: " + resultSheet.getId() + " manager: " + manager.getId());

		    // SheetApproval Model Save
		    if (resultSheet.getId() > 0)
		    {
		    	SheetApproval sheetApproval = new SheetApproval();
			    sheetApproval.setScsbSheetId(resultSheet.getId());
			    sheetApproval.setApproverId(resultSheet.getApplicantId());
			    sheetApproval.setApprover(resultSheet.getApplicant());
			    sheetApproval.setSort(Constants.SORT_0);
			    sheetApproval.setCreator(resultSheet.getApplicantId());
			    sheetApproval.setModifier(resultSheet.getApplicantId());
			    sheetApproval.setStatus(Constants.SHEET_APPROVAL_APPLICATION);
			    sheetApprovalService.save(sheetApproval);
			    LogUtil.setActionLog(reFlieName + " save: ", "SheetApproval: " + sheetApproval.getId() + " manager: " + manager.getId());
		    }
		    
		    try {
		    	sendEmailService.sendSheetApproveEmail(request, resultSheet.getNextApproverId(), resultSheet.getAgentId());
			} catch (Exception e) {
				LogUtil.setErrorLog(reFlieName + " sendSheetApproveEmail", e);
			}
		    
			return commonService.alertPageSetUp(model, Constants.RESULT_SUCCESS, MessageConstants.MESSAGE_INSERT_SUCCESS, Constants.RECORDSHEET_URL);
		}
		catch (Exception e) 
		{
			// TODO 錯誤時應改倒轉至其他頁面
			LogUtil.setErrorLog(reFlieName + " save", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.LOGIN_URL);
		}
	}
}