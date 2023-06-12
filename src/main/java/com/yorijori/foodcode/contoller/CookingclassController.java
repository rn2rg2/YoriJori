package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/cookingclass")
@Controller
public class CookingclassController {
	@RequestMapping("/Instructor")
	public String classListInstructor() {
		return "thymeleaf/cookingclass/classListInstructor";
	}
	@RequestMapping("/list")
	public String showCookingclassList() {
		return "thymeleaf/cookingclass/classList";
	}
	
	@RequestMapping("/read")
	public String showCookingclass() {
		return "thymeleaf/cookingclass/classRead";
	}
	
	@RequestMapping("/in")
	public String insertCookingclass() {
		return "thymeleaf/cookingclass/classInsert";
	}
}
