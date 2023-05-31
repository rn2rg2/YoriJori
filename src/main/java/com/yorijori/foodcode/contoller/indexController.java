package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class indexController {
	@RequestMapping("/main")
	public String mainpage (Model model) {
		return "index";
	}
}
