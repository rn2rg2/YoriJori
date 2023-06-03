package com.yorijori.foodcode.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	@RequestMapping("/list")
	public String boardList() {

		return "thymeleaf/board/list";
	}
	@RequestMapping("/read")
	public String boardRead() {
		return "thymeleaf/layout/boardLayout";
	}
	@RequestMapping("/write")
	public String boardWrite() {
		return "thymeleaf/board/write";
	}	

	
}
