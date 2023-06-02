package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@RequestMapping("/profile")
	public String mainPage() {
		return "defaultMyPage";
	}
	@RequestMapping("/recipelist")
	public String recipeList() {
		return "myPageRecipeList";
	}

	@RequestMapping("/commentlist")
	public String commentList() {
		return "myPageCommentList";
	}

	@RequestMapping("/chat")
	public String chat() {
		return "myPageChat";
	}
	
	@RequestMapping("/refri")
	public String refri() {
		return "";
	}
	
	@RequestMapping("/tray")
	public String tray() {
		return "";
	}

}