package com.yorijori.foodcode.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.apidata.RecipeDataFetcher;
import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;

import com.yorijori.foodcode.jpa.entity.RecipeReview;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ApiRecipeService;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.RecipeService;

@RequestMapping("/recipe")
@Controller
public class RecipeController {
	RecipeService recipeService;
	RecipeDataFetcher recipeDataFetcher;
	ApiRecipeService apiRecipeService;
	IngredientService ingredientservice;

	@Autowired
	public RecipeController(RecipeService recipeService, RecipeDataFetcher recipeDataFetcher,
			ApiRecipeService apiRecipeService, IngredientService ingredientservice) {
		super();
		this.recipeService = recipeService;
		this.recipeDataFetcher = recipeDataFetcher;
		this.apiRecipeService = apiRecipeService;
		this.ingredientservice = ingredientservice;
	}

	@RequestMapping("/QA")
	public String qaRecipe(Model model) {
		return "thymeleaf/recipe/recipeQA";
	}

	@RequestMapping("/view")
	public String viewRecipe(Model model) {
		return "thymeleaf/recipe/recipeview";
	}

	// recipe insert
	@RequestMapping("/insert")
	public String insertRecipe(Model model, String ingredient, String num) {
		System.out.println("재료정보 뽑기 19028391283-901823901823");
		System.out.println(ingredient+" "+num);
		List<Ingredients> list = ingredientservice.selectAll();
		model.addAttribute("list", list);
		return "thymeleaf/recipe/recipeInsert";
	}

	@RequestMapping("/list/{type}/{pageNo}")
	public String listRecipe(Model model, @PathVariable String type, @PathVariable int pageNo) throws IOException {
		if (type.equals("user")) { // user recipe
			long count = recipeService.countAll();
			List<Recipe> list = recipeService.selectListByPage(pageNo, 9);
			model.addAttribute("count", count);
			model.addAttribute("type", type);
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
		} else { // server recipe
			long count = apiRecipeService.countAll();
			List<ApiRecipe> list = apiRecipeService.getServerRecipe(pageNo, 9);
			model.addAttribute("count", count);
			model.addAttribute("type", type);
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
		}
		return "thymeleaf/recipe/recipelist";
	}

	@RequestMapping("/view/{type}/{rcpSeq}")
	public String getViewPage(Model model, @PathVariable String type, @PathVariable int rcpSeq, HttpServletRequest req,
			HttpServletResponse res) {
		String view = "";
		Recipe recipe = new Recipe();
		if (type.equals("server")) { // 서버 레시피 detail view
			ApiRecipe data = apiRecipeService.selectByRcpSeq(rcpSeq);
			model.addAttribute("data", data);	
			model.addAttribute("type", type);
			model.addAttribute("rcpSeq", rcpSeq);
			viewCountUp(rcpSeq, type, req, res);
			view = "thymeleaf/recipe/serverRecipeView";
		} else { // user recipe detail view
			Recipe data = recipeService.select(rcpSeq);
			UserInfo userId = data.getUserId();
			List<RecipeImage> dataimg = recipeService.imgselect(rcpSeq);
			List<RecipeReview> datareview = recipeService.reviewselect(rcpSeq);
			model.addAttribute("data", data);
			model.addAttribute("dataimg",dataimg);
			model.addAttribute("user", data.getUserId());
			model.addAttribute("review", datareview);
			
			//테스트용
			
			//게시물 내용
			System.out.println(data);
			//사용저 이름
			System.out.println(data.getUserId());
			//게시물 이미지
			System.out.println(dataimg);
			//게시물 리뷰
			System.out.println(datareview);
			view = "thymeleaf/recipe/recipeview";
		}
		return view;
	}

	// server recipe count
	@RequestMapping("/list/servercount")
	@ResponseBody
	public Long listCount(Model model) {
		long result = 0;
		result = apiRecipeService.countAll();
		return result;
	}

	@RequestMapping("/like/{type}/{rcpNo}")
	public String addWishList(@PathVariable String type, @PathVariable int rcpNo, HttpSession session,
			HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		UserInfo userinfo = (UserInfo) session.getAttribute("userInfo");
		if (type.equals("user")) {
			Recipe recipe = new Recipe();
			recipe.setRecipeNo(rcpNo);
			recipeService.wishList(userinfo, recipe);
		} else {
			ApiRecipe apirecipe = new ApiRecipe();
			apirecipe.setRcpSeq(rcpNo);
			apiRecipeService.wishList(userinfo, apirecipe);
		}
		return "redirect:" + referer;
	}
		
	@PostMapping("/reviewinsert/{rcpNo}")
	public String insertReview(@ModelAttribute RecipeReview recipereview, @PathVariable int rcpNo, HttpSession session,
	        HttpServletRequest request) {
	    String referer = request.getHeader("Referer");

	    // 세션에서 userInfo 가져오기
	    UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
	    Recipe recipe = recipeService.select(rcpNo); 
	    recipereview.setRecipeNo(recipe);
	    recipereview.setUserId(userInfo);
	    recipereview.setRecipeNo(recipe);
		recipeService.reviewsave(recipereview);
	    
	    System.out.println(recipereview);

	    return "redirect:" + referer;
	}
	
	// 조회수 올리는 메소드 (쿠키 기반)
	private void viewCountUp(int id, String type, HttpServletRequest req, HttpServletResponse res) {

		Cookie oldCookie = null;

		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(type)) {
					oldCookie = cookie;
				}
			}
		}
		if (oldCookie != null) {
			if (!oldCookie.getValue().contains("[" + Integer.toString(id) + "]")) {
				if (type.equals("server")) {
					apiRecipeService.viewCountUp(id);
				} else {
					recipeService.viewCountUp(id);
				}
				// boardService.viewCountUp(id);
				oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60 * 24);
				res.addCookie(oldCookie);
			}
		} else {
			// boardService.viewCountUp(id);
			if (type.equals("server")) {
				apiRecipeService.viewCountUp(id);
			} else {
				recipeService.viewCountUp(id);
			}
			Cookie newCookie = new Cookie(type, "[" + id + "]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60 * 24);
			res.addCookie(newCookie);
		}
	}

//	@RequestMapping("/list/server/{pageNo}")
//	@ResponseBody
//	public List<ApiRecipe> listApiRecipe(@PathVariable int pageNo) throws IOException {
//		List<ApiRecipe> list = apiRecipeService.getServerRecipe(pageNo, 9);
//		System.out.println("list : " + list);
//		return list;
//	}

	// DB저장용 평상시 사용 x
//	@RequestMapping("/setting/{firstIdx}/{lastIdx}")
//	public String setRecipeAPI(@PathVariable int firstIdx, @PathVariable int lastIdx) throws IOException {
//		recipeDataFetcher.fetchRecipeData(firstIdx, lastIdx);
//		return "thymeleaf/recipe/recipelist";
//	}

}
