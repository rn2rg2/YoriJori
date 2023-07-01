package com.yorijori.foodcode.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.dto.UserInfoNicknameDTO;
import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.CategoryService;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.ProfileService;


@Controller
@RequestMapping("/mypage")

public class MyPageController {



	ProfileService profileservice;
	FileUploadLogic fileuploadlogic;
	CategoryService categoryservice;
	IngredientService ingreservice;

	 @Autowired
	 public MyPageController(ProfileService profileservice, FileUploadLogic fileuploadlogic, 
			 CategoryService categoryservice, IngredientService ingreservice) {
		super();
		this.profileservice = profileservice;
		this.fileuploadlogic = fileuploadlogic;
		this.categoryservice = categoryservice;
		this.ingreservice = ingreservice;
	}

	@RequestMapping("/profile")
	public String profile(Model model, HttpSession session) {
		UserInfo user = (UserInfo)session.getAttribute("userInfo");
		String[] prefer = user.getPrefer().split(",");
		String[] allergy = user.getAllergy().split(",");
		List<Category> categorylist =  categoryservice.findByLevel(2);
		List<Ingredients> ingrelist = ingreservice.selectAll();
		//System.out.println("프로필에서 , 로 나누기 : "+ Arrays.toString(prefer));
		model.addAttribute("category", categorylist);
		model.addAttribute("prefer", prefer);
		model.addAttribute("ingredient", ingrelist);
		model.addAttribute("allergy", allergy);
		return "thymeleaf/mypage/my_user_info";
	}
	
	@PostMapping(value="/deleteUser") //produces = "application/json; charset=utf8")
	@ResponseBody
	public String deleteuser(HttpSession session) {
	    UserInfo user = (UserInfo) session.getAttribute("userInfo");
	    user.setState(0);
	    profileservice.updatestate(user);
	    //System.out.println("deleteUser 확인 : "+ user);
	    if (session != null) {
	        session.invalidate();
	    }
	    return "회원 탈퇴에 성공하셨습니다.";
	}
//	 //리스트 값 받아올 ajax 
//	@PostMapping(value="/getprefer",produces="application/json;charset=utf-8")
//	@ResponseBody
//	public List<Category> getprefer() {
//	    List<Category> categorylist = categoryservice.findAll();
//	    System.out.println("getprefer: "+categorylist);
//	    return categorylist;
//	}
	
	@PostMapping("/changepass")
	public String changepass(HttpSession session, String newpass) {
		UserInfo user =(UserInfo)session.getAttribute("userInfo");
		user.setPass(newpass);
		user = profileservice.updatepassword(user);
		return "redirect:/mypage/profile";
	}
	
	@PostMapping(value= "checknickname", produces="application/json;charset=utf-8")
	@ResponseBody
	public UserInfoNicknameDTO checknickname(String nickname) {
		UserInfo user = new UserInfo();
		user = profileservice.checknickname(nickname);
		UserInfoNicknameDTO userDTO = new UserInfoNicknameDTO();
		System.out.println("닉네임 체크할때 유저 값: " +user);
		if(user != null) { //유저 중복
			userDTO.setNickname(user.getNickname());
		}else {
			userDTO.setNickname("1");
		}
		return userDTO;
	}
	
	@PostMapping("/updateprofileimage")
	public String updateprofileimage(HttpSession session, MultipartFile profilephoto, String cookingpurpose) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");	
		String imagePath = fileuploadlogic.getUploadpath("user/" + user.getImgPath());
		user.setPurpose(cookingpurpose);

		if (profilephoto != null) {	
			String fileRoot = fileuploadlogic.getUploadpath("user/"); // 저장될 외부 파일 경로
			String originalFileName = profilephoto.getOriginalFilename(); // 오리지날 파일명
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자
			String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명
			File targetFile = new File(fileRoot + savedFileName);
			File imagefile = new File(imagePath);
			System.out.println("파일 시스아웃" + imagefile);
			

			try {
				InputStream fileStream = profilephoto.getInputStream();
				FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
				user.setImgPath(savedFileName);
				System.out.println(user);
				UserInfo updateduser = profileservice.updateprofileimage(user);
				if(updateduser != null) {
					imagefile.delete();
				}
			} catch (IOException e) {
				FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
				e.printStackTrace();
			}
		}
		return "redirect:/mypage/profile";
	}

	@PostMapping("/updateprofile")
	public String updateProfile(HttpSession session, String Email, String Nickname
			, @RequestParam("userprefer") List<String> prefer, @RequestParam("userallergy") List<String> allergy) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		System.out.println("유저정보 업데이트 하기 ㅋㅋ : "+ allergy); 
		String setprefer = String.join(",", prefer);
		String setallergy = String.join(",", allergy);
		System.out.println("유저 정보 저장할 allergy "+ setallergy);
		user.setPrefer(setprefer);
		user.setAllergy(setallergy);
		user.setEmail(Email);
		user.setNickname(Nickname);
		System.out.println("업데이트할 유저 정보"+user);
		user = profileservice.updateprofile(user);
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