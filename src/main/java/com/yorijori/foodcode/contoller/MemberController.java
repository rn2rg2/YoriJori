package com.yorijori.foodcode.contoller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.service.KakaoService;


@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private KakaoService ms;
	
	
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
	
	// 카카오 로그인 
	@RequestMapping(value="/signkakao", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
		System.out.println("#########" + code);
		String access_Token = ms.getAccessToken(code);
        
		// 위에서 만든 코드 아래에 코드 추가
		HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
		
		return "thymeleaf/member/signUpForm2";
		
	}
	@RequestMapping("/signup3")
	public String signUp3() {
		return "thymeleaf/member/signUpForm3";
	}
	@RequestMapping("/signup4")
	public String signUp4() {
		return "thymeleaf/member/signUpForm4";
	}
}
