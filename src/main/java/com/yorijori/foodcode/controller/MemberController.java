package com.yorijori.foodcode.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yorijori.foodcode.dto.UserInfoDTO;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.KakaoService;
import com.yorijori.foodcode.service.MemberService;

import lombok.RequiredArgsConstructor;


@Controller

@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@Autowired
	private KakaoService ms;
    @Autowired
    private MemberService userService;
    
	
    @GetMapping("/login/page")
    public String login() {
        return "thymeleaf/member/loginpage";
    }
	
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword, HttpSession session,Model model) {
        UserInfo loginUser = userService.loginUser(userName, userPassword);

        if (loginUser != null && loginUser.getPass().equals(userPassword)) {
        	session.setAttribute("userInfo", loginUser);
            return "thymeleaf/index";
        }
        return "thymeleaf/member/loginpage";
        
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
        return "thymeleaf/index";
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
	@PostMapping("/signUp")
	public String signUp(@ModelAttribute UserInfoDTO userinfodto,
	        @RequestParam("number1") String number1, // Change int to Integer
	        @RequestParam("number2") String number2,
	        @RequestParam("number3") String number3,
	        Model model) {
	    Date today = new Date();
	    String number = number1 + number2 + number3;
	    int number5 = Integer.parseInt(number);
	    userinfodto.setRole("회원");
	    userinfodto.setPhone_number(number5);
	    userinfodto.setPoint(36); 
	    userinfodto.setState(0);
	    userinfodto.setKakaoID(userinfodto.getKakaoID());
	    System.out.println("test:::"+userinfodto.getKakaoID());
	    String kakaoID = userinfodto.getKakaoID();
	    

	    userinfodto.setDATE(new java.sql.Date(today.getTime()));
	    System.out.println(userinfodto);
	    memberService.save(userinfodto);
	    return "thymeleaf/member/signUpForm2";
	}
	// 카카오 로그인 
	@RequestMapping(value="/signkakao", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code,Model model,HttpSession session) throws Exception {
		String view = "";
		String access_Token = ms.getAccessToken(code);
		HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
		String loginname = (String) userInfo.get("id");

        UserInfo loginUser = userService.loginKakao(loginname);

		if(loginUser != null && loginUser.getKakaoID().equals(loginname)) {
        	session.setAttribute("userInfo", loginUser);
        	view = "thymeleaf/index";	
		}else {
			model.addAttribute("nickname", userInfo.get("nickname"));
			model.addAttribute("email", userInfo.get("email"));
			model.addAttribute("id", userInfo.get("id"));
			view = "thymeleaf/member/signUpForm2";
		}
		return view;	
		
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
