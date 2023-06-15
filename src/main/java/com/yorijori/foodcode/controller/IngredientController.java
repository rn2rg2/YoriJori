package com.yorijori.foodcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	

	@RequestMapping("/list/{page}/{pagePerCount}")
	public String profile(@PathVariable int page,@PathVariable int pagePerCount, Model model) {
		long count = ingredientService.countAll();
		List<Ingredients> list = ingredientService.selectByPage(page, pagePerCount);
		model.addAttribute("page", page);
		model.addAttribute("count", count);
		model.addAttribute("list", list);
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
 