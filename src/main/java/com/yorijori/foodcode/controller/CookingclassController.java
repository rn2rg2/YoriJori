package com.yorijori.foodcode.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;
import com.yorijori.foodcode.service.CookingClassService;
import com.yorijori.foodcode.service.MemberService;
@RequestMapping("/cookingclass")
@Controller
public class CookingclassController {
	
	CookingClassService service;
	MemberService memservice;
	FileUploadLogic fileUploadLogic;
	
	@Autowired
	public CookingclassController(CookingClassService service,FileUploadLogic fileUploadLogic) {
		super();
		this.service = service;
		this.fileUploadLogic = fileUploadLogic;
	}
	
	@RequestMapping("/Instructor")
	public String classListInstructor() {
		return "thymeleaf/cookingclass/classListInstructor";
	}
	
	@RequestMapping("/list") 
	public String showCookingclassList(Model model) {
		List<CookingClass> classList = service.selectAllClass();
		model.addAttribute("classList",classList);
		return "thymeleaf/cookingclass/classList";
	}
	
	@RequestMapping("/read")
	public String showCookingclass(Model model, int cookNo) {
		CookingClass cookingclass = service.readClass(cookNo);
		List<CookingClassContent> content = service.readContent(cookNo);
		List<CookingClassCurriculum> curriculum= service.readCurriculum(cookNo);
		model.addAttribute("cookingclass", cookingclass);
		model.addAttribute("contentList", content);
		model.addAttribute("curriculumList", curriculum);
		return "thymeleaf/cookingclass/classRead";
	}
	@RequestMapping("/delete")
	public String deleteClass(int cookNo){
		service.delete(cookNo);
		return "redirect:/cookingclass/list";
	}
	
	@RequestMapping("/in")
	public String showInsertCookingclass(HttpSession session) {
		return "thymeleaf/cookingclass/classInsert";
	}
	@PostMapping("/in")
	public String insertCookingclass(CookingClass cookingclass,CookingClassContent content,CookingClassCurriculum curriculum) {
		service.insert(cookingclass, content, curriculum);
		System.out.println(cookingclass);
		System.out.println(content);
		System.out.println(curriculum);
		return "redirect:/cookingclass/list";
	}
	
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		JsonObject json = new JsonObject();
		
		String fileRoot = "C:\\project\\upload\\summernoteimage\\";	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
				
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			json.addProperty("url", "/yorijori/data/summernoteimage/"+savedFileName);
			json.addProperty("responseCode", "success");
			
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			json.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String jsonvalue = json.toString();
		System.out.println("======================");
		System.out.println(jsonvalue);
		System.out.println("======================");
		return jsonvalue;
	}
}
