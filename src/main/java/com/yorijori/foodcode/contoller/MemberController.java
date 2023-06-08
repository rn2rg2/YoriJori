package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	@RequestMapping("/login")
	public String login() {
		return "thymeleaf/member/loginpage";
	}
	@RequestMapping("/register")
	public String register() {
		return "member/register";
	}
	@RequestMapping("/signup1")
	public String signUp1() {
		return "thymeleaf/member/signUpForm1";
	}
	@RequestMapping("/signup2")
	public String signUp2() {
		return "thymeleaf/member/signUpForm2";
	}
	@RequestMapping("/signup3")
	public String signUp3() {
		return "thymeleaf/member/signUpForm3";
	}	
}
