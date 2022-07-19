package com.scsb.controller.login;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scsb.config.Constants;
import com.scsb.config.MessageConstants;
import com.scsb.model.Ldap;
import com.scsb.model.Manager;
import com.scsb.model.ManagerRole;
import com.scsb.model.ManagerTask;
import com.scsb.model.SecurityEquals;
import com.scsb.model.Sheet;
import com.scsb.service.CommonService;
import com.scsb.service.LdapService;
import com.scsb.service.ManagerRoleService;
import com.scsb.service.SheetService;
import com.scsb.service.TaskService;
import com.scsb.util.LogUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private LdapService ldapService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ManagerRoleService managerRoleService;
	@Autowired
	private SheetService sheetService;
	

	// TODO 刪除
	@Value("${is.test}")
	private boolean isTest;
	
	@RequestMapping("/login")
	public String Login(@ModelAttribute Manager loginForm, Model model, HttpServletRequest request) 
	{
		request.getSession().removeAttribute(Constants.SESSION_MEMBER_KEY);
		request.getSession().removeAttribute(Constants.SESSION_FUNCTION_TASK_KEY);
		request.getSession().removeAttribute(Constants.SESSION_MEMBER_RISHTS);
		request.getSession().removeAttribute(Constants.SESSION_LOGIN_TIME_KEY);
		// [20220228] 登入人員是否為A、B級主管
        request.getSession().removeAttribute(Constants.SESSION_IS_MANAGER);
		return "views/login/login";
	}


	// Get登入
	@RequestMapping(value = "/postLogin", method = RequestMethod.GET)
	public String GetLogin(@RequestParam(name = "empNo") String empNo, Model model, HttpServletRequest request) 
	{
		try
		{
			Ldap ldap = ldapService.getDataByEmpNo(empNo);
			
			if(ldap == null || StringUtils.isBlank(ldap.getCn()))
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.LOGIN_URL);
	
			Manager member = new Manager();
			String roleId = managerRoleService.getRoleIdByDepartmentNumber(ldap.getDepartmentNumber());
			ManagerRole role = managerRoleService.getRoleById(roleId);
			// 檢查超級使用者權限
			if (ldap.getSeObject() != null && !StringUtils.isBlank(ldap.getSeObject().getCn()))
			{
				if (ldap.getSeObject().getCn().equals(Constants.SECURITYEQUALS_SUPERUSERGRP))
				{
					List<ManagerTask> taskList = taskService.superList();
					role.getTasks().addAll(taskList);
					role.setTasks(role.getTasks());
				}
			}
			member.setRole(role);
			member.setLdap(ldap);
			member.setName(ldap.getGivenName());
			//設置權限查詢表
	        Map<String, String> rights = member.getRole().getTasks().stream().collect(Collectors.toMap(ManagerTask::getId, ManagerTask::getName));
	        request.getSession().setAttribute(Constants.SESSION_MEMBER_RISHTS, rights);
	        
	        //功能列表
	        Map<String, List<ManagerTask>> tasks = taskService.organizeTasks(member.getRole().getTasks());
	        
	        Timestamp now = new Timestamp(System.currentTimeMillis());
	        member.getRole().setTasksMap(tasks);
	        request.getSession().setAttribute(Constants.SESSION_MEMBER_KEY, member);
	        request.getSession().setAttribute(Constants.SESSION_FUNCTION_TASK_KEY, tasks);
	        request.getSession().setAttribute(Constants.SESSION_LOGIN_TIME_KEY, now);
	        
	        List<Ldap> approverList = ldapService.getDepartmentPeople(ldap);
	        request.getSession().setAttribute(Constants.SESSION_APPROVERS, approverList);
	        
	        List<Ldap> prList = ldapService.getPrDepartmentPeople();
	        request.getSession().setAttribute(Constants.SESSION_PUBLIC_RELATIONS, prList);
	        
	        // [20220228] 登入人員是否為A、B級主管
	        request.getSession().setAttribute(Constants.SESSION_IS_MANAGER, ldapService.isManager(ldap));
	        
	        // 若有停刊申請的待核表單
	        List<Sheet> sheetList = sheetService.getCancelSheetListByCn(ldap.getCn());
	        if (sheetList != null && sheetList.size() > 0)
	        {
	        	return "redirect:/cancelSheet/list";
	        }
	        // 若有上架申請的待核表單
	        sheetList = sheetService.getPendingSheetListByCn(ldap.getCn());
	        if (sheetList != null && sheetList.size() > 0)
	        {
	        	return "redirect:/pendingSheet/list";
	        }
	        
			return "redirect:recordSheet/list";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LogUtil.setErrorLog("postLogin", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_LOGIN_LDAP_ERROR, Constants.LOGIN_URL);
		}
	}
	
	// Post登入
	@RequestMapping(value = "/postLogin", method = RequestMethod.POST)
	public String postLogin(@RequestParam(name = "empNo") String empNo, Model model, HttpServletRequest request) 
	{
		try
		{
			
			logger.info("=========empNo:"+empNo);
			
			Ldap ldap = ldapService.getDataByEmpNo(empNo);
			
			if(ldap == null || StringUtils.isBlank(ldap.getCn()))
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.LOGIN_URL);
	
			Manager member = new Manager();
			String roleId = managerRoleService.getRoleIdByDepartmentNumber(ldap.getDepartmentNumber());
			ManagerRole role = managerRoleService.getRoleById(roleId);
			
			log.info("=============role:{}",role);
			
			// 檢查超級使用者權限
			boolean  superUser = false;
			for (Iterator iterator = ldap.getSecurityEquals().iterator(); iterator.hasNext();) {
				String type = (String) iterator.next();
				String[] securityStrings = type.split(",");
				if (Constants.SECURITYEQUALS_SUPERUSERGRP.equals(securityStrings[0])) {
					superUser = true;
					log.info("=========superUser:{}",superUser);
				}
			}
			
			if (ldap.getSeObject() != null && !StringUtils.isBlank(ldap.getSeObject().getCn()))
			{
				if (superUser|| empNo.equals("1732")|| empNo.equals("12815")|| empNo.equals("9971")|| empNo.equals("17621"))
				{
					List<ManagerTask> taskList = taskService.superList();
					role.getTasks().addAll(taskList);
					role.setTasks(role.getTasks());
				}
			}
			member.setRole(role);
			member.setLdap(ldap);
			member.setName(ldap.getGivenName());
			
			// [20220228] 登入人員是否為A、B級主管
	        boolean isManager = ldapService.isManager(ldap);
			log.info("=========isManager:{}",isManager);
			//設置權限查詢表
//	        Map<String, String> rights = member.getRole().getTasks().stream().collect(Collectors.toMap(ManagerTask::getId, ManagerTask::getName));
	        Map<String, String> rights = member.getRole().getTasks().stream().filter(task -> isManager || !("200".equals(task.getId()) || "200".equals(task.getParentId()))).collect(Collectors.toMap(ManagerTask::getId, ManagerTask::getName));
	        request.getSession().setAttribute(Constants.SESSION_MEMBER_RISHTS, rights);
	        
	       
	        //功能列表
	        Map<String, List<ManagerTask>> tasks = taskService.organizeTasks(member.getRole().getTasks().stream().filter(task -> isManager || !("200".equals(task.getId()) || "200".equals(task.getParentId()))).collect(Collectors.toList()));
	        
	        Timestamp now = new Timestamp(System.currentTimeMillis());
	        member.getRole().setTasksMap(tasks);
	        request.getSession().setAttribute(Constants.SESSION_MEMBER_KEY, member);
	        request.getSession().setAttribute(Constants.SESSION_FUNCTION_TASK_KEY, tasks);
	        request.getSession().setAttribute(Constants.SESSION_LOGIN_TIME_KEY, now);
	        
	        List<Ldap> approverList = ldapService.getDepartmentPeople(ldap);
	        request.getSession().setAttribute(Constants.SESSION_APPROVERS, approverList);
	        
	        List<Ldap> prList = ldapService.getPrDepartmentPeople();
	        request.getSession().setAttribute(Constants.SESSION_PUBLIC_RELATIONS, prList);
	        
	        // [20220228] 登入人員是否為A、B級主管
	        request.getSession().setAttribute(Constants.SESSION_IS_MANAGER, ldapService.isManager(ldap));
	        
	        if (isManager) {
	        	// 若有停刊申請的待核表單
		        List<Sheet> sheetList = sheetService.getCancelSheetListByCn(ldap.getCn());
		        if (sheetList != null && sheetList.size() > 0)
		        {
		        	return "redirect:/cancelSheet/list";
		        }
		        // 若有上架申請的待核表單
		        sheetList = sheetService.getPendingSheetListByCn(ldap.getCn());
		        if (sheetList != null && sheetList.size() > 0)
		        {
		        	return "redirect:/pendingSheet/list";
		        }
			}
	        
	        
			return "redirect:/recordSheet/list";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LogUtil.setErrorLog("postLogin", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_LOGIN_LDAP_ERROR, Constants.LOGIN_URL);
		}
	}
	
	//登入
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute LoginForm form, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session)
	{
		try
		{
			// TODO 為開發用路線
			if (isTest)
			{
				Manager member = new Manager();
				String roleId = managerRoleService.getRoleIdByDepartmentNumber("部門編號");
	    		ManagerRole role = managerRoleService.getRoleById(roleId);
	    		
	    		Ldap ldap = new Ldap();
	    		ldap.setCn("1732");
	    		ldap.setGivenName("測試員");
	    		ldap.setDepartmentNumber("840000");
	    		ldap.setDepartmentNumberName("個人金融事業部");
	    		SecurityEquals se = new SecurityEquals();
	    		se.setCn(Constants.SECURITYEQUALS_SUPERUSERGRP);
	    		ldap.setSeObject(se);
	    		// 檢查超級使用者權限
				if (ldap.getSeObject() != null && !StringUtils.isBlank(ldap.getSeObject().getCn()))
				{
					List<ManagerTask> taskList = taskService.superList();
					
					role.getTasks().addAll(taskList);
					role.setTasks(role.getTasks());
				}
				member.setRole(role);
	    		member.setLdap(ldap);
	    		member.setName(ldap.getGivenName());
	    		
	    		boolean isManager = true;
	    		
	    		Map<String, String> rights = member.getRole().getTasks().stream().filter(task -> isManager || !("200".equals(task.getId()) || "200".equals(task.getParentId()))).collect(Collectors.toMap(ManagerTask::getId, ManagerTask::getName));
	            request.getSession().setAttribute(Constants.SESSION_MEMBER_RISHTS, rights);
	    		
	            Map<String, List<ManagerTask>> tasks = taskService.organizeTasks(member.getRole().getTasks().stream().filter(task -> isManager || !("200".equals(task.getId()) || "200".equals(task.getParentId()))).collect(Collectors.toList()));
	            
	            Timestamp now = new Timestamp(System.currentTimeMillis());
	            member.getRole().setTasksMap(tasks);
	            
				request.getSession().setAttribute(Constants.SESSION_MEMBER_KEY, member);
		        request.getSession().setAttribute(Constants.SESSION_FUNCTION_TASK_KEY, tasks);
		        request.getSession().setAttribute(Constants.SESSION_LOGIN_TIME_KEY, now);
		        
		        // [20220228] 登入人員是否為A、B級主管
		        request.getSession().setAttribute(Constants.SESSION_IS_MANAGER, isManager);
		        
		        // 審核人
		        List<Ldap> approverList = ldapService.fakeItClassA();
		        request.getSession().setAttribute(Constants.SESSION_APPROVERS, approverList);
		        
		        // 公關
		        List<Ldap> prList = ldapService.fakePrClassAll();
		        request.getSession().setAttribute(Constants.SESSION_PUBLIC_RELATIONS, prList);
		        
		        if (isManager) {// 只有主管帳號才重新導向
			        // 若有停刊申請的待核表單
			        List<Sheet> sheetList = sheetService.getCancelSheetListByCn(ldap.getCn());
			        if (sheetList != null && sheetList.size() > 0)
			        {
			        	return "redirect:/cancelSheet/list";
			        }
			        // 若有上架申請的待核表單
			        sheetList = sheetService.getPendingSheetListByCn(ldap.getCn());
			        if (sheetList != null && sheetList.size() > 0)
			        {
			        	return "redirect:/pendingSheet/list";
			        }
		        }
		        
				return "redirect:/recordSheet/list";
			}
			
			// ldap登入
			Ldap ldap = ldapService.getAuthorityForSystem(form.getZhho(), form.getMima());
	        String empNo = form.getZhho();
			if(ldap == null || StringUtils.isBlank(ldap.getCn()))
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.LOGIN_URL);
	
			Manager member = new Manager();
			String roleId = managerRoleService.getRoleIdByDepartmentNumber(ldap.getDepartmentNumber());
			ManagerRole role = managerRoleService.getRoleById(roleId);
			
			log.info("=============role:{}",role);
			
			// 檢查超級使用者權限
			boolean  superUser = false;
			for (Iterator iterator = ldap.getSecurityEquals().iterator(); iterator.hasNext();) {
				String type = (String) iterator.next();
				String[] securityStrings = type.split(",");
				if (Constants.SECURITYEQUALS_SUPERUSERGRP.equals(securityStrings[0])) {
					superUser = true;
					log.info("=========superUser:{}",superUser);
				}
			}
			
			if (ldap.getSeObject() != null && !StringUtils.isBlank(ldap.getSeObject().getCn()))
			{
				if (superUser|| empNo.equals("1732")|| empNo.equals("12815")|| empNo.equals("9971")|| empNo.equals("17621"))
				{
					List<ManagerTask> taskList = taskService.superList();
					role.getTasks().addAll(taskList);
					role.setTasks(role.getTasks());
				}
			}
			member.setRole(role);
			member.setLdap(ldap);
			member.setName(ldap.getGivenName());
			
			// [20220228] 登入人員是否為A、B級主管
	        boolean isManager = ldapService.isManager(ldap);
			log.info("=========isManager:{}",isManager);
			//設置權限查詢表
//	        Map<String, String> rights = member.getRole().getTasks().stream().collect(Collectors.toMap(ManagerTask::getId, ManagerTask::getName));
	        Map<String, String> rights = member.getRole().getTasks().stream().filter(task -> isManager || !("200".equals(task.getId()) || "200".equals(task.getParentId()))).collect(Collectors.toMap(ManagerTask::getId, ManagerTask::getName));
	        request.getSession().setAttribute(Constants.SESSION_MEMBER_RISHTS, rights);
	        
	       
	        //功能列表
	        Map<String, List<ManagerTask>> tasks = taskService.organizeTasks(member.getRole().getTasks().stream().filter(task -> isManager || !("200".equals(task.getId()) || "200".equals(task.getParentId()))).collect(Collectors.toList()));
	        
	        Timestamp now = new Timestamp(System.currentTimeMillis());
	        member.getRole().setTasksMap(tasks);
	        request.getSession().setAttribute(Constants.SESSION_MEMBER_KEY, member);
	        request.getSession().setAttribute(Constants.SESSION_FUNCTION_TASK_KEY, tasks);
	        request.getSession().setAttribute(Constants.SESSION_LOGIN_TIME_KEY, now);
	        
	        List<Ldap> approverList = ldapService.getDepartmentPeople(ldap);
	        request.getSession().setAttribute(Constants.SESSION_APPROVERS, approverList);
	        
	        List<Ldap> prList = ldapService.getPrDepartmentPeople();
	        request.getSession().setAttribute(Constants.SESSION_PUBLIC_RELATIONS, prList);
	        
	        // [20220228] 登入人員是否為A、B級主管
	        request.getSession().setAttribute(Constants.SESSION_IS_MANAGER, ldapService.isManager(ldap));
	        
	        if (isManager) {
	        	// 若有停刊申請的待核表單
		        List<Sheet> sheetList = sheetService.getCancelSheetListByCn(ldap.getCn());
		        if (sheetList != null && sheetList.size() > 0)
		        {
		        	return "redirect:/cancelSheet/list";
		        }
		        // 若有上架申請的待核表單
		        sheetList = sheetService.getPendingSheetListByCn(ldap.getCn());
		        if (sheetList != null && sheetList.size() > 0)
		        {
		        	return "redirect:/pendingSheet/list";
		        }
			}
	        
	        
			return "redirect:/recordSheet/list";
		}
		catch (Exception e)
		{
			LogUtil.setErrorLog("doLogin", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
		}
    }
	
	/**
	 * 登出
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) 
	{
		return "redirect:login";
	}
}