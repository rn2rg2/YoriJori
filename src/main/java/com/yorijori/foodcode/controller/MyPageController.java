package com.yorijori.foodcode.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.jpa.entity.RecipeIngredients;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

	private final ProfileService profileservice;
	FileUploadLogic fileuploadlogic;

	@Value("${file.dir}") // c://project/upload
	private String uploadpath;

	@RequestMapping("/profile")
	public String profile() {
		return "thymeleaf/mypage/my_user_info";
	}

	@PostMapping("/updateprofileimage")
	public String updateprofileimage(HttpSession session, MultipartFile profilephoto, String cookingpurpose) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");

		user.setPurpose(cookingpurpose);

		if (profilephoto != null) {
			
			String imagePath = uploadpath + "user/" + user.getImgPath();
			File imagefile = new File(imagePath);
			System.out.println("파일 시스아웃" + imagefile);
			imagefile.delete();
			String fileRoot = uploadpath + "user/"; // 저장될 외부 파일 경로
			String originalFileName = profilephoto.getOriginalFilename(); // 오리지날 파일명
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자
			String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명
			File targetFile = new File(fileRoot + savedFileName);
			

			try {
				InputStream fileStream = profilephoto.getInputStream();
				FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
				user.setImgPath(savedFileName);
				System.out.println(user);
			} catch (IOException e) {
				FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
				e.printStackTrace();
			}
		}
		profileservice.updateprofileimage(user);

		return "redirect:/mypage/profile";
	}

	@PostMapping("/updateprofile")
	public String updateProfile(HttpSession session, String Email, String Nickname) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		user = profileservice.updateprofile2(user, Email, Nickname);
		session.setAttribute("userInfo", user);
		return "redirect:/mypage/profile";
	}

	@RequestMapping("/recipelist")
	public String recipeList() {
		return "thymeleaf/mypage/recipelist";
	}

	@RequestMapping("/commentlist")
	public String commentList() {
		return "thymeleaf/mypage/commentlist";
	}

	@RequestMapping("/chat")
	public String chat() {
		return "thymeleaf/mypage/chat";
	}

}