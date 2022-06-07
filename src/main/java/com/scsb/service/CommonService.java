package com.scsb.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.scsb.config.Constants;
import com.scsb.config.MessageConstants;
import com.scsb.model.Manager;
import com.scsb.model.ManagerTask;
import com.scsb.model.Sheet;


@Service
public class CommonService 
{
	@SuppressWarnings("unchecked")
	public String setCommonInfo(Model model, HttpServletRequest request)
	{
		String result = Constants.RESULT_ERROR;
		List<ManagerTask> tasks = null;
		Map<String, ManagerTask> secondMap = null;
		Manager user = null;
		try 
		{
			tasks = (List<ManagerTask>) request.getSession().getAttribute(Constants.SESSION_FUNCTION_TASK_KEY);
			user = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
		}
		catch (Exception e)
		{
			return result;
		}

		if (tasks == null || user == null) 
		{
			return result;
		}
		
		model.addAttribute("tasks", tasks);
		model.addAttribute("user", user);
		result = Constants.RESULT_SUCCESS;
		return result;
	}

	public List<String> getFormProcessFormType(String type, String status)
	{
		
		System.out.println("================");
		System.out.println(status.equals(Constants.SHEET_STATUS_OFF_SHELF) || status.equals(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING));
		System.out.println("================");
		
		if (status.equals(Constants.SHEET_STATUS_OFF_SHELF) || status.equals(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING))
		{
			return Arrays.asList("審核", "覆核");
		}
		else
		{
			
			System.out.println("================");
			System.out.println(Constants.NONE_PORMOTION_LIST.contains(type));
			System.out.println("================");
			if (Constants.NONE_PORMOTION_LIST.contains(type))// 非廣告類表單只需單位主管審批
			{
				return Arrays.asList("審核", "覆核");
			}
			else
			{
				return Arrays.asList("審核", "公關", "覆核");
			}
		}
	}
	
	// 檢查是否為最後一步的表單
	public boolean checkFinalStep(Sheet sheet, String page)
	{
		boolean result = false; 
		if (page.equals(Constants.PAGE_KEY_APPROVAL))
		{
			if (Constants.NONE_PORMOTION_LIST.contains(sheet.getType()))// 非廣告類表單只需單位主管審批
			{
				if (sheet.getStep() == 2)
					result = true;
			}
			else
			{
				if (sheet.getStep() == 3)
					result = true;
			}
		}
		
		if (page.equals(Constants.PAGE_KEY_CANCEL))
		{
			if (sheet.getStep() == 2)
				result = true;
		}
		return result;	
	}
	
	public String alertPageSetUp(Model model, String status, String msg, String redirectUrl)
	{
		model.addAttribute("result", status);
		model.addAttribute("msg", msg);
		model.addAttribute("redirectUrl", redirectUrl);
		return "views/common/alert";
	}
}
