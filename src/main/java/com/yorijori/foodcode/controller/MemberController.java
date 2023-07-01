package com.yorijori.foodcode.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.CategoryService;
import com.yorijori.foodcode.service.KakaoService;
import com.yorijori.foodcode.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller

@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	private KakaoService ms;
	private MemberService userService;
	private CategoryService categoryservice;

	@Autowired
	public MemberController(MemberService memberService, KakaoService ms, MemberService userService,
			CategoryService categoryservice) {
		super();
		this.memberService = memberService;
		this.ms = ms;
		this.userService = userService;
		this.categoryservice = categoryservice;
	}

	@GetMapping("/loginpage")
	public String login(HttpServletRequest request, HttpSession session) {
		return "thymeleaf/member/loginpage";
	}

	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
			HttpSession session, Model model, HttpServletRequest request) {
		UserInfo loginUser = userService.login(userName, userPassword);

		String previousPage = (String) session.getAttribute("previousPage");
		session.removeAttribute("previousPage");
		
		if (loginUser != null && loginUser.getPass().equals(userPassword)) {
			session.setAttribute("userInfo", loginUser);
			if (loginUser.getRole().equals("관리자")) {
				return "redirect:/admin/main";
			} 
			if (previousPage != null && !previousPage.isEmpty()) {
	            return "redirect:" + previousPage;
			} else {
				return "thymeleaf/index";
			}
		}
		model.addAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
		return "thymeleaf/member/loginpage";

	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
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
	public String signUp2(Model model) {
		List<Category> categories = categoryservice.findByLevel(2);
		List<String> categoryNames = new ArrayList<>();

		for (Category category : categories) {
			categoryNames.add(category.getName());
		}
		model.addAttribute("categories", categoryNames);

		return "thymeleaf/member/signUpForm2";
	}

	@PostMapping("/signUp")
	public String signUp(@ModelAttribute UserInfo userinfodto, @RequestParam String num1, @RequestParam String num2,
			@RequestParam String num3, Model model) {
		String number = num1 + num2 + num3;

		Date today = new Date();
		String kakaoID = userinfodto.getKakaoID();
		String email = userinfodto.getEmail().replace(",", "");
		System.out.println(userinfodto.getPhoneNumber());
		System.out.println(num2);
		System.out.println(Integer.parseInt(number));
		userinfodto.setPhoneNumber(Integer.parseInt(number));
		userinfodto.setEmail(email);
		userinfodto.setRole("회원");
		userinfodto.setPoint(36);
		userinfodto.setState(0);
		userinfodto.setImgPath("userimg.png");
		userinfodto.setKakaoID(userinfodto.getKakaoID());
		userinfodto.setDate(new java.sql.Date(today.getTime()));

		System.out.println("test:::" + userinfodto.getKakaoID());

		System.out.println(userinfodto);
		memberService.save(userinfodto);
		return "thymeleaf/member/signUpForm3";
	}

	// 코드 설명 - 중복체크 컨트롤러
	//
	// - ID 중복체크 컨트롤러
	@GetMapping("/checkID")
	@ResponseBody
	public boolean checkUserId(@RequestParam("userId") String userId) {
		boolean check = memberService.idcheck(userId);
		System.out.println(userId);
		return check;
	}

	// - 닉네임 중복체크 컨트롤러
	@GetMapping("/checkNickName")
	@ResponseBody
	public boolean checkNickName(@RequestParam("nickName") String nickName) {
		boolean check = memberService.nicknamecheck(nickName);
		return check;
	}

	// 카카오 로그인
	@RequestMapping(value = "/signkakao", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model,
			HttpSession session) throws Exception {
		String view = "";
		String access_Token = ms.getAccessToken(code);
		HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
		String loginname = (String) userInfo.get("id");

		UserInfo loginUser = userService.loginKakao(loginname);
			//만약 카카오 로그인하는데 DB에 KaKao 토큰이 있을때
		if (loginUser != null && loginUser.getKakaoID().equals(loginname)) {
			session.setAttribute("userInfo", loginUser);
			view = "thymeleaf/index";
		} else {
			//만약 카카오 로그인하는데 토근을 호출하고 KaKao 토큰번호가 DB에 없을떄
			List<Category> categories = categoryservice.findByLevel(2);
			List<String> categoryNames = new ArrayList<>();
			for (Category category : categories) {
				categoryNames.add(category.getName());
			}
			model.addAttribute("nickname", userInfo.get("nickname"));
			model.addAttribute("email", userInfo.get("email"));
			model.addAttribute("id", userInfo.get("id"));
			model.addAttribute("categories", categoryNames);
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
