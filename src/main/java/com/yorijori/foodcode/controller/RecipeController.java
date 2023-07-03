package com.yorijori.foodcode.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yorijori.foodcode.apidata.RecipeDataFetcher;
import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.common.RecipeSpecification;
import com.yorijori.foodcode.dto.FilterDTO;
import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.ApiRecipeReview;
import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeCategory;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeIngredients;
import com.yorijori.foodcode.jpa.entity.RecipeQa;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.SearchLog;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.ApiRecipeService;
import com.yorijori.foodcode.service.CategoryService;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.RecipeService;
import com.yorijori.foodcode.service.SearchLogService;

@RequestMapping("/recipe")
@Controller
public class RecipeController {
	RecipeService recipeService;
	RecipeDataFetcher recipeDataFetcher;
	ApiRecipeService apiRecipeService;
	IngredientService ingredientservice;
	CategoryService categoryservice;
	FileUploadLogic fileuploadlogic;
	SearchLogService searchservice;

	@Value("${file.dir}") // c://project/upload
	private String uploadpath;

	@Autowired
	public RecipeController(RecipeService recipeService, RecipeDataFetcher recipeDataFetcher,
			ApiRecipeService apiRecipeService, IngredientService ingredientservice, CategoryService categoryservice,
			FileUploadLogic fileuploadlogic, SearchLogService searchservice) {
		super();
		this.recipeService = recipeService;
		this.recipeDataFetcher = recipeDataFetcher;
		this.apiRecipeService = apiRecipeService;
		this.ingredientservice = ingredientservice;
		this.categoryservice = categoryservice;
		this.fileuploadlogic = fileuploadlogic;
		this.searchservice = searchservice;
	}

	// 질문
	@RequestMapping("/QA/{type}/{rcpNo}")
	public String qaRecipeQ(@PathVariable String type, @PathVariable int rcpNo, HttpSession session,
			HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		UserInfo userinfo = (UserInfo) session.getAttribute("userInfo");
		return "thymeleaf/recipe/recicpeQA";
	}

	// 답변
	@RequestMapping("/QA/{title}/{type}/{rcpNo}")
	public String qaRecipeA(@PathVariable String title, @PathVariable String type, @PathVariable int rcpNo,
			HttpSession session, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		UserInfo userinfo = (UserInfo) session.getAttribute("userInfo");
		return "thymeleaf/recipe/recicpeQA";
	}

	// QAinsert
	@PostMapping("/QAinsert/{type}/{rcpNo}")
	public String qaRecipeinsertQ(@ModelAttribute RecipeQa recipeqa, @PathVariable String type, @PathVariable int rcpNo,
			HttpSession session, HttpServletRequest request) {
		UserInfo userinfo = (UserInfo) session.getAttribute("userInfo");
		Recipe recipe = new Recipe();
		String view = "thymeleaf/recipe/recipeqaexlt";
		if (type.equals("user")) {
			recipe.setRecipeNo(rcpNo);
			recipeqa.setUserId(userinfo);
			recipeqa.setRecipeNo(recipe);
			recipeqa.setDepthLevel(0);
			recipeqa.setState(1);
			recipeService.recipeqasave(recipeqa);
		} else { // 예외
			recipe.setRecipeNo(rcpNo);
			recipeqa.setUserId(userinfo);
			recipeqa.setRecipeNo(recipe);
			recipeqa.setDepthLevel(1);
			recipeqa.setState(1);
			recipeService.recipeqasave(recipeqa);
		}
		return view;
	}

	@RequestMapping("/view")
	public String viewRecipe(Model model) {
		return "thymeleaf/recipe/recipeview";
	}

	@PostMapping("/filtersearch")
	@ResponseBody
	public Map<String, Object> search(@RequestBody FilterDTO filterDTO) {
		System.out.println(filterDTO);

		Specification<Recipe> spec = RecipeSpecification.any();
		List<Recipe> recipes = recipeService.findAll(spec);

		// 중첩형 필터
		if (filterDTO.getServing() != null && !filterDTO.getServing().isEmpty()) {
			spec = spec.and(RecipeSpecification.equalServing(filterDTO.getServing()));
		}
		if (filterDTO.getMin() != null && !filterDTO.getMin().isEmpty()) {
			spec = spec.and(RecipeSpecification.equalmin(filterDTO.getMin()));
		}
		if (filterDTO.getOrder() != null && !filterDTO.getOrder().isEmpty()) {
			List<String> order = filterDTO.getOrder();
			if (order.contains("viewed")) {
				spec = spec.and(RecipeSpecification.orderByViewCount());
			}
			if (order.contains("great")) {
				spec = spec.and(RecipeSpecification.orderByViewCount());
			}
			if (order.contains("comments")) {
				spec = spec.and(RecipeSpecification.orderByWishlist());
			}
			if (order.contains("registration")) {
				spec = spec.and(RecipeSpecification.orderByCreatedDatetime());
			}
		}
		if (filterDTO.getCountry() != null && !filterDTO.getCountry().isEmpty()) {
			List<String> countryList = filterDTO.getCountry();
			for (String country : countryList) {
				spec = spec.and(RecipeSpecification.findByCountry(country));
			}
		}
		if (filterDTO.getRecipe() != null && !filterDTO.getRecipe().isEmpty()) {
			List<String> recipeList = filterDTO.getRecipe();
			for (String recipe : recipeList) {
				spec = spec.and(RecipeSpecification.findByRecipe(recipe));
			}
		}

		Map<String, Object> result = new HashMap<>();

		long count = recipeService.countAll();

		result.put("recipes", recipeService.findAll(spec));
		result.put("recipeCount", count);

		return result;
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
	public String recipeInsert(Recipe recipedata, @RequestParam("recipethumbnail") MultipartFile multipartFile,
			@RequestParam(value = "cookingList", required = true) List<MultipartFile> cookingList,
			HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		recipedata.setUserId(user);
		fileuploadlogic.createRecipeImageroot(recipedata.getImglist(), cookingList); // LIST 로 된 파일 처리 recipedata set
																						// 시켜주기
		String fileRoot = fileuploadlogic.getUploadpath("recipethumbnail/");// 저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자
		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명
		File targetFile = new File(fileRoot + savedFileName);

		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			recipedata.setThumbnail(savedFileName);
			System.out.println(recipedata);
			System.out.println(recipedata.getCategorylist());
			
			for (RecipeCategory test : recipedata.getCategorylist()) {
				System.out.println(test.getCategoryNo());
			}
			System.out.println(recipedata.getImglist());
			System.out.println(recipedata.getIngrelist());
			for (RecipeIngredients test : recipedata.getIngrelist()) {
				System.out.println(test.getMatlNo());
			}
			recipeService.insertAll(recipedata);
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			e.printStackTrace();
		}

		return "redirect:/recipe/list/user/0";
	}

	@RequestMapping("/list/{type}/{pageNo}")
	public String listRecipe(Model model, @PathVariable String type, @PathVariable int pageNo) throws IOException {
		if (type.equals("user")) { // user recipe
			// 전체 레시피 수 조회
			long count = recipeService.countAll();
			// 페이지에 해당하는 레시피 목록 조회
			List<Recipe> list = recipeService.selectListByPage(pageNo, 9);
			System.out.println(list);
			// 모델에 데이터 추가
			model.addAttribute("count", count);
			model.addAttribute("type", type);
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
		} else { // server recipea
			long count = apiRecipeService.countAll();
			// 페이지에 해당하는 서버 레시피 목록 조회
			List<ApiRecipe> list = apiRecipeService.getServerRecipe(pageNo, 9);
			// 모델에 데이터 추가
			model.addAttribute("count", count);
			model.addAttribute("type", type);
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
		}
		return "thymeleaf/recipe/recipelist";
	}

	// 레시피 뷰
	@RequestMapping("/view/{type}/{rcpSeq}")
	public String getViewPage(Model model, @PathVariable String type, @PathVariable int rcpSeq, HttpServletRequest req,
			HttpServletResponse res) {
		// 뷰 설정
		String view = "";

		Recipe recipe = new Recipe();
		if (type.equals("server")) { // 서버 레시피 detail view
			// 서버 레시피의 데이터 조회
			ApiRecipe data = apiRecipeService.selectByRcpSeq(rcpSeq);
			List <ApiRecipeReview> review = apiRecipeService.findByRcpSeq(rcpSeq);


			// 평균값 계산
			BigDecimal sum = BigDecimal.ZERO;
			for (ApiRecipeReview datareview : review) {
			    sum = sum.add(datareview.getStar());
			}

			// 평균값 계산
			BigDecimal average;
			if (review.size() > 0) {
			    BigDecimal size = BigDecimal.valueOf(review.size());
			    average = sum.divide(size, 2, RoundingMode.HALF_UP);
			} else {
			    average = BigDecimal.ZERO;
			}

			
			System.out.println(review);
			// 모델에 데이터 추가
			model.addAttribute("review_average", average);
			model.addAttribute("data", data);
			model.addAttribute("type", type);
			model.addAttribute("rcpSeq", rcpSeq);
			model.addAttribute("review",review);
			// 조회수 증가
			viewCountUp(rcpSeq, type, req, res);
			// 뷰 페이지 설정
			view = "thymeleaf/recipe/serverRecipeView";
			} else { // user recipe detail view
				// 사용자 레시피의 데이터 조회
				Recipe data = recipeService.select(rcpSeq);
				// 사용자 정보와 관련된 데이터 조회
				UserInfo userId = data.getUserId();
				List<RecipeImage> dataimg = recipeService.imgselect(rcpSeq);
				List<RecipeReview> datareview = recipeService.reviewselect(rcpSeq);
				// 질문
				List<RecipeQa> dataq = recipeService.QAselect(rcpSeq);
				List<RecipeQa> depthLevelZeroList = new ArrayList<>();
				List<RecipeQa> depthLevelOneList = new ArrayList<>();
				List<RecipeIngredients> ingr = recipeService.selectingr(rcpSeq);
				List<Ingredients> idList = new ArrayList<>();
	
				for (RecipeIngredients recipeIngredients : ingr) {
					Ingredients ingr2 = ingredientservice.selectByMatlNo(recipeIngredients.getMatlNo());
					idList.add(ingr2);
				}
				// 필터링 질문자 0과 1
				// 이유 --> 타임리프로 depthLevel == 1 해도 0과1이 똑같이 출력되서 구분
				for (RecipeQa item : dataq) {
					if (item.getDepthLevel() == 0) {
						depthLevelZeroList.add(item);
						System.out.println(item);
					} else if (item.getDepthLevel() == 1) {
						depthLevelOneList.add(item);
						System.out.println(item);
	
					}
				}
				
				
				BigDecimal sum = BigDecimal.ZERO;
				for (RecipeReview review : datareview) {
				    sum = sum.add(review.getStar());
				}

				// 평균값 계산
				BigDecimal average;
				if (datareview.size() > 0) {
				    BigDecimal size = BigDecimal.valueOf(datareview.size());
				    average = sum.divide(size, 2, RoundingMode.HALF_UP);
				} else {
				    average = BigDecimal.ZERO;
				}

				// 모델에 데이터 추가
				// 게시물 상세내용
				model.addAttribute("data", data);
				// 게시물 이미지 및 레시피방법
				model.addAttribute("dataimg", dataimg);
				// 사용저 이름
				model.addAttribute("user", data.getUserId());
				// 게시물 리뷰
				model.addAttribute("review", datareview);
				model.addAttribute("reviewcount", datareview.size());
				
				model.addAttribute("review_average", average);
				model.addAttribute("dataq", depthLevelZeroList);
				model.addAttribute("dataa", depthLevelOneList);
				model.addAttribute("rcpSeq", rcpSeq);
				model.addAttribute("ingrList", idList);
				System.out.println("TESTTEST" + dataimg);
	
				viewCountUp(rcpSeq, type, req, res);
	
				view = "thymeleaf/recipe/userRecipeView";
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
			// 사용자 레시피의 경우
			Recipe recipe = new Recipe();
			recipe.setRecipeNo(rcpNo);
			recipeService.wishList(userinfo, recipe);
		} else {
			// 서버 레시피의 경우
			ApiRecipe apirecipe = new ApiRecipe();
			apirecipe.setRcpSeq(rcpNo);
			apiRecipeService.wishList(userinfo, apirecipe);
		}
		return "redirect:" + referer;
	}

//	@PostMapping("/reviewinsert/{rcpNo}")
//	public String insertReview(@ModelAttribute RecipeReview recipereview, @PathVariable int rcpNo, HttpSession session,
//	        HttpServletRequest request) {
//		//세션처리
//	    String referer = request.getHeader("Referer");
//	    // 세션에서 userInfo 가져오기
//	    UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
//	    Recipe recipe = new Recipe();
//	    //@PathVariable로 받아온 레시피번호로 재조회후 정보 가져오기
//	    recipe.setRecipeNo(rcpNo);
//	    //정보 넣어주기
//	    recipereview.setRecipeNo(recipe);
//	    recipereview.setUserId(userInfo);
//	    //저정한 정보 insert
//		recipeService.reviewsave(recipereview);
//		// 리턴
//	    return "redirect:" + referer;
//	}
	@PostMapping("/reviewinsert/{type}/{rcpNo}")
	public String insertReview(@ModelAttribute RecipeReview recipereview,@ModelAttribute ApiRecipeReview APIrecipereview, @PathVariable String type,@PathVariable int rcpNo, HttpSession session,
			HttpServletRequest request) {
		// 세션처리
		String referer = request.getHeader("Referer");
		// 세션에서 userInfo 가져오기
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Recipe recipe = new Recipe();
		ApiRecipe apirecipe = new ApiRecipe();
		String view = "redirect:" + referer;
		// If userInfo is null, redirect to login page
		if(userInfo == null) {
			view = "yorijori/member/loginpage";
		}else {
			if(type.equals("user")) {
				recipe.setRecipeNo(rcpNo);
				// 정보 넣어주기
				recipereview.setRecipeNo(recipe);
				recipereview.setUserId(userInfo);
				// 저정한 정보 insert
				recipeService.reviewsave(recipereview);
				
			}if(type.equals("server")) {
				apirecipe.setRcpSeq(rcpNo);
				System.out.println("test");
				APIrecipereview.setState(1);
				APIrecipereview.setRcpSeq(apirecipe);
				APIrecipereview.setUserId(userInfo);
				apiRecipeService.reviewsave(APIrecipereview);
				System.out.println("서버");
			}
		}

		
		return view;

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

	@RequestMapping("/search/{data}/{pageNo}")
	public String searchRecipe(@PathVariable String data, @PathVariable int pageNo, Model model) {
		long count = recipeService.countByNameContaining(data);
		List<Recipe> list = recipeService.selectBySearch(pageNo, data, 9);
		System.out.println(list);
		SearchLog searchlog = new SearchLog();
		searchlog.setKeyword(data);
		searchservice.insertLog(searchlog);

		// 모델에 데이터 추가
		model.addAttribute("count", count);
		model.addAttribute("type", "user");
		model.addAttribute("list", list);
		model.addAttribute("pageNo", pageNo);
		return "thymeleaf/recipe/recipelist";
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
