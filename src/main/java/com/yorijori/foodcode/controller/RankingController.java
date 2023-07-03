package com.yorijori.foodcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.jpa.entity.SearchLog;
import com.yorijori.foodcode.service.SearchLogService;

@Controller
@RequestMapping("/ranking")
public class RankingController {
	SearchLogService searchservice;

	@Autowired
	public RankingController(SearchLogService searchservice) {
		super();
		this.searchservice = searchservice;
	}

	@RequestMapping("/rlist")
	public String reciperank() {
		return "thymeleaf/ranking/rrankinglist";
	}
	
	@RequestMapping("/klist")
	public String keywordrank(Model model) {
		List<SearchLog> top10list = searchservice.findTop10ByCount();
		List<SearchLog> searchlist = searchservice.findTop100ByCount();
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("top10list", top10list);
		return "thymeleaf/ranking/krankinglist";
	}
	
	@RequestMapping("/clist")
	public String chefrank() {
		return "thymeleaf/ranking/crankinglist";
	}

}