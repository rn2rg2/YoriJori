package com.yorijori.foodcode.controller;

import java.util.List;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.dto.CookingClassDTO;
import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;
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
		List<CookingClass> classList = service.selectAllClass();
		model.addAttribute("classList",classList);
		//model.addAttribute("session", session);
		System.out.println("출력전 확인"+classList);
		return "thymeleaf/cookingclass/classList";
	}
	
	@RequestMapping("/read")
	public String showCookingclass(int cookNo) {
		return "thymeleaf/cookingclass/classRead";
	}
	
	@RequestMapping("/in")
	public String showInsertCookingclass() {
		return "thymeleaf/cookingclass/classInsert";
	}
	@PostMapping("/in")
	public String insertCookingclass(CookingClass cookingclass,CookingClassContent content,CookingClassCurriculum curriculum) {
		service.insert(cookingclass, content, curriculum);
		return "thymeleaf/cookingclass/classList";
	}
	
}
