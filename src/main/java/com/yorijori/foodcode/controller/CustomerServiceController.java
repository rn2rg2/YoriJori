package com.yorijori.foodcode.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.jpa.entity.Inquiry;
import com.yorijori.foodcode.jpa.entity.InquiryComment;
import com.yorijori.foodcode.jpa.entity.Notice;
import com.yorijori.foodcode.jpa.entity.Question;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.CustomerServiceService;

@RequestMapping("/cs")
@Controller
public class CustomerServiceController {
	
	CustomerServiceService service;
	FileUploadLogic fileUploadLogic;

	@Autowired
	public CustomerServiceController(CustomerServiceService service, FileUploadLogic fileUploadLogic) {
		super();
		this.service = service;
		this.fileUploadLogic = fileUploadLogic;
	}

	@RequestMapping("/notice/{pageNo}/{pagePerCount}")
	public String showNotificationList(Model model, @PathVariable int pageNo, @PathVariable int pagePerCount) {
		List <Notice> noticeList = service.selectByPageAndpagePerCount(pageNo, pagePerCount);
		long count = service.noticeCountAll();
		
		model.addAttribute("count", count);
		model.addAttribute("noticeList",noticeList);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("pagePerCount", pagePerCount);

		
		return "thymeleaf/customerService/notificationList"; 
	}
	//공지사항
	@GetMapping("/notice/insert")
	public String noticeInsert(HttpSession session) {
		return "thymeleaf/customerService/notiInsert"; 

	}
	@PostMapping("/notice/insert") //관리자용
	public String notificationInsert(Notice notice, HttpSession session) {
		//System.out.println("connnnnnnntroller");
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		notice.setUserId(user); 
		service.noticeInsert(notice);
		return "redirect:/cs/notice/0/10"; 
	}
	@GetMapping("/notice/delete")
	public String noticeDelete(int noticeNo) {
		service.noticeDelete(noticeNo);
		return "redirect:/cs/notice/0/10";
	}
	
	//자주묻는질문


	@RequestMapping("/help/{pageNo}/{pagePerCount}")
	public String showHelpList(Model model, @PathVariable int pageNo, @PathVariable int pagePerCount) {
		List <Question> questionList = service.questionSelectByPageAndpagePerCount(pageNo, pagePerCount);
		long count = service.questionCountAll();
		
		model.addAttribute("count", count);
		model.addAttribute("questionList",questionList);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("pagePerCount", pagePerCount);

		return "thymeleaf/customerService/helpList"; 
	}
	
	@GetMapping("/help/insert")
	public String showHelpList() {
		return "thymeleaf/customerService/helpInsert"; 
	}
	
	@PostMapping("/help/insert") 
	public String HelpInsert(Question question, HttpSession session) {
		//System.out.println("connnnnnnntroller");
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		question.setUserId(user); 
		service.questionInsert(question);
		return "redirect:/cs/help/0/10"; 
	}
	
	@GetMapping("/help/delete")
	public String helpDelete(int questionNo) {
		service.questionDelete(questionNo);
		return "redirect:/cs/help/0/10";
	}
	

	//문의사항
	/*
	 * @RequestMapping("/inquiry") public String showInquiry() { return
	 * "thymeleaf/customerService/inquiry"; }
	 */
	
	@GetMapping("/inquiry/insert") //유저용
	public String showInquiryForm() {
		return "thymeleaf/customerService/inquiryForm_popup"; 
	}
	
	
	@PostMapping("/inquiry/insert") 
	public String inquiryInsert(Inquiry inquiry, HttpSession session) { 
		UserInfo user = (UserInfo) session.getAttribute("userInfo"); 
		inquiry.setUserId(user);
		//System.out.println("iiiiiiiiiinqco"); 
		service.inquiryInsert(inquiry);
	    return "redirect:/cs/inquiry"; // 문의 성공 시 success 파라미터를 전달하여 리다이렉트합니다

	}
	

	@RequestMapping("/inquiry")
	public String showinquiryList(Model model,HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo"); 
		String role = user.getRole();
		
		 if ("관리자".equals(role)) {
		        // 관리자 동작 수행
		        List<Inquiry> inquiryList = service.getAllInquiries();
		        model.addAttribute("inquiryList", inquiryList);
		        return "thymeleaf/customerService/inquiry"; 
		    } else {
		        // 사용자 동작 수행
		        List<Inquiry> inquiryList = service.findByUserId(user);
		        model.addAttribute("inquiryList", inquiryList);
		        return "thymeleaf/customerService/inquiry"; 
		    }
	}
	
   

	
	@RequestMapping("/inquiry/read/{inquiryNo}")
	public String inquiryRead(@PathVariable int inquiryNo, Model model,HttpSession session) {
		Inquiry inquiry = service.select(inquiryNo);
		List<InquiryComment> commentList = service.getInquiryCommentsByState(inquiryNo, 0);
		model.addAttribute("inquiry",inquiry);
		model.addAttribute("commentList",commentList);

		return "thymeleaf/customerService/inquiryRead"; 
	}
	
	@GetMapping("/inquiry/delete")
	public String inquiryDelete(int inquiryNo) {
		service.inquiryDelete(inquiryNo);
		return "redirect:/cs/inquiryNo/list";
	}
	
	//문의사항 댓글

	
	@PostMapping("/inquiryComment/insert") 
	public String inquiryCommentInsert( InquiryComment inquiryComment, HttpSession session) { 
		UserInfo user = (UserInfo) session.getAttribute("userInfo"); 
		inquiryComment.setUserId(user);
		System.out.println(inquiryComment); 
		System.out.println(inquiryComment.getInquiryNo()); 
		service.inquiryCommentInsert(inquiryComment);
		String url = "redirect:/cs/inquiry/read/"+inquiryComment.getInquiryNo();
	    return url;

	}
	@GetMapping("/inquiryComment/delete")
	public String inquiryCommentDelete(int id) {
		InquiryComment inquiryComment= service.inquiryCommentDelete(id);
		System.out.println(inquiryComment.getId());
		System.out.println(inquiryComment.getInquiryNo());

		return "redirect:/cs/inquiry/read/"+inquiryComment.getInquiryNo();
	}	
	
	@RequestMapping("/test") //관리자용
	public String test() {
		return "thymeleaf/customerService/CSpageNavigation"; 
	}
}
