package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	@RequestMapping("/list")
	public String boardList() {
		return "board/list";
	}
	@RequestMapping("/read")
	public String boardRead() {
		return "board/read";
	}
	@RequestMapping("/write")
	public String boardWrite() {
		return "board/write";
	}
	@RequestMapping("/reply")
	public String boardreply() {
		return "board/reply";
	}		
	
}
