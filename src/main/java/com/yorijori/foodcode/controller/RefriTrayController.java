package com.yorijori.foodcode.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.RefriTrayService;

@Controller
@RequestMapping("/mypage")
public class RefriTrayController {
	IngredientService ingreService;
	RefriTrayService refriTrayService;

	@Autowired
	public RefriTrayController(IngredientService ingreService, RefriTrayService refriTrayService) {
		super();
		this.ingreService = ingreService;
		this.refriTrayService = refriTrayService;
	}

	@RequestMapping("/refri")
	public String refri(Model model, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		List<UserFrige> refrilist = refriTrayService.selectAll(user);
		long count = ingreService.countAll();
		long refriCount = refriTrayService.countByUserId(user);
		model.addAttribute("refrilist", refrilist);
		model.addAttribute("count", count);
		model.addAttribute("refriCount", refriCount);
		return "thymeleaf/mypage/refri";
	}

	@RequestMapping("/tray")
	public String tray(Model model, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		List<UserWishlist> recipeWishList = refriTrayService.selectWishListAll(userId);
		return "thymeleaf/mypage/tray";
	}
	
	@PostMapping("/refri/insert")
	public String refriInsert(UserFrige userfrige,HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		userfrige.setUserId(user);
		refriTrayService.insert(userfrige, user);
		return "redirect:/mypage/refri";
	}
}
