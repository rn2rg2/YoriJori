package com.yorijori.foodcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	@RequestMapping("/main")
	public String mainpage (Model model) {
		return "thymeleaf/index";
		
	}

	@RequestMapping("/test24")
	public String test (Model model) {
		return "thymeleaf/test";
	}

}
 