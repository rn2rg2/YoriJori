package com.yorijori.foodcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;
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
		
		// 조회 순 인기 유저 레시피 모곩
		List<Recipe> rcpList = recipeService.selectListByPageAndSort(0, 4, "count");
		
		// 조회 순 인기 서버 레시피 목록
		List<ApiRecipe> apircpList = apiRecipeService.selectListByPageAndSort(0, 4, "count"); 
		
		// 유저 정보 나중에 
		List<UserInfo> userList = memberService.selectListByPageAndSort(0, 3, "point");
		
		// 게시물 목록
		
		
		// count 관련
		model.addAttribute("userCount", userCount);
		model.addAttribute("serverRcpCount", serverRcpCount);
		model.addAttribute("userRcpCount", userRcpCount);
		model.addAttribute("boardCount", boardCount);
		
		
		// list 관련
		model.addAttribute("rcpList", rcpList);
		model.addAttribute("apircpList", apircpList);
		model.addAttribute("userList", userList);
		
		return "thymeleaf/index";
		
	}

	@RequestMapping("/test24")
	public String test (Model model) {
		return "thymeleaf/test";
	}

}
 