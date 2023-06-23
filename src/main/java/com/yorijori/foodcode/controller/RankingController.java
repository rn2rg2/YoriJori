package com.yorijori.foodcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking")
public class RankingController {
	
	@RequestMapping("/rlist")
	public String reciperank() {
		return "thymeleaf/ranking/rrankinglist";
	}
	
	@RequestMapping("/klist")
	public String keywordrank() {
		return "thymeleaf/ranking/krankinglist";
	}
	
	@RequestMapping("/clist")
	public String chefrank() {
		return "thymeleaf/ranking/crankinglist";
	}

}