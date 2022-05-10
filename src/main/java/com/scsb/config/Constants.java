package com.scsb.config;

import java.util.Arrays;
import java.util.List;

import com.scsb.util.WebUtil;

/**
 * 
 * 建立日期：2020/04/15
 * 程式摘要：com.scsb.config
 * 類別名稱：Constants.java
 * 程式內容說明：共用常數
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
public class Constants {
	
	/** 啟用狀態  0:關閉 1:開啟 2:封存 3:未開通 **/
    public static final String STATUS_ENABLE = "1";
    public static final String STATUS_DISABLE = "0";
	
	/** 時間格式 **/
	public static final String TIME_FORMAT_YYYYMMDD_HHMMSS = "yyyy/MM/dd HH:mm:ss";
	public static final String TIME_FORMAT_YYYYMMDD = "yyyy/MM/dd";
	public static final String TIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/** 系統常用 **/
	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String SYSTEM_ADMIN = "SYSTEM";
	
	/** SESSION **/
	public static final String SESSION_MEMBER_KEY = "member";
	public static final String SESSION_FUNCTION_TASK_KEY = "tasks";
	public static final String SESSION_LOGIN_TIME_KEY = "login_time";
	public static final String SESSION_MEMBER_RISHTS = "rights";
	public static final String SESSION_APPROVERS = "approvers";
	public static final String SESSION_PUBLIC_RELATIONS = "publicRelations";
	public static final String SESSION_IS_MANAGER = "isManager";
	
    /** 後端結果驗證  0:成功 1:錯誤 2:資訊 **/
    public static final String RESULT_SUCCESS = "0";
    public static final String RESULT_ERROR = "1";
    public static final String RESULT_INFO = "2";

    /** ROLE ID **/
    public static final String ROLE_ID_ADMIN = "1";	//超級使用者
    public static final String ROLE_ID_PR = "2"; //公關
    public static final String ROLE_ID_BUSINESS = "3"; //企業金融
    public static final String ROLE_ID_PERSONAL = "4"; //個人金融
    public static final String ROLE_ID_DEPOSIT = "5"; //台外幣存匯
    public static final String ROLE_ID_DIGIT = "6"; //數位金融
    public static final String ROLE_ID_OTHER = "7"; //其他
    public static final String ROLE_ID_TEST = "8"; //測試帳號
    
    /** 頁面Task ID **/
    public static final String PAGE_KEY_INDEX_ANNOUNCE = "10001"; // 首頁-本行公告
    public static final String PAGE_KEY_INDEX_ACTIVITY = "10002"; // 首頁-最新活動
    public static final String PAGE_KEY_INDEX_BANNER = "10003"; // 首頁-廣告輪播
    public static final String PAGE_KEY_INDEX_WINNERS = "10004"; // 首頁-中獎名單
    public static final String PAGE_KEY_BUSINESS_BANNER = "10005"; //企業金融-廣告輪播
    public static final String PAGE_KEY_BUSINESS_AD = "10006"; //企業金融-廣告
    public static final String PAGE_KEY_PERSONAL_BANNER = "10007"; //個人金融-廣告輪播
    public static final String PAGE_KEY_PERSONAL_AD = "10008"; //個人金融-廣告
    public static final String PAGE_KEY_DEPOSIT_BANNER = "10009"; //台外幣存匯-廣告廣告輪播
    public static final String PAGE_KEY_DEPOSIT_AD = "10010"; //台外幣存匯-廣告
    public static final String PAGE_KEY_DIGIT_BANNER = "10011"; //數位金融-廣告輪播
    public static final String PAGE_KEY_DIGIT_AD = "10012"; //數位金融-廣告
    public static final String PAGE_KEY_CARD_BANNER = "10013"; //信用卡 - 廣告輪播
    public static final String PAGE_KEY_CARD_AD = "10014"; //信用卡 - 廣告
    public static final String PAGE_KEY_CARD_SWIPE_HOT = "10015"; //信用卡 - 刷卡優惠 > 熱門活動
    public static final String PAGE_KEY_CARD_SWIPE_GIFT = "10016"; //信用卡 -  刷卡優惠 > 新戶禮
    public static final String PAGE_KEY_CARD_SWIPE_DISCOUNT_SHOP = "10017"; //信用卡 - 刷卡優惠 > 優惠商店
    public static final String PAGE_KEY_CARD_REWARD = "10018";//信用卡>紅利積點>紅利兌換
    public static final String PAGE_KEY_CARD_DEBIT_DISCOUNT = "10019"; //信用卡 - Debit卡 > 刷卡優惠
    public static final String PAGE_KEY_RECORDSHEET = "200"; //待核表單
    public static final String PAGE_KEY_APPROVAL = "20001"; //刊登
    public static final String PAGE_KEY_CANCEL = "20002"; //停刊
    public static final String PAGE_KEY_HISTORY = "300"; //表單紀錄
    public static final String PAGE_KEY_HISTORY_SUPERIOR = "400"; //管理者表單紀錄
    public static final String PAGE_KEY_REMIND_EMAIL_SETTING = "500"; //系統提醒
    
    /** 圖片類型 **/
    public static final List<String> FILETYPES = Arrays.asList("jpg", "jpeg", "png", "gif");
    
    /** 表單狀態 狀態0:處理中-刊登,1:已通過,2:已退回,3:已下架,4:處理中-停刊 **/
    public static final String SHEET_STATUS_PROCESSING = "0";
    public static final String SHEET_STATUS_PASSED = "1";
    public static final String SHEET_STATUS_RETURNED = "2";
    public static final String SHEET_STATUS_OFF_SHELF = "3";
    public static final String SHEET_STATUS_OFF_SHELF_PROCESSING = "4";
    
    //表單型態
    /** 首頁- 本行公告*/
    public static final String INDEX_ANNOUNCE_SHEET_TYPE = "0";
    /** 首頁- 最新活動 */
    public static final String INDEX_ACTIVITY_SHEET_TYPE = "1";
    /** 首頁- 廣告輪播 */
    public static final String INDEX_BANNER_SHEET_TYPE = "2";
    /** 首頁- 中獎名單 */
    public static final String INDEX_WINNERS_SHEET_TYPE = "3";
    /** 企金- 廣告輪播 */
    public static final String BUSINESS_BANNER_SHEET_TYPE = "4";
    /** 企金- 廣告 */
    public static final String BUSINESS_AD_SHEET_TYPE = "5";
    /** 個金- 廣告輪播 */
    public static final String PERSONAL_BANNER_SHEET_TYPE = "6";
    /** 個金- 廣告 */
    public static final String PERSONAL_AD_SHEET_TYPE = "7";
    /** 台外幣存匯- 廣告輪播 */
    public static final String DEPOSIT_BANNER_SHEET_TYPE = "8";
    /** 台外幣存匯- 廣告 */
    public static final String DEPOSIT_AD_SHEET_TYPE = "9";
    /** 數金- 廣告輪播 */
    public static final String DIGIT_BANNER_SHEET_TYPE = "10";
    /** 數金- 廣告 */
    public static final String DIGIT_AD_SHEET_TYPE = "11";
    /** 信用卡 - 廣告輪播 */
    public static final String CARD_BANNER_SHEET_TYPE = "12";
    /** 信用卡 - 廣告 */
    public static final String CARD_AD_SHEET_TYPE = "13";
    /** 信用卡 - 刷卡優惠 > 熱門活動 */
    public static final String CARD_SWIPE_HOT_SHEET_TYPE = "14";
    /** 信用卡 - 刷卡優惠 > 新戶禮 */
    public static final String CARD_SWIPE_GIFT_SHEET_TYPE = "15";
    /** 信用卡 - 刷卡優惠 > 優惠商店 */
    public static final String CARD_SWIPE_DISCOUNT_SHOP_SHEET_TYPE = "16";
    /** 信用卡 - 紅利積點 > 紅利兌換 */
    public static final String CARD_REWARD_SHEET_TYPE = "17";
    /** 信用卡 - 紅利積點 > 紅利折抵 */
    public static final String CARD_REWARD_REDEEM_SHEET_TYPE = "18";
    /** 信用卡 - Debit卡 > 刷卡優惠 */
    public static final String CARD_DEBIT_DISCOUNT_SHEET_TYPE = "19";
    
    /** 非廣告類表單 */
    public static final List<String> NONE_PORMOTION_LIST = Arrays.asList("0",  "3", "14", "15", "16", "17", "18", "19");
    
    /** 簽核意見狀態 **/
    public static final String SHEET_APPROVAL_APPLICATION = "0";
    public static final String SHEET_APPROVAL_PASS = "1";
    public static final String SHEET_APPROVAL_REFUSE = "2";
    
    /** 表單步驟數字 **/
    public static final int STEP_1 = 1;
    
    /** 簽核表單順序 **/
    public static final int SORT_0 = 0; // 經辦
    
    static {
    	// 將contextPath抽出來，若之後war檔名稱有換可以避免忘記改動讀不到頁面的麻煩
//        public static final String LOGIN_URL = "/scsb-springboot-0.0.3/login";
//        public static final String INDEX_BANNER_URL = "/scsb-springboot-0.0.3/indexBanner/add";
//        public static final String PENDINGSHEET_URL = "/scsb-springboot-0.0.3/pendingSheet/list";
//        public static final String CANCELSHEET_URL = "/scsb-springboot-0.0.3/cancelSheet/list";
//        public static final String RECORDSHEET_URL = "/scsb-springboot-0.0.3/recordSheet/list";
//        public static final String REMIND_EMAIL_URL = "/scsb-springboot-0.0.3/remindEmailSetting/edit";
    	LOGIN_URL = WebUtil.getContextPath() + "/login";
    	INDEX_BANNER_URL = WebUtil.getContextPath() + "/indexBanner/add";
    	PENDINGSHEET_URL = WebUtil.getContextPath() + "/pendingSheet/list";
    	CANCELSHEET_URL = WebUtil.getContextPath() + "/cancelSheet/list";
    	RECORDSHEET_URL = WebUtil.getContextPath() + "/recordSheet/list";
    	REMIND_EMAIL_URL = WebUtil.getContextPath() + "/remindEmailSetting/edit";
    	EMAIL_LOGIN_URL = "/postLogin";
    	System.out.println("====LOGIN_URL>>>" + WebUtil.getContextPath() + "/login");
    	System.out.println("====INDEX_BANNER_URL>>>" + WebUtil.getContextPath() + "/indexBanner/add");
    	System.out.println("====PENDINGSHEET_URL>>>" + WebUtil.getContextPath() + "/pendingSheet/list");
    	System.out.println("====CANCELSHEET_URL>>>" + WebUtil.getContextPath() + "/cancelSheet/list");
    	System.out.println("====RECORDSHEET_URL>>>" + WebUtil.getContextPath() + "/recordSheet/list");
    	System.out.println("====REMIND_EMAIL_URL>>>" + WebUtil.getContextPath() + "/remindEmailSetting/edit");
    }
    
    /** 連結網址 **/
    public static final String LOGIN_URL;
    public static final String INDEX_BANNER_URL;
    public static final String PENDINGSHEET_URL ;
    public static final String CANCELSHEET_URL ;
    public static final String RECORDSHEET_URL ;
    public static final String REMIND_EMAIL_URL ;
    public static final String EMAIL_LOGIN_URL ;
    

    
    /** 表單頁訊息提示 **/
    public static final String MSG_NO_CONDITION = "請先選擇搜尋條件";
    public static final String MSG_NO_DATA = "查無相關資料";
    
    /** 預覽頁網址 **/
    /** 企金-廣告預覽資源路徑 */
    public static final String PREVIEW_BUSINESS_AD="views/preview/previewBusinessAd";
    /** 企金-廣告輪播預覽資源路徑 */
    public static final String PREVIEW_BUSINESS_BANNER="views/preview/previewBusinessBanner";
    /** 個金-廣告預覽資源路徑 */
    public static final String PREVIEW_PERSONAL_AD="views/preview/previewPersonalAd";
    /** 個金-廣告輪播預覽資源路徑 */
    public static final String PREVIEW_PERSONAL_BANNER="views/preview/previewPersonalBanner";
    /** 台外幣存匯-廣告預覽資源路徑 */
    public static final String PREVIEW_DEPOSIT_AD="views/preview/previewDepositAd";
    /** 台外幣存匯-廣告輪播預覽資源路徑 */
    public static final String PREVIEW_DEPOSIT_BANNER="views/preview/previewDepositBanner";
    /** 數金-廣告預覽資源路徑 */
    public static final String PREVIEW_DIGIT_AD="views/preview/previewDigitAd";
    /** 數金-廣告輪播預覽資源路徑 */
    public static final String PREVIEW_DIGIT_BANNER="views/preview/PreviewDigitBanner";
    /** 首頁-本行公告預覽預覽資源路徑 */
    public static final String PREVIEW_INDEX_ANNOUNCE="views/preview/previewIndexAnnounce";
    /** 首頁-最新活動預覽資源路徑 */
    public static final String PREVIEW_INDEX_ACTIVITY="views/preview/previewIndexActivity";
    /** 首頁-廣告輪播預覽資源路徑 */
    public static final String PREVIEW_INDEX_BANNER="views/preview/previewIndexBanner";
    /** 首頁-中獎名單預覽資源路徑 */
    public static final String PREVIEW_INDEX_WINNERS="views/preview/previewIndexWinners";
    /** 信用卡-廣告預覽資源路徑 */
    public static final String PREVIEW_CARD_AD="views/preview/previewCardAd";
    /** 信用卡-廣告輪播預覽資源路徑 */
    public static final String PREVIEW_CARD_BANNER = "views/preview/previewCardBanner";
    /** 信用卡-熱門活動預覽資源路徑 */
    public static final String PREVIEW_CARD_DISCOUNT_HOT = "views/preview/previewCardSwipeDiscountHot";
    /** 信用卡-新戶禮預覽資源路徑 */
    public static final String PREVIEW_CARD_DISCOUNT_GIFT = "views/preview/previewCardSwipeDiscountGift";
    /** 信用卡-優惠商店預覽資源路徑 */
    public static final String PREVIEW_CARD_DISCOUNT_SHOP = "views/preview/previewCardDiscountShop";
    /** 信用卡-紅利兌換預覽資源路徑 */
    public static final String PREVIEW_CARD_REWARD = "views/preview/previewCardReward";
    /** 信用卡-Debit卡-刷卡優惠預覽資源路徑 */
    public static final String PREVIEW_CARD_DEBIT = "views/preview/previewCardDebit";
    
    /** ldap權限群組字 **/
    public static final String SECURITYEQUALS_SUPERUSERGRP = "superuserGrp";
    public static final String SECURITYEQUALS_GENERALGRP = "generalGrp";
	

}