package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ingredient")
public class IngredientController {
	@RequestMapping("/list")
	public String profile() {
		return "thymeleaf/common/ingredient";
	}
}
