package com.yorijori.foodcode.controller;

import java.awt.print.Pageable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.CookingClassService;
import com.yorijori.foodcode.service.MemberService;

@Controller
public class AdminController {
    MemberService userService;
    CookingClassService classService;
    
	
	public AdminController(MemberService userService, CookingClassService classService) {
		super();
		this.userService = userService;
		this.classService = classService;
	}

	@RequestMapping("/admin")
	public String getAdminPage() {
		return "thymeleaf/admin/index";
	}

	@RequestMapping("/AdminUserPage")
	public String getAdminUserPage(Model model) {
	    List<UserInfo> list = userService.selectall(1);
	    List<UserInfo> list2 = userService.selectall(0);
	    int count = 0;
	    for (UserInfo userInfo : list) {
	        LocalDate date = userInfo.getDate().toLocalDate();
	        if (date.equals(LocalDate.now())) {
	            count++;
	        }
	    }
		model.addAttribute("todayuser", count);
	    model.addAttribute("userlist", list);
	    model.addAttribute("secessioncount", list2.size());
	    model.addAttribute("secessionlist", list2);
	    model.addAttribute("usercount", userService.userCount("회원"));
	    model.addAttribute("date", LocalDate.now());

	    return "thymeleaf/admin/AdminUserPage";
	}
	
	@ResponseBody
	@PostMapping("/UserDelete")
	public ResponseEntity deleteUser(@RequestParam("userId") String userId) {
		System.out.println(userId);
		userService.updateUserStateByUserId(userId,0);
		 return ResponseEntity.ok().build();
	}
	@ResponseBody
	@PostMapping("/UserRestore")
	public ResponseEntity restoreUser(@RequestParam("userId") String userId) {
		System.out.println(userId);
		userService.updateUserStateByUserId(userId,1);
		 return ResponseEntity.ok().build();
	}
	
	@RequestMapping("/cooking")
	public String getAdminCookingclass(Model model) {
		List<CookingClass> list=classService.selectAllClass(0);
		List<CookingClass> list2=classService.selectAllClass(1);
		int count = 0;
	    for (CookingClass cclass : list) {
	        LocalDate date = cclass.getDate().toLocalDate();
	        if (date.equals(LocalDate.now())) {
	            count++;
	        }
	    }
	    model.addAttribute("classlist", list);
	    model.addAttribute("classlist2", list2);
		model.addAttribute("date", LocalDate.now());
		model.addAttribute("todayclass", count);
		model.addAttribute("deleteclass", list2.size());
		return "thymeleaf/admin/adminCookingclass";
	}
	@PostMapping("/classRestore")
	public ResponseEntity classRestore(int cookNo){
		classService.restore(cookNo);
		return ResponseEntity.ok().build();
	}
}
