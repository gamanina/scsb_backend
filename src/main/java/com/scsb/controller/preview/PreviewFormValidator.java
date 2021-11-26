package com.scsb.controller.preview;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.scsb.config.Constants;

@Component
public class PreviewFormValidator {
	
	/**
	 * 表單驗證
	 * @param errors
	 * @throws Exception 
	 */
	public void validate(PreviewForm form, BindingResult result) throws Exception 
	{
		// 檔案類型取得
		String fileName = form.getImageFile() == null ? null : form.getImageFile().getOriginalFilename();
		String fileType = form.getImageFile() == null ? null : fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		
		if (StringUtils.isBlank(form.getOnTimeDate()) || StringUtils.isBlank(form.getOffTimeDate())) 
		{
			result.rejectValue("onTimeDate", "error", "請輸入刊登時間");
		}
		
		if (StringUtils.isBlank(form.getTitle()))
		{
			result.rejectValue("title", "error", "請輸入標題");
		}
		
		// 依據表單類型驗證是否輸入類別
		String type =form.getType();
		if (StringUtils.isNotBlank(type)) {
			switch(form.getType()) {
			case Constants.CARD_SWIPE_HOT_SHEET_TYPE:// 信用卡 - 刷卡優惠 > 熱門活動 
			case Constants.CARD_SWIPE_DISCOUNT_SHOP_SHEET_TYPE:// 信用卡 - 刷卡優惠 > 優惠商店 
				if (StringUtils.isBlank(form.getCategory()))
				{
					result.rejectValue("category", "error", "請選擇表單類別");
				}
				break;
			}
		}
		
		// 防止script,style注入
		Pattern pattenScriptTag = Pattern.compile("<script[^>]*>(?:.*?)</script>");
		Pattern pattenStyleTag = Pattern.compile("<style[^>]*>(?:.*?)</style>");
		String content = form.getContent().replace("\r\n", "");
		if (pattenScriptTag.matcher(content).find() || pattenStyleTag.matcher(content).find()) {
			result.rejectValue("content", "error", "請勿輸入script或style等非法標籤");
		}

		
		if (form.getImageFile() != null && StringUtils.isBlank(form.getImageFile().getOriginalFilename())) {
			result.rejectValue("imageFile", "error", "請選擇圖片");
		}
		if (form.getImageFile() != null && !form.getImageFile().isEmpty()) 
		{
			if (!Constants.FILETYPES.contains(fileType))
			{
				result.rejectValue("imageFile", "error", "請上傳圖片格式檔案(jpg、jpeg、png、gif)");
			}
			else if (form.getImageFile().getSize() >= 500 * 1024)
			{
				result.rejectValue("imageFile", "error", "圖片大小不得超過500k");
	        }
        }
				
	}
	
}
