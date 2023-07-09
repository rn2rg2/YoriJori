package com.yorijori.foodcode.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.service.IngredientService;

@Controller
@RequestMapping("ingredient")
public class IngredientController {
	IngredientService ingredientService;
	FileUploadLogic fileUploadLogic;
	
	@Autowired
	public IngredientController(IngredientService ingredientService, FileUploadLogic fileUploadLogic) {
		super();
		this.ingredientService = ingredientService;
		this.fileUploadLogic = fileUploadLogic;
	}
	

	@RequestMapping("/list/{page}/{category}")
	public String getListByCategory(@PathVariable int page, @PathVariable String category,Model model) {
		long count = ingredientService.countAll();
		List<Ingredients> list = ingredientService.selectByPage(page, category);
		model.addAttribute("page", page);
		if (category.equals("all")) {
			model.addAttribute("count", count);
		} else {
			model.addAttribute("count", list.size());
		}
		model.addAttribute("list", list);
		model.addAttribute("category", category);
		return "thymeleaf/recipe/ingredient";
	}
	
	@RequestMapping("/list/{page}/{category}/{searchData}")
	public String getListByCategory(@PathVariable int page,@PathVariable String category, @PathVariable String searchData, Model model) {
		List<Ingredients> list = ingredientService.selectBySearch(page, category, searchData, 12);
		model.addAttribute("page", page);
		model.addAttribute("count", list.size());
		model.addAttribute("list", list);
		model.addAttribute("category", category);
		model.addAttribute("searchData", searchData);
		
		return "thymeleaf/recipe/ingredient";
	}
	@GetMapping("/insert")
	public String getInsertPage() {
		return "thymeleaf/recipe/ingredientInsert";
	}
	@PostMapping("/insert")
	public String InsertPage(Ingredients ingredients, @RequestParam("file") MultipartFile multipartFile) {
		System.out.println("==========================");
		System.out.println(ingredients);
		System.out.println("==========================");
		String fileRoot = fileUploadLogic.getUploadpath("ingredient/"); // 저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명
		File targetFile = new File(fileRoot + savedFileName);

		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			ingredients.setImgPath(savedFileName);
			ingredientService.insert(ingredients);
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			e.printStackTrace();
		}
		return "redirect:/ingredient/list/0/all";
	}
	
	@RequestMapping("/view/{matlNo}")
	public String getViewByMaltNo(@PathVariable int matlNo, Model model) {
		Ingredients data = ingredientService.selectByMatlNo(matlNo);
		model.addAttribute("data", data);
		return "thymeleaf/recipe/ingredientView";
	}

	@PostMapping("/getListByPage")
	@ResponseBody
	public List<Ingredients> getListByPage(int page, int pagePerCount) {
		List<Ingredients> list = ingredientService.selectByPagePerCount(page, pagePerCount);
		return list;
	}
	
    @PostMapping("/getListBySearchData")
	@ResponseBody
	public List<Ingredients> getListByPageAndSearchData(int page, int pagePerCount,String searchData) {
		List<Ingredients> list = ingredientService.selectBySearch(page, "all", searchData, pagePerCount);
		return list;
	}
	@PostMapping("/getListCount")
	@ResponseBody
	public long getListByPage(String searchData) {
		long count = ingredientService.countAllBySearchData(searchData);
		return count;
	}
}
 