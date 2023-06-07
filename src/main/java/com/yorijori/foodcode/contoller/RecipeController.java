package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/recipe")
@Controller
public class RecipeController {
	@RequestMapping("/list")
	private String listRecipe(Model model) {
		return "thymeleaf/recipe/recipelist";
	}
	@RequestMapping("/insert")
	private String insertRecipe(Model model) {
		return "thymeleaf/recipe/recipeInsert";
	}
}
