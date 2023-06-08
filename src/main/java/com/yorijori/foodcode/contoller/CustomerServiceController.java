package com.yorijori.foodcode.contoller;

import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/cs")
@Controller
public class CustomerServiceController {
	
	@RequestMapping("/help")
	public String showHelpList() {
		return "thymeleaf/customerService/helpList"; 
	}

	@RequestMapping("/inquiry") //
	public String showInquiry() {
		return "thymeleaf/customerService/inquiry"; 
	}
	@RequestMapping("/inquiry/form") //유저용
	public String showInquiryForm() {
		return "thymeleaf/customerService/inquiryForm_popup"; 
	}
	@RequestMapping("/notification")
	public String showNotificationList() {
		return "thymeleaf/customerService/notificationList"; 
	}
	@RequestMapping("/insert") //관리자용
	public String showNotificationInsert() {
		return "thymeleaf/customerService/notiInsert"; 
	}
	
	@RequestMapping("/test") //관리자용
	public String test() {
		return "thymeleaf/customerService/CSpageNavigation"; 
	}
}
