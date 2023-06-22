package com.yorijori.foodcode.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class ThumbnailMaker {
	@Value("${file.dir}") // c://project/upload
	private String uploadpath;

	public String getUploadpath(String filename) {
		String path = uploadpath + "/thumbnail/" + filename;
		return path; // 폴더명 + 이미지 명
	}
	/*
	 * public uploadThumbnail( MultipartFile uploadfile) {
	 * 
	 * }
	 */
}
