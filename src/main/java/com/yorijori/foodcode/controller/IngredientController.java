package com.yorijori.foodcode.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.entity.Ingredients;

@Controller
@RequestMapping("ingredient")
public class IngredientController {

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
