package com.yorijori.foodcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.service.CookingClassService;
@RequestMapping("/cookingclass")
@Controller
public class CookingclassController {
	
	CookingClassService service;
	
	@Autowired
	public CookingclassController(CookingClassService service) {
		super();
		this.service = service;
	}
	
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
