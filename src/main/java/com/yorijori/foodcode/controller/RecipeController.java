package com.yorijori.foodcode.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.common.ServerRecipeAPI;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.service.RecipeService;

@RequestMapping("/recipe")
@Controller
public class RecipeController {
	ServerRecipeAPI recipeAPI;
	RecipeService recipeService;

	@Autowired
	public RecipeController(ServerRecipeAPI recipeAPI, RecipeService recipeService) {
		super();
		this.recipeAPI = recipeAPI;
		this.recipeService = recipeService;
	}

	@RequestMapping("/QA")
	private String qaRecipe(Model model) {
		return "thymeleaf/recipe/recipeQA";
	}

	@RequestMapping("/view")
	private String viewRecipe(Model model) {
		return "thymeleaf/recipe/recipeview";
	}

	@RequestMapping("/list/{type}/{pageNo}")
	private String listRecipe(Model model, @PathVariable String type, @PathVariable int pageNo) {
		if (type.equals("user")) {
			long count = recipeService.countAll();
			List<Recipe> list = recipeService.selectListByPage(pageNo, 9);
			for (Recipe data : list) {
				System.out.println(data.getReviewList());
			}
			model.addAttribute("count", count);
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
		}
		return "thymeleaf/recipe/recipelist";
	}

	@RequestMapping("/insert")
	private String insertRecipe(Model model) {
		return "thymeleaf/recipe/recipeInsert";
	}

	@RequestMapping(value = "/list/{type}/{pageNo}/{pagePerResult}", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String getlistRecipe(@PathVariable String type, @PathVariable String pageNo,
			@PathVariable String pagePerResult, Model model) throws IOException {
		String result = "";
		if (type.equals("server")) { // server recipe
			result = recipeAPI.getServerRecipe(pageNo, pagePerResult);
			result = result.replaceAll(", ,", ", "); // 데이터에 ,, 두개가있는 부분이있음....
		}
		return result;
	}
}
