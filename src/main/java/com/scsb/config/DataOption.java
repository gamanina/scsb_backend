package com.scsb.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ConcurrentReferenceHashMap.ReferenceType;

@Component
public class DataOption {

	/** 建立弱引用快取，如果在一個gc週期內重複調用可以從快取中取得資料 */
	private static Map<Object, Map<String, String>> cacheOption = new ConcurrentReferenceHashMap<Object, Map<String, String>>(16, ReferenceType.WEAK);

	/** == 使用Object當key更節省記憶體 == **/
	/** 狀態option快取index **/
	public static final Object INDEX_STATUS = new Object();
	/** 表單狀態option快取index **/
	public static final Object INDEX_SHEET_STATUS = new Object();
	/** 表單名稱option快取index **/
	public static final Object INDEX_SHEET_TYPES = new Object();
	/** 信用卡>刷卡優惠>熱門活動(分類)option快取index **/
	public static final Object INDEX_CARD_SWIPE_HOT_CATEGORYS = new Object();
	/** 信用卡>刷卡優惠>優惠商店(分類)option快取index **/
	public static final Object INDEX_CARD_SWIPE_DISCOUNT_SHOP_CATEGORYS = new Object();
	/** 信用卡>紅利積點>紅利兌換(分類)option快取index **/
	public static final Object INDEX_CARD_REWARD_CATEGORYS = new Object();
	/** 本行公告、最新活動、中獎名單各部門inner-tag class快取index **/
	public static final Object INDEX_DEPT_TAGS_CLASS = new Object();

	/**
	 * 取得狀態option
	 * @return Map<String, String>
	 */
	public Map<String, String> getStatusMap() {
		Map<String, String> statusMap = getFromCache(INDEX_STATUS);
		if (statusMap == null || statusMap.isEmpty()) {
			statusMap = new LinkedHashMap<String, String>();
			statusMap.put("0", "關閉");
			statusMap.put("1", "開啟");
			// 註冊快取, 重寫為不可編輯的Map
			statusMap = registCache(INDEX_SHEET_STATUS, statusMap);
		}
		return statusMap;
	}

	/**
	 * 取得表單狀態option
	 * @return Map<String, String>
	 */
	public Map<String, String> getSheetStatusMap() {
		Map<String, String> statusMap = getFromCache(INDEX_SHEET_STATUS);
		if (statusMap == null || statusMap.isEmpty()) {
			statusMap = new LinkedHashMap<String, String>();
			statusMap.put("0", "處理中-刊登");
			statusMap.put("1", "已通過");
			statusMap.put("2", "已退回");
			statusMap.put("3", "已停刊");
			statusMap.put("4", "處理中-停刊");
			// 註冊快取, 重寫為不可編輯的Map
			statusMap = registCache(INDEX_SHEET_STATUS, statusMap);
		}
		return statusMap;

	}

	/**
	 * 取得表單名稱option
	 * @return Map<String, String>
	 */
	public Map<String, String> getSheetTypeMap() {
		Map<String, String> typeMap = getFromCache(INDEX_SHEET_TYPES);
		if (typeMap == null || typeMap.isEmpty()) {
			typeMap = new LinkedHashMap<String, String>();
			typeMap.put("0", "本行公告");
			typeMap.put("1", "最新活動");
			typeMap.put("2", "廣告輪播");
			typeMap.put("3", "中獎名單");
			typeMap.put("4", "企金輪播");
			typeMap.put("5", "企金廣告");
			typeMap.put("6", "個金輪播");
			typeMap.put("7", "個金廣告");
			typeMap.put("8", "台外幣輪播");
			typeMap.put("9", "台外幣廣告");
			typeMap.put("10", "數金輪播");
			typeMap.put("11", "數金廣告");
			typeMap.put("12", "信用卡輪播");
			typeMap.put("13", "信用卡廣告");
			typeMap.put("14", "信用卡熱門");
			typeMap.put("15", "信用卡新戶禮");
			typeMap.put("16", "信用卡優惠商店");
			typeMap.put("17", "信用卡紅利兌換");
			typeMap.put("18", "信用卡紅利折抵");
			typeMap.put("19", "信用卡Debit刷卡優惠");
			// 註冊快取, 重寫為不可編輯的Map
			typeMap = registCache(INDEX_SHEET_TYPES, typeMap);
		}
		return typeMap;
	}

	/**
	 * 以index取得表單option
	 * @param index
	 * @return
	 */
	public Map<String, String> getSheetCategoryByIndex(Object index) {
		// 參數檢核
		if (index == null) {
			return null;
		}

		Map<String, String> map = getFromCache(index);
		if (map == null || map.isEmpty()) {
			map = new LinkedHashMap<String, String>();
			if (INDEX_CARD_SWIPE_HOT_CATEGORYS.equals(index)) {// 信用卡>刷卡優惠>熱門活動
				map.put("bonus", "紅利點數");
				map.put("travel", "餐旅活動");
				map.put("shopping", "網購活動");
				map.put("installment", "分期付款");
				map.put("mobilepay", "行動支付");
				map.put("cdcard", "卡片優惠");
			} else if (INDEX_CARD_SWIPE_DISCOUNT_SHOP_CATEGORYS.equals(index)) {// 信用卡>刷卡優惠>優惠商店
				map.put("travel", "旅遊休閒");
				map.put("shopping", "美食購物");
				map.put("movie", "藝文電影");
				map.put("installment", "分期付款優惠商店");
			} else if (INDEX_CARD_REWARD_CATEGORYS.equals(index)) {// 信用卡>紅利積點>紅利兌換
				map.put("ticket", "票券/航空");
				map.put("point", "點數/捐款");
				map.put("airport", "機場接送");
				map.put("discount", "費用折抵");
				map.put("exclusive", "獨家商品");
			} else if (INDEX_DEPT_TAGS_CLASS.equals(index)) {// 部門tagClass
				map.put("870000", "bus");// 企業金融
				map.put("890000", "ind");// 個人金融
				map.put("840000", "dep");// 台外幣存匯
				map.put("910000", "cdcard");// 信用卡部
				map.put("880000", "fund");// 財富管理部
				map.put("850000", "dig");// 數位金融部門
			}
			// 註冊快取, 重寫為不可編輯的Map
			map = registCache(index, map);
		}

		return map;
	}

	/**
	 * 從快取中取得option資料
	 * @param index
	 * @return
	 */
	private Map<String, String> getFromCache(Object index) {
		return cacheOption.get(index);
	}

	/**
	 * 註冊快取, 重寫為不可編輯的Map
	 * @param index
	 * @param map
	 */
	private Map<String, String> registCache(Object index, Map<String, String> map) {
		Map<String, String> registMap = Collections.unmodifiableMap(map);
		cacheOption.put(index, registMap);
		return registMap;
	}

}
