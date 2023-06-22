package com.yorijori.foodcode.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.RefriTrayService;

@Controller
public class RefriTrayController {
	IngredientService ingreService;
	RefriTrayService rerfriTrayService;

	@Autowired
	public RefriTrayController(IngredientService ingreService, RefriTrayService rerfriTrayService) {
		super();
		this.ingreService = ingreService;
		this.rerfriTrayService = rerfriTrayService;
	}

	@RequestMapping("/mypage/refri")
	public String refri(Model model, HttpSession session) {
		UserInfo user= (UserInfo)session.getAttribute("userInfo");
		List<UserFrige> list = rerfriTrayService.selectAll(user);
		long count = ingreService.countAll();
		long refriCount = rerfriTrayService.countByUserId(user);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("refriCount", refriCount);
		return "thymeleaf/mypage/refri";
	}

	@RequestMapping("/mypage/tray")
	public String tray() {
		return "thymeleaf/mypage/tray";
	}
}
