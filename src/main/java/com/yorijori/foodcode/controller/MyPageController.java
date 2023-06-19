package com.yorijori.foodcode.controller;

import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.dto.UserInfoDTO;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
	
	private final ProfileService profileservice;
	
	@RequestMapping("/profile")
	public String profile() {
		return "thymeleaf/mypage/my_user_info";
	}
	@PostMapping("/update2")
	public String updateProfile(HttpSession session, String Email, String Nickname) {
		UserInfo user = (UserInfo)session.getAttribute("userInfo");
		System.out.println(user);
		System.out.println("Email ->> "+Email);
		System.out.println("Nickname=>>>" + Nickname);
		profileservice.updateprofile2(user, Email, Nickname);
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