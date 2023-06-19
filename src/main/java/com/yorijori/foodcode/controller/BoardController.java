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

import com.yorijori.foodcode.dto.BoardDTO;
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
	
	//게시물 전체보기
	@RequestMapping ("/list/{pageNo}")
	public String boardList(Model model,@PathVariable String pageNo) {
		List<Board> list = service.selectByPage(Integer.parseInt(pageNo));
		model.addAttribute("boardlist",list);
		System.out.println("aaaaaaaaaaaaaa"+list); 
		return "thymeleaf/board/list";
	}
	
	//게시물 상세보기
	@RequestMapping("/read/{commNo}/{state}")
	public String boardRead(@PathVariable int commNo, @PathVariable int state, HttpSession session, Model model) {
		Board board = service.select(commNo);
		List<BoardDTO> boardCommentList = commentService.selectComment(commNo);
		model.addAttribute("boardCommentList", boardCommentList);
		model.addAttribute("board", board);
		return "thymeleaf/layout/boardLayout";
	}
	
	//댓글 입력
	@RequestMapping("/boardCommentInsert")
	public String boardCommentInsert(BoardDTO boardDTO, Model model) {
		commentService.insertCommnet(boardDTO);
		boardDTO.setGroup_no(boardDTO.getComm_no());
		commentService.updateGroupNo(boardDTO);
		return "redirect:/board/read/" + boardDTO.getComm_no() + "/" + boardDTO.getState();
	}
	
	//댓글 전체 조회
	@RequestMapping("/boardCommentList")
	public String boardCommentList(BoardComment boardComment,Model model) {
		List<BoardDTO> boardCommentList = commentService.selectComment(boardComment.getCommNo());
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

	//게시글 삭제
	@GetMapping("/delete")
	public String delete(int commNo) {
		service.delete(commNo);
		return "redirect:/board/list/0";
	}
	
	
}
