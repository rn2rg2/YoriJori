package com.yorijori.foodcode.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.RefriTrayService;
import com.yorijori.foodcode.service.UserWishService;

@Controller
@RequestMapping("/mypage")
public class RefriTrayController {
	IngredientService ingreService;
	RefriTrayService refriTrayService;
	UserWishService userWishService;

	@Autowired
	public RefriTrayController(IngredientService ingreService, RefriTrayService refriTrayService, UserWishService userWishService) {
		super();
		this.ingreService = ingreService;
		this.refriTrayService = refriTrayService;
		this.userWishService = userWishService;
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
		//UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		//String user = userinfo.getUserId();
		//List<UserWishlist> recipeWishList = userWishService.selectAll(userinfo,0,4);
		//model.addAttribute("recipeWishList", recipeWishList);
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
	
	@PostMapping("/wish/list")
	@ResponseBody
	public List<UserWishlist> getWishListByPage(int pageNo, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		List<UserWishlist> list = userWishService.selectAll(userinfo,pageNo, 4);
		return list;
	}
	
	@PostMapping("/wish/count")
	@ResponseBody
	public long getListCount() {
		long count = userWishService.countAll();
		return count;
	}
}
