package com.scsb.controller.preview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.scsb.config.Constants;
import com.scsb.config.DataOption;
import com.scsb.config.MessageConstants;
import com.scsb.model.Sheet;
import com.scsb.service.CommonService;
import com.scsb.service.SheetService;
import com.scsb.util.LogUtil;
import com.scsb.util.WebUtil;

@Controller
@RequestMapping("/")
public class PreviewController {
	
	@Autowired
    private CommonService commonService;
	@Autowired
	private SheetService sheetService;
	@Autowired
	private DataOption dataOption;
	@Autowired
	PreviewFormValidator validator;
	
	@Value("${web.upload-path}")
	private String uploadPath;
	
	@RequestMapping("/previewBusinessBanner")
	 public String previewBusinessBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet", null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_BUSINESS_BANNER;
	 }

	@RequestMapping("/previewBusinessAd")
	public String previewBusinessAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet",null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_BUSINESS_AD;
	}
	
	@RequestMapping("/previewDepositBanner")
	 public String previewDepositBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet", null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_DEPOSIT_BANNER;
	 }
	
	@RequestMapping("/previewDepositAd")
	public String previewDepositAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet",null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_DEPOSIT_AD;
	}
	
	@RequestMapping("/previewDigitBanner")
	 public String previewDigitBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet", null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_DIGIT_BANNER;
	 }
	
	@RequestMapping("/previewDigitAd")
	public String previewDigitAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet",null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_DIGIT_AD;
	}
	
	@RequestMapping("/previewPersonalBanner")
	 public String previewPersonalBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet", null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_PERSONAL_BANNER;
	 }
	
	@RequestMapping("/previewPersonalAd")
	public String previewPersonalAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet",null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_PERSONAL_AD;
	}
	
	private String convertToBase64(MultipartFile imageFile) throws Exception
	{
		if(imageFile == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		try {
			byte[] imageByteArray = imageFile.getBytes();
			sb.append("data:image/png;base64,");
			sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(imageByteArray, false)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@RequestMapping("/previewCardBanner")
	public String previewCardBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_CARD_BANNER;
	}
	
	@RequestMapping("/previewCardAd")
	public String previewCardAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_CARD_AD;
	}
	
	@RequestMapping("/previewCardSwipeDiscountHot")
	public String previewCardSwipeDiscountHot(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request, BindingResult result) 
	{
		return doPreviewSheet(form, model, request, result, Constants.PREVIEW_CARD_DISCOUNT_HOT, Constants.CARD_SWIPE_HOT_SHEET_TYPE);
	}
	
	@RequestMapping("/previewCardSwipeDiscountGift")
	public String previewCardSwipeDiscountGift(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request, BindingResult result) 
	{
		return doPreviewSheet(form, model, request, result, Constants.PREVIEW_CARD_DISCOUNT_GIFT, Constants.CARD_SWIPE_GIFT_SHEET_TYPE);
	}
	
	@RequestMapping("/previewCardDiscountShop")
	public String previewCardDiscountShop(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request, BindingResult result) 
	{
		return doPreviewSheet(form, model, request, result, Constants.PREVIEW_CARD_DISCOUNT_SHOP, Constants.CARD_SWIPE_DISCOUNT_SHOP_SHEET_TYPE);
	}
	
	@RequestMapping("/previewCardReward")
	public String previewCardReward(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request, BindingResult result) 
	{
		return doPreviewSheet(form, model, request, result, Constants.PREVIEW_CARD_REWARD, Constants.CARD_REWARD_SHEET_TYPE);
	}
	
	@RequestMapping("/previewCardDebitDiscount")
	public String previewCardDebitDiscount(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request, BindingResult result) 
	{
		return doPreviewSheet(form, model, request, result, Constants.PREVIEW_CARD_DEBIT, Constants.CARD_DEBIT_DISCOUNT_SHEET_TYPE);
	}
	
	@RequestMapping("/previewSheet")
	public String previewSheet(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) {
		return doPreviewSheet(form, model, request);
	}
	
	@RequestMapping("/previewImgUrl")
	 public String previewImgUrl(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request)
	{	
		Sheet sheet = sheetService.getSheetById(Integer.parseInt(form.getScsbSheetId()));
		//TODO: image路徑待確認
		model.addAttribute("image",sheet.getImage());
		model.addAttribute("title",sheet.getTitle());
		model.addAttribute("content",sheet.getContent());
		model.addAttribute("sheet",sheet);
		//取得預覽頁面
		String previewPage="/";
		
		switch(sheet.getType()) {
		
	    case Constants.BUSINESS_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_BUSINESS_BANNER;
	        break;
	    case Constants.BUSINESS_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_BUSINESS_AD;
	        break;
	    case Constants.PERSONAL_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_PERSONAL_BANNER;
	        break;
	    case Constants.PERSONAL_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_PERSONAL_AD;
	        break;
	    case Constants.DEPOSIT_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_DEPOSIT_BANNER;
	        break;
	    case Constants.DEPOSIT_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_DEPOSIT_AD;
	        break;
	    case Constants.DIGIT_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_DIGIT_BANNER;
	        break;
	    case Constants.DIGIT_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_DIGIT_AD;
	        break;
	    case Constants.INDEX_ANNOUNCE_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_INDEX_ANNOUNCE;
	        break;
	    case Constants.INDEX_ACTIVITY_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_INDEX_ACTIVITY;
	        break;
	    case Constants.INDEX_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_INDEX_BANNER;
	        break;
	    case Constants.INDEX_WINNERS_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_INDEX_WINNERS;
	        break;
	    case Constants.CARD_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_CARD_BANNER;
	    	break;
	    case Constants.CARD_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_CARD_AD;
	    	break;
	        
	    default:
	        	model.addAttribute("result",Constants.RESULT_ERROR);
				model.addAttribute("msg",MessageConstants.MESSAGE_LOAD_PREVIEW_PAGE_ERROR);
				model.addAttribute("closeWindow", true);
			    LogUtil.setActionLog(" previewImgUrl loadPage: ", "id: " + sheet.getId() + " type: " + sheet.getType());
				return "views/common/alert";
		}
		
		return previewPage;
	}
	
	@RequestMapping("/previewIndexAnnounce")
	 public String previewIndexAnnounce(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			
			model.addAttribute("title",form.getTitle());
			model.addAttribute("image",base64);
			model.addAttribute("content",form.getContent());
			model.addAttribute("offTimeDate",form.getOffTimeDate());
			model.addAttribute("imageUrl",form.getImageUrl());
			model.addAttribute("fileName", form.getFile().getOriginalFilename());
			model.addAttribute("fileName2", form.getFile2().getOriginalFilename());
			model.addAttribute("fileName3", form.getFile3().getOriginalFilename());
			model.addAttribute("fileName4", form.getFile4().getOriginalFilename());
			model.addAttribute("fileName5", form.getFile5().getOriginalFilename());
			model.addAttribute("sheet",null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_INDEX_ANNOUNCE;
	 }
	
	@RequestMapping("/previewIndexActivity")
	 public String previewIndexActivity(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("title",form.getTitle());
			model.addAttribute("title",form.getTitle());
			model.addAttribute("image",base64);
			model.addAttribute("content",form.getContent());
			model.addAttribute("offTimeDate",form.getOffTimeDate());
			model.addAttribute("imageUrl",form.getImageUrl());
			model.addAttribute("fileName", form.getFile().getOriginalFilename());
			model.addAttribute("fileName2", form.getFile2().getOriginalFilename());
			model.addAttribute("fileName3", form.getFile3().getOriginalFilename());
			model.addAttribute("fileName4", form.getFile4().getOriginalFilename());
			model.addAttribute("fileName5", form.getFile5().getOriginalFilename());
			model.addAttribute("sheet",null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_INDEX_ACTIVITY;
	 }
	
	@RequestMapping("/previewIndexBanner")
	 public String previewIndexBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("sheet",null);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("closeWindow", true);
			return "views/common/alert";
		}
		return Constants.PREVIEW_INDEX_BANNER;
	 }
	
	@RequestMapping("/previewIndexWinners")
	 public String previewIndexWinners(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("title",form.getTitle());
			model.addAttribute("image",base64);
			model.addAttribute("content",form.getContent());
			model.addAttribute("offTimeDate",form.getOffTimeDate());
			model.addAttribute("imageUrl",form.getImageUrl());
			model.addAttribute("fileName", form.getFile().getOriginalFilename());
			model.addAttribute("fileName2", form.getFile2().getOriginalFilename());
			model.addAttribute("fileName3", form.getFile3().getOriginalFilename());
			model.addAttribute("fileName4", form.getFile4().getOriginalFilename());
			model.addAttribute("fileName5", form.getFile5().getOriginalFilename());
			model.addAttribute("sheet",null);
			
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","indexWinners");
			return "views/common/alert";
		}
		return Constants.PREVIEW_INDEX_WINNERS;
	 }
	
	private String doPreviewSheet(PreviewForm form, Model model, HttpServletRequest request) {
		return doPreviewSheet(form, model, request, null, null, null);
	}
	
	/**
	 * 共用預覽方法
	 * @param form
	 * @param model
	 * @param request
	 * @param sheetType
	 * @return
	 */
	private String doPreviewSheet(PreviewForm form, Model model, HttpServletRequest request, BindingResult result, String previewPage, String sheetType) {
		// 如果已送審
		if (form.getScsbSheetId() != null && !form.getScsbSheetId().trim().isEmpty()) {
			Sheet sheet = sheetService.getSheetById(Integer.parseInt(form.getScsbSheetId()));
			
			model.addAttribute("image",sheet.getImage());// 圖片
			model.addAttribute("title",sheet.getTitle());// 標題
			model.addAttribute("ontime",sheet.getOnTime());// 上架時間
			model.addAttribute("offtime",sheet.getOffTime());// 下架時間
			model.addAttribute("content",form.getContent());// 內文
			
			// 此處sheet.getFile()不一定有值，如果有值且讀取第一個檔案的檔名為空，表示使用者沒有上傳檔案
			if (sheet.getFile() != null && !"".equals(sheet.getFile())) {
				List<String> downloadList = new ArrayList<String>();
				addDownloadListSaved(downloadList, sheet.getFile());
				addDownloadListSaved(downloadList, sheet.getFile2());
				addDownloadListSaved(downloadList, sheet.getFile3());
				addDownloadListSaved(downloadList, sheet.getFile4());
				addDownloadListSaved(downloadList, sheet.getFile5());
				model.addAttribute("downloadList",downloadList);
			}
			
			Map<String, String> categoryMap = null;
			switch(sheet.getType()) {
			
			case Constants.INDEX_WINNERS_SHEET_TYPE:
				previewPage = Constants.PREVIEW_INDEX_WINNERS;
				categoryMap = null;//TODO 實作中獎名單TAG
				break;
		    case Constants.CARD_SWIPE_HOT_SHEET_TYPE:
		    	previewPage = Constants.PREVIEW_CARD_DISCOUNT_HOT;
		    	categoryMap = dataOption.getSheetCategoryByIndex(DataOption.INDEX_CARD_SWIPE_HOT_CATEGORYS);
		        break;
		    case Constants.CARD_SWIPE_GIFT_SHEET_TYPE:
		    	previewPage = Constants.PREVIEW_CARD_DISCOUNT_GIFT;
		    	break;
		    case Constants.CARD_SWIPE_DISCOUNT_SHOP_SHEET_TYPE:
		    	previewPage = Constants.PREVIEW_CARD_DISCOUNT_SHOP;
		    	break;
		    case Constants.CARD_REWARD_SHEET_TYPE:
		    	previewPage = Constants.PREVIEW_CARD_REWARD;
		    	break;
		        
		    default:
		        	model.addAttribute("result",Constants.RESULT_ERROR);
					model.addAttribute("msg",MessageConstants.MESSAGE_LOAD_PREVIEW_PAGE_ERROR);
					model.addAttribute("redirectUrl","/");
				    LogUtil.setActionLog(" previewSheet loadPage: ", "id: " + sheet.getId() + " type: " + sheet.getType());
					return "views/common/alert";
			}
			
			// 如果預覽頁面有實作則加入sheetTag返回對應預覽頁
			if (categoryMap != null && !categoryMap.isEmpty()) {
				model.addAttribute("sheetTag", categoryMap.get(sheet.getCategory()));// 表單tag
			}
			model.addAttribute("tagClass", sheet.getCategory());
		} else {
			// 表單驗證
			try {
				validator.validate(form, result);
				if (result.hasErrors()) {
					model.addAttribute("result",Constants.RESULT_ERROR);
					model.addAttribute("msg", result.getFieldError().getDefaultMessage());
					model.addAttribute("closeWindow",true);
					return "views/common/alert";
				}
				String base64 = convertToBase64(form.getImageFile());
				
				Map<String, String> categoryMap = null;
				switch(sheetType) {
				case Constants.INDEX_WINNERS_SHEET_TYPE:
					categoryMap = null;//TODO 實作中獎名單TAG
					break;
			    case Constants.CARD_SWIPE_HOT_SHEET_TYPE:
			    	categoryMap = dataOption.getSheetCategoryByIndex(DataOption.INDEX_CARD_SWIPE_HOT_CATEGORYS);
			        break;
			    case Constants.CARD_REWARD_SHEET_TYPE:
			    	categoryMap = dataOption.getSheetCategoryByIndex(DataOption.INDEX_CARD_REWARD_CATEGORYS);
			    	break;
				}
				
				if (categoryMap != null && !categoryMap.isEmpty()) {
					model.addAttribute("sheetTag", categoryMap.get(form.getCategory()));// 表單tag
				}
				model.addAttribute("tagClass", form.getCategory());// 表單tagClass
				model.addAttribute("image",base64);// 圖片
				model.addAttribute("title",form.getTitle());// 標題
				model.addAttribute("onTime",form.getOnTimeDate());// 上架時間
				model.addAttribute("offTime",form.getOffTimeDate());// 下架時間
				model.addAttribute("content",form.getContent());// 內文
				
				// 此處form.getFile()不一定有值，如果讀取第一個檔案的檔名為空，表示使用者沒有上傳檔案
				if (form.getFile() != null && !form.getFile().getOriginalFilename().isEmpty()) {
					// 產生附檔超連結(未送審)
					List<String> downloadList = new ArrayList<String>();
					addDownloadListNotSaved(downloadList, form.getFile());
					addDownloadListNotSaved(downloadList, form.getFile2());
					addDownloadListNotSaved(downloadList, form.getFile3());
					addDownloadListNotSaved(downloadList, form.getFile4());
					addDownloadListNotSaved(downloadList, form.getFile5());
					model.addAttribute("downloadList",downloadList);
				}
			} catch (Exception e) {
				model.addAttribute("result",Constants.RESULT_ERROR);
				model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
				model.addAttribute("closeWindow",true);
				return "views/common/alert";
			}
		}
		
		return previewPage; 
	}
	
	/**
	 * 產生附檔超連結(未送審)
	 * @param downloadList
	 * @param mfile
	 */
	private void addDownloadListNotSaved(List<String> downloadList, MultipartFile mfile) {
		String filename = mfile.getOriginalFilename();
		if (filename == null || "".equals(filename.trim())) {
			return;
		}
		downloadList.add(String.format("<a href=\"javascript:void(0);\" class=\"list-item\" title=\"%s\" %s>%s</a>",
				filename, getDataFormat(filename), filename));
	}
	
	/**
	 * 產生附檔超連結(已送審)
	 * @param downloadList
	 * @param fullpath
	 */
	private void addDownloadListSaved(List<String> downloadList, String fullpath) {
		String filename = fullpath == null ? null : fullpath.substring(fullpath.lastIndexOf('/') + 1);
		if (filename == null || "".equals(filename.trim())) {
			return;
		}
		fullpath = WebUtil.getContextPath() + fullpath;
		downloadList.add(String.format("<a href=\"%s\" class=\"list-item\" target=\"_blank\" title=\"%s\" %s>%s</a>",
				fullpath, filename, getDataFormat(filename), filename));
	}
	
	/**
	 * 取得檔案的類型
	 * @param filename
	 * @return
	 */
	private String getDataFormat(String filename) {
		String format = "";
		if (filename.toLowerCase().endsWith(".pdf")) {
			format = "data-format-pdf";
		} else if (filename.toLowerCase().endsWith(".doc") || filename.toLowerCase().endsWith(".docx")) {
			format = "data-format-doc";
		} else if (filename.toLowerCase().endsWith(".xls") || filename.toLowerCase().endsWith(".xlsx")) {
			format = "data-format-xls";
		} else if (filename.toLowerCase().endsWith(".zip")) {
			format = "data-format-zip";
		}
		return format;
	}
}