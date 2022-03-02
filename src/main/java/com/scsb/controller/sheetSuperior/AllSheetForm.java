package com.scsb.controller.sheetSuperior;

import lombok.Data;

/**
 *管理者表單搜尋物件
 */
@Data
public class AllSheetForm 
{
	private String strDate;
	private String endDate;
	private String applicant;
	private String tableName;
	private String status;
	private String keyword;
	

}
