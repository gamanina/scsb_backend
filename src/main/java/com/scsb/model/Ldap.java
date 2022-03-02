package com.scsb.model;

import java.util.List;

import lombok.Data;

/**
 * 
 * 建立日期：2021/05/03
 * 程式摘要：com.scsb.model
 * 類別名稱：Ldap.java 
 * 程式內容說明：Ldap帳號物件
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
@Data
public class Ldap {
	
	/** 行員編號 **/
	private String cn;
	/** 行員姓名 **/
	private String givenName;
	/** 所在單位代碼 **/
	private String departmentNumber;
	/** 所在單位中文名稱 **/
	private String departmentNumberName;
	/** 就職狀況; 若為 A* 為在職, 若為D* 為留職停薪或離職退休 **/
	private String employmentStatus;
	/** 行員Email, 若要寄到Notes 則為行編 + @notes.scsb.com.tw **/
	private String mail;
	/** 直屬主管行編 **/
	private String supervisorAcno;
	/** 直屬主管姓名 **/
	private String supervisorName;
	/** 職稱 **/
	private String cardtitle;
	/** 職位名稱 **/
	private String title;
	/** 該值若為A 則為A級主管; B為B級主管; 空值為經辦 **/
	private String titleno;
	
	/** 單位代號 **/
	private String ou;
	/** 單位名稱 **/
	private String description;
	/** 單位主管 **/
	private String manAcctno;
	/** 遵法主管 **/
	private String observer;
	/** 權限群組字串 **/
	private List<String> securityEquals;
	/** 權限群組物件 **/
	private SecurityEquals seObject;

}
