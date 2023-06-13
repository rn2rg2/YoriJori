package com.yorijori.foodcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking")
public class RankingController {
	
	@RequestMapping("/list")
	public String profile() {
		return "thymeleaf/ranking/rankinglist";
	}


}