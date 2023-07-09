package com.yorijori.foodcode.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.VO.MonthlyRcpVO;
import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ApiRecipeService;
import com.yorijori.foodcode.service.BoardService;
import com.yorijori.foodcode.service.CookingClassService;
import com.yorijori.foodcode.service.CustomerServiceService;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.MemberService;
import com.yorijori.foodcode.service.RecipeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	MemberService userService;
	RecipeService rcpService;
	IngredientService ingreService;
	ApiRecipeService apircpService;
	BoardService boardSerivce;
	CookingClassService classService;
	CustomerServiceService customerService;

	@Autowired
	public AdminController(MemberService userService, RecipeService rcpService, IngredientService ingreService,
			ApiRecipeService apircpService, BoardService boardSerivce, CookingClassService classService,CustomerServiceService customerService) {
		super();
		this.userService = userService;
		this.rcpService = rcpService;
		this.ingreService = ingreService;
		this.apircpService = apircpService;
		this.boardSerivce = boardSerivce;
		this.classService = classService;
		this.customerService = customerService;
	}

	@RequestMapping("")
	public String getAdminPage() {
		return "thymeleaf/admin/index";
	}

	@RequestMapping("/User")
	public String getAdminUserPage(Model model) {
		List<UserInfo> list = userService.selectall(1);
		List<UserInfo> list2 = userService.selectall(0);
		List<Long> usercount = userService.countByUserRole(0, 2);
		List<Long> counts = userService.countByUserPoint(1, 5);
		//System.out.println(counts);
		
		//System.out.println(usercount);
		int count = 0;
		for (UserInfo userInfo : list) {
			LocalDate date = userInfo.getDate().toLocalDate();
			if (date.equals(LocalDate.now())) {
				count++;
			}
		}
		model.addAttribute("todayuser", count);
		model.addAttribute("userinfo",usercount);
		model.addAttribute("userpoint", counts);
		model.addAttribute("userlist", list);
		model.addAttribute("secessioncount", list2.size());
		model.addAttribute("secessionlist", list2);
		model.addAttribute("usercount", userService.userCount("회원"));
		model.addAttribute("usercount2", userService.userCount("전문가"));

		model.addAttribute("date", LocalDate.now());

		return "thymeleaf/admin/AdminUserPage";
	}

	@ResponseBody
	@PostMapping("/UserDelete")
	public ResponseEntity deleteUser(@RequestParam("userId") String userId) {
		//System.out.println(userId);
		userService.updateUserStateByUserId(userId, 0);
		return ResponseEntity.ok().build();
	}

	@ResponseBody
	@PostMapping("/UserRestore")
	public ResponseEntity restoreUser(@RequestParam("userId") String userId) {
		//System.out.println(userId);
		userService.updateUserStateByUserId(userId, 1);
		return ResponseEntity.ok().build();
	}

	@RequestMapping("/Recipe")
	public String getAdminRcpPage(Model model) {
		List<Long> purposelist = rcpService.countByCategoryNo(5, 9);
		List<Long> countrylist = rcpService.countByCategoryNo(10, 13);
		List<Long> typelist = rcpService.countByCategoryNo(15, 20);
		List<Long> foodlist = rcpService.countByCategoryNo(21, 25);
		List<MonthlyRcpVO> monthlylist = rcpService.getMonthlyData();

		long rcpcount = rcpService.countAll();
		long apicount = apircpService.countAll();
		long ingrecount = ingreService.countAll();

		model.addAttribute("purposelist", purposelist);
		model.addAttribute("countrylist", countrylist);
		model.addAttribute("typelist", typelist);
		model.addAttribute("foodlist", foodlist);
		model.addAttribute("monthlylist", monthlylist);

		model.addAttribute("rcpcount", rcpcount);
		model.addAttribute("apicount", apicount);
		model.addAttribute("ingrecount", ingrecount);
		return "thymeleaf/admin/recipe";

	}

	@RequestMapping("/board")
	public String getBoardPage(Model model) {
		
		long allcount = boardSerivce.countAll();
		long rcpcount = boardSerivce.getCountByCategorysAndState("레시피질문");
		long matzipcount = boardSerivce.getCountByCategorysAndState("맛집추천");
		long todayeatcount = boardSerivce.getCountByCategorysAndState("오늘뭐먹지");
		long deletecount = boardSerivce.countByState(1);
		
		long noticecount = customerService.noticeCountAll();
		long questioncount = customerService.questionCountAll();
		long inquirycount = customerService.inquiryCountAll();
		
		model.addAttribute("allcount", allcount);
		model.addAttribute("rcpcount", rcpcount);
		model.addAttribute("matzipcount", matzipcount);
		model.addAttribute("todayeatcount", todayeatcount);
		model.addAttribute("deletecount", deletecount);
		model.addAttribute("noticecount", noticecount);
		model.addAttribute("questioncount", questioncount);
		model.addAttribute("inquirycount", inquirycount);
		return "thymeleaf/admin/board";

	}

	@RequestMapping("/cooking")
	public String getAdminCookingclass(Model model) {
		List<CookingClass> list = classService.selectAllClass(0);
		List<CookingClass> list2 = classService.selectAllClass(1);
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
		model.addAttribute("allclass",classService.countAll());
		model.addAttribute("deleteclass", list2.size());
		return "thymeleaf/admin/adminCookingclass";
	}
	@PostMapping("/classDelete")
	public ResponseEntity classDelete(int cookNo) {
		classService.delete(cookNo);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/classRestore")
	public ResponseEntity classRestore(int cookNo) {
		classService.restore(cookNo);
		return ResponseEntity.ok().build();
	}

	@RequestMapping("/help")
	public String getHelpPage(Model model) {
		return "thymeleaf/admin/help";

	}

	@RequestMapping("/inquiry")
	public String getInquiryPage(Model model) {
		return "thymeleaf/admin/inquiry";

	}

	@RequestMapping("/noti")
	public String getNotiPage(Model model) {
		return "thymeleaf/admin/noti";

	}

}
