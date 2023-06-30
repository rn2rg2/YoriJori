package com.yorijori.foodcode.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.jpa.VO.RecipeVO;
import com.yorijori.foodcode.jpa.VO.UserTrayListResponse;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserTray;
import com.yorijori.foodcode.jpa.entity.UserTrayList;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.RecipeService;
import com.yorijori.foodcode.service.RefriTrayService;
import com.yorijori.foodcode.service.UserWishService;

@Controller
@RequestMapping("/mypage")
public class RefriTrayController {
	IngredientService ingreService;
	RefriTrayService refriTrayService;
	UserWishService userWishService;
	RecipeService recipeService;

	@Autowired
	public RefriTrayController(IngredientService ingreService, RefriTrayService refriTrayService, UserWishService userWishService, RecipeService recipeService) {
		super();
		this.ingreService = ingreService;
		this.refriTrayService = refriTrayService;
		this.userWishService = userWishService;
		this.recipeService = recipeService;
	}

	@RequestMapping("/refri")
	public String refri(Model model, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		List<UserFrige> refrilist = new ArrayList<UserFrige>();
		refrilist = refriTrayService.selectAll(user);
		long count = ingreService.countAll();
		long refriCount = refriTrayService.countByUserId(user);
		if (refrilist.size() > 0  ) {
			List<RecipeVO> rcplist = new ArrayList<RecipeVO>();
			int maxSize = 4;
			if ( refrilist.size() < 4) {
				maxSize = refrilist.size();
			}
			for ( int i = 0 ; i < maxSize; i ++ ) {
				RecipeVO rcp = refriTrayService.getRecommendList(userinfo, refrilist.get(i));
				if (rcp != null ) {
					rcplist.add(rcp);
				}
				System.out.println("=================================");
				System.out.println(rcp);
				System.out.println("=================================");
			}
			if ( rcplist.size() > 0 ) {
				model.addAttribute("rcplist", rcplist);
			}
		}
		model.addAttribute("refrilist", refrilist);
		model.addAttribute("count", count);
		model.addAttribute("refriCount", refriCount);
		return "thymeleaf/mypage/refri";
	}

	@RequestMapping("/tray")
	public String tray(Model model, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		List<UserTray> tray = refriTrayService.selectTrayByUserId(user);
		model.addAttribute("tray", tray);
		return "thymeleaf/mypage/tray";
	}
	
	@PostMapping("/refri/insert")
	public String refriInsert(UserFrige userfrige,HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		userfrige.setUserId(user);
		refriTrayService.insert(userfrige, user);
		return "redirect:/mypage/refri";
	}
	
	@PostMapping("/tray/insert")
	public String refriInsert(UserTray usertray,HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		usertray.setUserId(user);
		refriTrayService.insertTray(usertray);
		return "redirect:/mypage/tray";
	}
	
	@RequestMapping("/tray/list/{trayNo}")
	@ResponseBody
	public List<UserTrayListResponse> getUserTrayDetail(@PathVariable int trayNo, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		List<UserTrayListResponse> list = new ArrayList<UserTrayListResponse>();
		UserTray tray = refriTrayService.selectTrayDetail(trayNo, user);
		for ( UserTrayList li : tray.getTrayList()) {
			int recipeNo = li.getRecipeNo();
			Recipe rcp = recipeService.select(recipeNo);
			UserTrayListResponse dto = new UserTrayListResponse(li, rcp);
			list.add(dto);
		}
		
		return list;
	}
	
	@GetMapping("/wish/list/{pageNo}/{pagePerCount}")
	@ResponseBody
	public List<UserWishlist> getWishListByPage(@PathVariable int pageNo, @PathVariable int pagePerCount, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		//List<RcpCategoryDTO> list = userWishService.selectRcpAndCategory(userinfo);
		//System.out.println(list);
		List<UserWishlist> list = userWishService.selectAll(userinfo,pageNo, pagePerCount);
		return list;
	}
	
	@PostMapping("/wish/count")
	@ResponseBody
	public long getListCount(HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		long count = userWishService.countAllByUserId(userinfo);
		return count;
	}
}
