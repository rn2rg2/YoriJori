package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	@RequestMapping("/main")
	public String mainpage (Model model) {
		return "thymeleaf/mainpage/index";
		
	}

	@RequestMapping("/test24")
	public String test (Model model) {
		return "thymeleaf/test";
	}

}
 