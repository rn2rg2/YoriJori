package com.yorijori.foodcode.controller;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
	FileUploadLogic fileUploadLogic;
	
	@Autowired
	public BoardController(BoardService service, BoardCommentService commentService,FileUploadLogic fileUploadLogic) {
		super();
		this.service = service;
		this.commentService = commentService;
		this.fileUploadLogic = fileUploadLogic;
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
		service.bulletinBoardViews(commNo);
		Board board = service.select(commNo);
		List<BoardDTO> boardCommentList = commentService.selectComment(commNo);
		model.addAttribute("boardCommentList", boardCommentList);
		model.addAttribute("board", board);
		return "thymeleaf/layout/boardLayout";
	}
	
	//댓글 입력
	@RequestMapping("/boardCommentInsert")
	public String boardCommentInsert(BoardDTO boardDTO, Model model) {
		commentService.insertComment(boardDTO);
		if (boardDTO.getIs_first_comment().equals("Y")) {
			boardDTO.setGroup_no(boardDTO.getComment_no());
		} else {
			boardDTO.setGroup_no(boardDTO.getParents_comment_no());
		}
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
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		
		JsonObject jsonObject = new JsonObject();
		
		String fileRoot = "C:\\project\\upload\\summernoteimage\\";	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
				
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/yorijori/data/summernoteimage/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		System.out.println("ccccccccccccccontroller");
		System.out.println(jsonObject.toString());
		return jsonObject;
	}
	
}
