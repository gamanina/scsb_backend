package com.scsb.controller.preview;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PreviewForm {
	private String scsbSheetId;
	private String applicantUnitId;
	private String applicantUnit;
	private String title;
	private String type;
	private String category;
	private String onTimeDate;
	private String offTimeDate;
    private String image;
    private MultipartFile imageFile;
    private MultipartFile file;
    private MultipartFile file2;
    private MultipartFile file3;
    private MultipartFile file4;
    private MultipartFile file5;
    private String content;
    private String imageUrl;
    
}
