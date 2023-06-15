package com.yorijori.foodcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.service.IngredientService;

@Controller
@RequestMapping("ingredient")
public class IngredientController {
	IngredientService ingredientService;
	
	@Autowired
	public IngredientController(IngredientService ingredientService) {
		super();
		this.ingredientService = ingredientService;
	}
	

	@RequestMapping("/list")
	public String profile(Model model) {
		return "thymeleaf/common/ingredient";
	}


	@GetMapping("/getListByPage")
	@ResponseBody
	public List<Ingredients> getListByPage(int page, int pagePerCount) {
		System.out.println(page);
		List<Ingredients> list = null;
		return list;
	}
	
}
