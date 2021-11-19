package com.scsb.controller.preview;

import org.springframework.web.multipart.MultipartFile;

public class PreviewForm {
	private String scsbSheetId;
    private String image;
    private MultipartFile imageFile;
    private String content;
    private String title;
    private String offTimeDate; 
    private String imageUrl;
    private MultipartFile file;
    private MultipartFile file2;
    private MultipartFile file3;
    private MultipartFile file4;
    private MultipartFile file5;
    
    
    
	
    
    
	
	
	public MultipartFile getFile2() {
		return file2;
	}




	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}




	public MultipartFile getFile3() {
		return file3;
	}




	public void setFile3(MultipartFile file3) {
		this.file3 = file3;
	}




	public MultipartFile getFile4() {
		return file4;
	}




	public void setFile4(MultipartFile file4) {
		this.file4 = file4;
	}




	public MultipartFile getFile5() {
		return file5;
	}




	public void setFile5(MultipartFile file5) {
		this.file5 = file5;
	}




	public MultipartFile getFile() {
		return file;
	}




	public void setFile(MultipartFile file) {
		this.file = file;
	}




	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getOffTimeDate() {
		return offTimeDate;
	}
	public void setOffTimeDate(String offTimeDate) {
		this.offTimeDate = offTimeDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getScsbSheetId() {
		return scsbSheetId;
	}
	public void setScsbSheetId(String scsbSheetId) {
		this.scsbSheetId = scsbSheetId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
