package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	//member로 옮길 예정!!
	@RequestMapping("/register")
	public String register() {
		return "member/register";
	}
	@RequestMapping("/boardlist")
	public String boardList() {
		return "board/boardList";
	}
	@RequestMapping("/boardread")
	public String boardRead() {
		return "board/boardRead";
	}
	@RequestMapping("/boardwrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	@RequestMapping("/boardreply")
	public String boardreply() {
		return "board/boardReply";
	}		
}
