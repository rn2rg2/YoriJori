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

import com.yorijori.foodcode.jpa.entity.Board;
import com.yorijori.foodcode.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	BoardService service;
	@Autowired
	public BoardController(BoardService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping ("/list")
	public String boardList(Model model) {
		List<Board> list = service.selectAll();
		model.addAttribute("boardlist",list);
		System.out.println("aaaaaaaaaaaaaa"+list); 
		return "thymeleaf/board/list";
	}
	@RequestMapping("/read/{commNo}/{state}")
	public String boardRead(@PathVariable int commNo, @PathVariable int state, Model model) {
		Board board = service.select(commNo);
		model.addAttribute("board", board);
		return "thymeleaf/layout/boardLayout";
	}
	//게시판 글쓰기 view
	@GetMapping("/write")
	public String boardWrite(HttpSession session) {
		return "thymeleaf/board/write";
	}
	//게시판 글쓰기 기능
	@PostMapping("/write")
	public String boardwrite(Board board) {
		//System.out.println("Controller");
		//System.out.println(board.toString());
		service.insert(board);
		return "redirect:/board/list";
	}
	
}
