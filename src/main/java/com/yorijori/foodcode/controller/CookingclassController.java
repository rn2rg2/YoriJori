package com.yorijori.foodcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.dto.CookingClassDTO;
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
	public String showCookingclassList(Model model) {
		List<CookingClassDTO> classList = service.selectAllClass();
		model.addAttribute("classList",classList);
		
		System.out.println("출력전 확인"+classList);
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
