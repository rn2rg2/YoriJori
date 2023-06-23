package com.yorijori.foodcode.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yorijori.foodcode.apidata.RecipeDataFetcher;
import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.dto.RecipeListDTO;
import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeIngredients;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ApiRecipeService;
import com.yorijori.foodcode.service.CategoryService;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.RecipeService;

@RequestMapping("/recipe")
@Controller
public class RecipeController {
	RecipeService recipeService;
	RecipeDataFetcher recipeDataFetcher;
	ApiRecipeService apiRecipeService;
	IngredientService ingredientservice;
	CategoryService categoryservice;
	FileUploadLogic fileuploadlogic;

	@Autowired
	public RecipeController(RecipeService recipeService, RecipeDataFetcher recipeDataFetcher,
			ApiRecipeService apiRecipeService, IngredientService ingredientservice, CategoryService categoryservice
			,FileUploadLogic fileuploadlogic) {
		super();
		this.recipeService = recipeService;
		this.recipeDataFetcher = recipeDataFetcher;
		this.apiRecipeService = apiRecipeService;
		this.ingredientservice = ingredientservice;
		this.categoryservice = categoryservice;
		this.fileuploadlogic = fileuploadlogic;
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
	public String insertRecipe(Model model) {
		List<Ingredients> list = ingredientservice.selectAll();
		List<Category> categorylist1 = categoryservice.findByUpperLevel("음식용도");
		List<Category> categorylist2 = categoryservice.findByUpperLevel("국가별");
		List<Category> categorylist3 = categoryservice.findByUpperLevel("조리법");
		List<Category> categorylist4 = categoryservice.findByUpperLevel("식품별");
		model.addAttribute("list", list);
		model.addAttribute("categorylist1", categorylist1);
		model.addAttribute("categorylist2", categorylist2);
		model.addAttribute("categorylist3", categorylist3);
		model.addAttribute("categorylist4", categorylist4);
		
		return "thymeleaf/recipe/recipeInsert";
	}
	
	@PostMapping("/insert")
	public String recipeInsert(Recipe recipedata, @RequestParam("recipethumbnail") MultipartFile multipartFile, @RequestParam(value="cookingList", required=true) List<MultipartFile> cookingList,
			HttpSession session) {
		UserInfo user = (UserInfo)session.getAttribute("userInfo");
		recipedata.setUserId(user);
		fileuploadlogic.createRecipeImageroot(recipedata.getImglist(), cookingList); // LIST 로 된 파일 처리 recipedata set 시켜주기
		String fileRoot = "C:\\project\\upload\\recipethumbnail\\";	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			String url ="/yorijori/data/recipethumbnail/"+savedFileName;
			recipedata.setThumbnail(url);
			System.out.println(recipedata);
			System.out.println(recipedata.getCategorylist());
			System.out.println(recipedata.getImglist());
			System.out.println(recipedata.getIngrelist());
			for(RecipeIngredients test : recipedata.getIngrelist()) {
				System.out.println(test.getMatlNo());
			}
			recipeService.insertAll(recipedata);
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			e.printStackTrace();
		}
			
		return "thymeleaf/recipe/recipelist";
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

	// recipe detail view
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
			model.addAttribute("data", data);
			
			model.addAttribute("user", data.getUserId());
			
			//테스트용
			//게시물 내용
			//출력
			System.out.println(data);
			//사용저 이름
			//출력
			System.out.println(data.getUserId());
			//게시물 이미지
			//출력
			System.out.println(dataimg);
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
