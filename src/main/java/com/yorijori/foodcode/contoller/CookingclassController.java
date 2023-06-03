package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/cookingclass")
@Controller
public class CookingclassController {
	
	@RequestMapping("/list")
	public String showCookingclassList() {
		return "thymeleaf/cookingclass/classList";
	}
}
