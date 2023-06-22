package com.yorijori.foodcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.service.ApiRecipeService;
import com.yorijori.foodcode.service.BoardService;
import com.yorijori.foodcode.service.MemberService;
import com.yorijori.foodcode.service.RecipeService;


@Controller
public class IndexController {
	// 유저 리스트 , 레시피 리스트 , 서버 레시피 리스트 , 게시물 리스트
	MemberService memberService;
	ApiRecipeService apiRecipeService;
	RecipeService recipeService;
	BoardService boardService;

	@Autowired
	public IndexController(MemberService memberService, ApiRecipeService apiRecipeService, RecipeService recipeService,
			BoardService boardService) {
		super();
		this.memberService = memberService;
		this.apiRecipeService = apiRecipeService;
		this.recipeService = recipeService;
		this.boardService = boardService;
	}

	@RequestMapping("/main")
	public String mainpage (Model model) {
		// 유저 리스트 , 레시피 리스트 , 서버 레시피 리스트 , 게시물 리스트
		// 회원수
		long userCount = memberService.userCount("회원"); 
		// 서버 레시피 수
		long serverRcpCount = apiRecipeService.countAll();
		// 유저 레시피 수 
		long userRcpCount = recipeService.countAll();
		// 게시물 수
		long boardCount = boardService.countAll();
		
		model.addAttribute("userCount", userCount);
		model.addAttribute("serverRcpCount", serverRcpCount);
		model.addAttribute("userRcpCount", userRcpCount);
		model.addAttribute("boardCount", boardCount);
		return "thymeleaf/index";
		
	}

	@RequestMapping("/test24")
	public String test (Model model) {
		return "thymeleaf/test";
	}

}
 