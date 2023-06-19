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
import com.yorijori.foodcode.jpa.entity.BoardComment;
import com.yorijori.foodcode.service.BoardCommentService;
import com.yorijori.foodcode.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	BoardService service;
	BoardCommentService commentService;
	
	@Autowired
	public BoardController(BoardService service, BoardCommentService commentService) {
		super();
		this.service = service;
		this.commentService = commentService;
	}
	@RequestMapping ("/list/{pageNo}")
	public String boardList(Model model,@PathVariable String pageNo) {
		List<Board> list =service.selectByPage(Integer.parseInt(pageNo));
		model.addAttribute("boardlist",list);
		System.out.println("aaaaaaaaaaaaaa"+list); 
		return "thymeleaf/board/list";
	}
	@RequestMapping("/read/{commNo}/{state}")
	public String boardRead(@PathVariable int commNo, @PathVariable int state, HttpSession session, Model model) {
		Board board = service.select(commNo);
		List<BoardComment> boardCommentList = commentService.selectComment(commNo);
		System.out.println("bbbbbbbbbbbbbbbbbbb"+boardCommentList);
		model.addAttribute("boardCommentList", boardCommentList);
		model.addAttribute("board", board);
		return "thymeleaf/layout/boardLayout";
	}
	
	@RequestMapping("/boardCommentInsert")
	public String boardCommentInsert(BoardComment boardComment, Model model) {
		commentService.insert(boardComment);
		return "redirect:/board/read/" + boardComment.getCommNo() + "/" + boardComment.getState();
	}
	
	@RequestMapping("/boardCommentList")
	public String boardCommentList(BoardComment boardComment,Model model) {
		List<BoardComment> boardCommentList = commentService.selectAll();
		model.addAttribute("boardCommentList", boardCommentList);
		System.out.println("commmmmmment"+boardCommentList);
		return "redirect:/board/read/" + boardComment.getCommNo() + "/" + boardComment.getState();
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
		return "redirect:/board/list/0";
	}
	
	
	
}
