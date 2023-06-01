package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	@RequestMapping("/test")
	public String test() {
		return "test";
	}

	@RequestMapping("/thtest")
	public String thymeleaf_test(Model model) {
		model.addAttribute("msg", "Thymeleaf test");
		return "thymeleaf/admin/test";
	}
}
