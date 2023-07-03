package com.yorijori.foodcode.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ApiRecipeService;
import com.yorijori.foodcode.service.BoardService;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.MemberService;
import com.yorijori.foodcode.service.RecipeService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	MemberService userService;
	RecipeService rcpService;
	IngredientService ingreService;
	ApiRecipeService apircpService;
	BoardService boardSerivce;

	@Autowired
	public AdminController(MemberService userService, RecipeService rcpService, IngredientService ingreService,
			ApiRecipeService apircpService, BoardService boardSerivce) {
		super();
		this.userService = userService;
		this.rcpService = rcpService;
		this.ingreService = ingreService;
		this.apircpService = apircpService;
		this.boardSerivce = boardSerivce;
	}

	@RequestMapping("")
	public String getAdminPage() {
		return "thymeleaf/admin/index";
	}

	@RequestMapping("/User")
	public String getAdminUserPage(Model model) {
		List<UserInfo> list = userService.selectall(1);
		List<UserInfo> list2 = userService.selectall(0);
		int count = 0;
		for (UserInfo userInfo : list) {
			LocalDate date = userInfo.getDate().toLocalDate();
			if (date.equals(LocalDate.now())) {
				count++;
			}
		}
		model.addAttribute("todayuser", count);
		model.addAttribute("userlist", list);
		model.addAttribute("secessioncount", list2.size());
		model.addAttribute("secessionlist", list2);
		model.addAttribute("usercount", userService.userCount("회원"));
		model.addAttribute("usercount2", userService.userCount("전문가"));

		model.addAttribute("date", LocalDate.now());

		return "thymeleaf/admin/AdminUserPage";
	}

	@ResponseBody
	@PostMapping("/UserDelete")
	public ResponseEntity deleteUser(@RequestParam("userId") String userId) {
		System.out.println(userId);
		userService.updateUserStateByUserId(userId, 0);
		return ResponseEntity.ok().build();
	}

	@ResponseBody
	@PostMapping("/UserRestore")
	public ResponseEntity restoreUser(@RequestParam("userId") String userId) {
		System.out.println(userId);
		userService.updateUserStateByUserId(userId, 1);
		return ResponseEntity.ok().build();
	}

	@RequestMapping("/Recipe")
	public String getAdminRcpPage(Model model) {
		List<Long> purposelist = rcpService.countByCategoryNo(5, 9);
		List<Long> countrylist = rcpService.countByCategoryNo(10, 13);
		List<Long> typelist = rcpService.countByCategoryNo(15, 20);
		List<Long> foodlist = rcpService.countByCategoryNo(21, 25);
		
		long rcpcount = rcpService.countAll();
		long apicount = apircpService.countAll();
		long ingrecount = ingreService.countAll();
		
		model.addAttribute("purposelist", purposelist);
		model.addAttribute("countrylist", countrylist);
		model.addAttribute("typelist", typelist);
		model.addAttribute("foodlist", foodlist);
		
		model.addAttribute("rcpcount", rcpcount);
		model.addAttribute("apicount", apicount);
		model.addAttribute("ingrecount", ingrecount);
		return "thymeleaf/admin/recipe";

	}
	
	@RequestMapping("/admin/board")
	public String getBoardPage(Model model) {
		long allcount = boardSerivce.countAll();
		long rcpcount = boardSerivce.getCountByCategorysAndState("레시피질문");
		long matzipcount = boardSerivce.getCountByCategorysAndState("맛집추천");
		long todayeatcount = boardSerivce.getCountByCategorysAndState("오늘뭐먹지");
		model.addAttribute("allcount", allcount);
		model.addAttribute("rcpcount", rcpcount);
		model.addAttribute("matzipcount", matzipcount);
		model.addAttribute("todayeatcount", todayeatcount);
		return "thymeleaf/admin/board";

	}
	
	@RequestMapping("/admin/help")
	public String getHelpPage(Model model) {
		return "thymeleaf/admin/help";

	}
	
	@RequestMapping("/admin/inquiry")
	public String getInquiryPage(Model model) {
		return "thymeleaf/admin/inquiry";

	}
	
	@RequestMapping("/admin/noti")
	public String getNotiPage(Model model) {
		return "thymeleaf/admin/noti";

	}
	
	
	

}
