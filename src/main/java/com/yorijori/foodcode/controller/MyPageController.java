package com.yorijori.foodcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@RequestMapping("/profile")
	public String profile() {
		return "thymeleaf/mypage/my_user_info2";
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
	@RequestMapping("/refri")
	public String refri(Model model) {
		int count = 40;
		model.addAttribute("count", count);
		return "thymeleaf/mypage/refri";
	}
	@RequestMapping("/tray")
	public String tray() {
		return "thymeleaf/mypage/tray";
	}

}