package com.yorijori.foodcode.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.common.ServerRecipeAPI;

@RequestMapping("/recipe")
@Controller
public class RecipeController {
	ServerRecipeAPI recipeAPI;
	
	@Autowired
	public RecipeController(ServerRecipeAPI recipeAPI) {
		super();
		this.recipeAPI = recipeAPI;
	}

	@RequestMapping("/QA")
	private String qaRecipe(Model model) {
		return "thymeleaf/recipe/recipeQA";
	}

	@RequestMapping("/view")
	private String viewRecipe(Model model) {
		return "thymeleaf/recipe/recipeview";
	}

	@RequestMapping("/list")
	private String listRecipe(Model model) {
		return "thymeleaf/recipe/recipelist";
	}

	@RequestMapping("/insert")
	private String insertRecipe(Model model) {
		return "thymeleaf/recipe/recipeInsert";
	}

	@RequestMapping(value="/list/{type}/{pageNo}/{pagePerResult}",produces = "application/text;charset=utf-8" )
	@ResponseBody
	public String getlistRecipe(@PathVariable String type, @PathVariable String pageNo,
			@PathVariable String pagePerResult, Model model) throws IOException {
		String result = "";
		if (type.equals("server")) { //server recipe
			result = recipeAPI.getServerRecipe(pageNo, pagePerResult);
			result = result.replaceAll(", ,", ", "); // 데이터에 ,, 두개가있는 부분이있음....
		} else { // db recipe
			
		}
		return result;
	}
}
