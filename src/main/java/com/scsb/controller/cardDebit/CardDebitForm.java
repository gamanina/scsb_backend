package com.scsb.controller.cardDebit;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 信用卡>Debit卡>刷卡優惠表單物件
 */
@Data
public class CardDebitForm 
{
	private String applicantId;
	private String applicant;
	private String applicantUnitId;
	private String applicantUnit;
	private String onTimeDate;
	private String onTimeHour;
	private String onTimeMin;
	private String offTimeDate;
	private String offTimeHour;
	private String offTimeMin;
	private String nextApproverId;
	private String nextApprover;
	private String agentId;
	private String agent;
	private String title;
	private String type;
	private String category; 
	private MultipartFile imageFile;
//	private String imageUrl;
//	private MultipartFile file;
//	private MultipartFile file2;
//	private MultipartFile file3;
//	private MultipartFile file4;
//	private MultipartFile file5;
	private String content;

}
