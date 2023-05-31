package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookingclassController {
	
	@RequestMapping("/cookingclass/list")
	public String showCookingclassList() {
		return "cookingclass/classList";
	}
}
