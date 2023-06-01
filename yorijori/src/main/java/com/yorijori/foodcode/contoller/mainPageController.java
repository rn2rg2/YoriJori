package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainPageController {

	@RequestMapping("/mainPage.do")
	public String mainPage() {
		return "defaultMainPage";
	}
	
	@RequestMapping("/recipeList")
	public String recipeList() {
		return "mainPageRecipeList";
	}
	
	@RequestMapping("/commentList")
	public String commentList() {
		return "mainPageCommentList";
	}
	
	@RequestMapping("/chat")
	public String chat() {
		return "mainPageChat";
	}

}
