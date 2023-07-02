package com.yorijori.foodcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yorijori.foodcode.dto.UserInfoDTO;
import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.Board;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ApiRecipeService;
import com.yorijori.foodcode.service.BoardService;
import com.yorijori.foodcode.service.MemberService;
import com.yorijori.foodcode.service.ProfileService;
import com.yorijori.foodcode.service.RecipeService;
import com.yorijori.foodcode.service.SearchLogService;


@Controller
public class IndexController {
	// 유저 리스트 , 레시피 리스트 , 서버 레시피 리스트 , 게시물 리스트
	MemberService memberService;
	ApiRecipeService apiRecipeService;
	RecipeService recipeService;
	BoardService boardService;
	ProfileService profileservice;
	SearchLogService searchservice;

	@Autowired
	public IndexController(MemberService memberService, ApiRecipeService apiRecipeService, RecipeService recipeService,
			BoardService boardService, SearchLogService searchservice, ProfileService profileservice) {
		super();
		this.memberService = memberService;
		this.apiRecipeService = apiRecipeService;
		this.recipeService = recipeService;
		this.boardService = boardService;
		this.profileservice = profileservice;
		this.searchservice = searchservice;
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
		List<Board> boardrcplist = boardService.selectByCategoryAndState("레시피질문", 0, 3);
		List<Board> storelist = boardService.selectByCategoryAndState("맛집추천", 0, 3);
		List<Board> eatlist = boardService.selectByCategoryAndState("오늘뭐먹지", 0, 3);
		
		
		// count 관련
		model.addAttribute("userCount", userCount);
		model.addAttribute("serverRcpCount", serverRcpCount);
		model.addAttribute("userRcpCount", userRcpCount);
		model.addAttribute("boardCount", boardCount);
		
		
		// list 관련
		model.addAttribute("rcpList", rcpList);
		model.addAttribute("apircpList", apircpList);
		model.addAttribute("userList", userList);
		model.addAttribute("boardrcplist",boardrcplist);
		model.addAttribute("storelist", storelist);
		model.addAttribute("eatlist", eatlist);
		
		return "thymeleaf/index";
		
	}
	@GetMapping("/user/{userId}")
	@ResponseBody
	public UserInfoDTO getUserProfile(@PathVariable("userId") String userId) {
	    // userId를 이용하여 유저 정보 조회
	    UserInfo user = profileservice.readuserinfo(userId);
	    UserInfoDTO userDTO = new UserInfoDTO();
	    userDTO.setUserId(user.getUserId());
	    userDTO.setRole(user.getRole());
	    userDTO.setNickname(user.getNickname());
	    userDTO.setPass(user.getPass());
	    userDTO.setEmail(user.getEmail());
	    userDTO.setName(user.getName());
	    userDTO.setPhoneNumber(user.getPhoneNumber());
	    userDTO.setSsn(user.getSsn());
	    userDTO.setImgPath(user.getImgPath());
	    userDTO.setPoint(user.getPoint());
	    userDTO.setPrefer(user.getPrefer());
	    userDTO.setPurpose(user.getPurpose());
	    userDTO.setAllergy(user.getAllergy());
	    userDTO.setState(user.getState());
	    userDTO.setDate(user.getDate());
	    userDTO.setKakaoID(user.getKakaoID());
	    
	    return userDTO;
	}
	@RequestMapping("/test24")
	public String test (Model model) {
		return "thymeleaf/test";
	}
	

}
 