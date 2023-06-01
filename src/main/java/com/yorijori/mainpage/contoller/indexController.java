package com.yorijori.mainpage.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class indexController {
	@RequestMapping("/main")
	public String mainpage () {
		return "index";
	}
}