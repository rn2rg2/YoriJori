package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

	@RequestMapping("/mypage")
	public String mainPage() {
		return "defaultMyPage";
	}

	
	@RequestMapping("/recipeList")
	public String recipeList() {
		return "myPageRecipeList";
	}

	@RequestMapping("/commentList")
	public String commentList() {
		return "myPageCommentList";
	}

	@RequestMapping("/chat")
	public String chat() {
		return "myPageChat";
	}

}