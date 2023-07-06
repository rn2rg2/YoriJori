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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassForm;
import com.yorijori.foodcode.jpa.entity.SearchLog;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.CookingClassService;
import com.yorijori.foodcode.service.MemberService;
import com.yorijori.foodcode.service.SearchLogService;

@RequestMapping("/cookingclass")
@Controller
public class CookingclassController {

	CookingClassService service;
	MemberService memservice;
	FileUploadLogic fileUploadLogic;
	SearchLogService searchservice;

	@Autowired
	public CookingclassController(CookingClassService service, MemberService memservice,
			FileUploadLogic fileUploadLogic, SearchLogService searchservice) {
		super();
		this.service = service;
		this.memservice = memservice;
		this.fileUploadLogic = fileUploadLogic;
		this.searchservice=searchservice;
	}

	@RequestMapping("/Instructor/{pageNo}/{pagePerCount}")
	public String classListInstructor(Model model, @PathVariable int pageNo, @PathVariable int pagePerCount) {
		List<UserInfo> list = memservice.getCookUser(pageNo, pagePerCount, "point");
		long count = memservice.userCount("전문가");
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pagePerCount", pagePerCount);
		return "thymeleaf/cookingclass/classListInstructor";
	}

	

	@RequestMapping("/read")
	public String showCookingclass(Model model, int cookNo) {
		CookingClass cookingclass = service.readClass(cookNo);
		List<CookingClass> otherclass = service.findByUserId(cookingclass.getUserId());

		model.addAttribute("cookingclass", cookingclass);
		model.addAttribute("otherclass", otherclass);
		return "thymeleaf/cookingclass/classRead";
	}

	@RequestMapping("/delete")
	public String deleteClass(int cookNo) {
		service.delete(cookNo);
		return "redirect:/cookingclass/list/0/6";
	}

	@RequestMapping("/application")
	public String applicateClass(int cookNo, HttpSession session, Model model) {
		if (session.getAttribute("userInfo") == null) {
			return "redirect:/main";
		} else {
			CookingClass cookingClass = service.readClass(cookNo);
			model.addAttribute("cookingClass", cookingClass);
		}
		return "thymeleaf/cookingclass/classApplicationForm";
	}

	@PostMapping("/application")
	public String applicationClass(CookingClassForm form, Integer classNo, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		form.setUserId(user);
		form.setCookNo(service.findById(classNo));
		System.out.println("\n\n\n\n\n\n*************c");
		System.out.println(form);
		System.out.println(form.getUserId());
		System.out.println(form.getCookNo());
		System.out.println(form.getPayment());
		System.out.println("*************\n\n\n\n\n\n");
		service.formInsert(form);
		return "redirect:/cookingclass/list";
	}

	@RequestMapping("/in")
	public String showInsertCookingclass(HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user.getRole().equals("회원")) {
			return "redirect:/main";
		}
		return "thymeleaf/cookingclass/classInsert";
	}
//	@RequestMapping("/upload")
//	public String showPopup() {
//		return "thymeleaf/cookingclass/uploadForm";
//	}

	@PostMapping("/in")
	public String insertCookingclass(CookingClass cookingclass, @RequestParam("file") MultipartFile multipartFile) {

		JsonObject json = new JsonObject();
		System.out.println(cookingclass.getContentList().get(0).getContent());
		String fileRoot = fileUploadLogic.getUploadpath("thumbnail/"); // 저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명
		File targetFile = new File(fileRoot + savedFileName);

		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			String url = "/yorijori/data/thumbnail/" + savedFileName;
			cookingclass.setThumbnail(url);
//			System.out.println("url이름: "+url);
//			System.out.println(cookingclass);
//			System.out.println(cookingclass.getContentList().get(1));
//			System.out.println(cookingclass.getCurriList().get(0));
			service.insert(cookingclass);
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			e.printStackTrace();
		}
		return "redirect:/cookingclass/list/0/6";
	}

	@PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		JsonObject json = new JsonObject();

		String fileRoot = fileUploadLogic.getUploadpath("summernoteimage/"); // 저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명
		File targetFile = new File(fileRoot + savedFileName);

		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			json.addProperty("url", "/yorijori/data/summernoteimage/" + savedFileName);
			json.addProperty("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			json.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String jsonvalue = json.toString();
		System.out.println("======================");
		System.out.println(jsonvalue);
		System.out.println("======================");
		return jsonvalue;
	}
	@RequestMapping("/list/{pageNo}/{pagePerCount}")
	public String showCookingclassList(Model model, @PathVariable int pageNo, @PathVariable int pagePerCount) {
		List<CookingClass> classList = service.selectByPageAndpagePerCount(pageNo, pagePerCount);
		List<CookingClass> top5class = service.findTop5ByOrderByCount();
		model.addAttribute("classList", classList);
		model.addAttribute("topclasslist", top5class);

		// 페이징
		long count = service.countAll();
		model.addAttribute("count", count);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pagePerCount", pagePerCount);

		return "thymeleaf/cookingclass/classList";
	}
	@RequestMapping("/search/{data}/{pageNo}")
	public String search(@PathVariable String data, @PathVariable int pageNo, Model model) {
		long count =service.countByTitleContaining(data);
		List<CookingClass> list = service.selectBySearch(pageNo, data, 6);
		System.out.println("\n\n\n"+list+"\n\n\n");
		SearchLog searchlog = new SearchLog();
		searchlog.setKeyword(data);
		searchservice.insertLog(searchlog);
		List<CookingClass> top5class = service.findTop5ByOrderByCount();
		model.addAttribute("topclasslist", top5class);
		
		model.addAttribute("count", count);
		model.addAttribute("classList", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pagePerCount", 6);
		return "thymeleaf/cookingclass/classList";
	}
	@PostMapping("/deleteFile")
	public String deleteSummernoteImageFile() {
		return null;
	}

}
