package com.yorijori.foodcode.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.Board;
import com.yorijori.foodcode.jpa.entity.BoardComment;
import com.yorijori.foodcode.jpa.entity.SearchLog;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.service.BoardCommentService;
import com.yorijori.foodcode.service.BoardService;
import com.yorijori.foodcode.service.MemberService;
import com.yorijori.foodcode.service.SearchLogService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	BoardService service;
	BoardCommentService commentService;
	FileUploadLogic fileUploadLogic;
	SearchLogService searchservice;
	MemberService memberservice;
	
	@Autowired
	public BoardController(BoardService service, BoardCommentService commentService,FileUploadLogic fileUploadLogic, SearchLogService searchservice,MemberService memberservice) {
		super();
		this.service = service;
		this.commentService = commentService;
		this.fileUploadLogic = fileUploadLogic;
		this.searchservice = searchservice;
		this.memberservice = memberservice;

	}
	
//	//게시물 전체보기
//	@RequestMapping ("/list/{pageNo}")
//	public String boardList(Model model,@PathVariable String pageNo) {
//		List<Board> list = service.selectByPage(Integer.parseInt(pageNo));
//		long count = service.countAll();
//		model.addAttribute("boardlist",list);
//		model.addAttribute("count",count);
//		//System.out.println("aaaaaaaaaaaaaa"+list); 
//		return "thymeleaf/board/list";
//	}
	//게시물 전체보기
	/*
	 * @RequestMapping ("/list/{pageNo}/{pagePerCount}") public String
	 * boardList(Model model,@PathVariable int pageNo, @PathVariable int
	 * pagePerCount,
	 * 
	 * @RequestParam(value = "category", required = false) String category) {
	 * //List<Board> list = service.selectByPage(pageNo); List<Board> list =
	 * service.selectByPageAndpagePerCount(pageNo, pagePerCount); long count =
	 * service.countAll(); model.addAttribute("boardlist",list);
	 * model.addAttribute("count",count); model.addAttribute("pageNo",pageNo);
	 * model.addAttribute("pagePerCount",pagePerCount);
	 * 
	 * return "thymeleaf/board/list"; }
	 */
	
	@RequestMapping("/list/{pageNo}/{pagePerCount}")
	public String boardList(Model model, @PathVariable int pageNo, @PathVariable int pagePerCount,
							@RequestParam(value = "category", required = false) String category,
	                        @RequestParam(value = "searchData", required = false) String searchData) {
	    // List<Board> list = service.selectByPage(pageNo);
		List<Board> list = null;
	    long count = 0;
		
		if (category != null && !category.isEmpty()) {
			list = service.selectByCategoryAndState(category, pageNo, pagePerCount);
			count = service.getCountByCategorysAndState(category);
			
	    	
		    model.addAttribute("pageNo", pageNo);
		    model.addAttribute("pagePerCount", pagePerCount);
		    model.addAttribute("category", category);
		    model.addAttribute("boardlist", list);
			return "thymeleaf/board/list";
		}
	    
	    
	    if (searchData != null && !searchData.isEmpty()) {
	    	count = service.getCountByContentsAndState(searchData);
	    	list = service.selectByPageAndpagePerCountSearch(pageNo, pagePerCount, searchData);
	    	
	    	SearchLog searchlog = new SearchLog();
			searchlog.setKeyword(searchData);
			searchservice.insertLog(searchlog);
	    	
	    	model.addAttribute("boardlist", list);
		    model.addAttribute("count", count);
		    model.addAttribute("pageNo", pageNo);
		    model.addAttribute("pagePerCount", pagePerCount);
		    model.addAttribute("searchData", searchData);
			
			return "thymeleaf/board/list";
	    } 
	    
	    list = service.selectByPageAndpagePerCount(pageNo, pagePerCount);
		count = service.countAll();

		model.addAttribute("boardlist", list);
	    model.addAttribute("count", count);
	    model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pagePerCount", pagePerCount);
		
		return "thymeleaf/board/list";
	}

	/*
	 * @RequestMapping("/list/{pageNo}/{pagePerCount}/{contentKeyword}") public
	 * String boardSearchList(Model model, @PathVariable int pageNo, @PathVariable
	 * int pagePerCount,
	 * 
	 * @RequestParam(value = "category", required = false) String category,
	 * 
	 * @PathVariable String contentKeyword) { //System.out.println(contentKeyword);
	 * List<Board> list = service.selectByPageAndpagePerCountSearch(pageNo,
	 * pagePerCount, contentKeyword); long count =
	 * service.getCountByContentsAndState(contentKeyword);
	 * 
	 * //System.out.println("zzzzzzzzzz컨트롤러search"); model.addAttribute("boardlist",
	 * list); model.addAttribute("count", count); model.addAttribute("pageNo",
	 * pageNo); model.addAttribute("pagePerCount", pagePerCount);
	 * model.addAttribute("searchData", contentKeyword);
	 * model.addAttribute("category", category);
	 * 
	 * 
	 * return "thymeleaf/board/list"; }
	 * 
	 * @RequestMapping("/list/{pageNo}/{pagePerCount}/{category}") public String
	 * boardListCheckBox(Model model, @PathVariable int pageNo, @PathVariable int
	 * pagePerCount,
	 * 
	 * @RequestParam(value = "category", required = false) String category) {
	 * //System.out.println(category); List<Board> list =
	 * service.selectByCategoryAndState(category, pageNo, pagePerCount); long count
	 * = service.getCountByCategorysAndState(category);
	 * model.addAttribute("boardlist", list); model.addAttribute("count", count);
	 * model.addAttribute("pageNo", pageNo); model.addAttribute("pagePerCount",
	 * pagePerCount); model.addAttribute("category", category);
	 * 
	 * 
	 * return "thymeleaf/board/list"; }
	 */
	//게시물 상세보기
	@RequestMapping("/read/{commNo}/{state}")
	public String boardRead(@PathVariable int commNo, @PathVariable int state, HttpSession session, Model model,
							HttpServletRequest request, HttpServletResponse response) {
		service.bulletinBoardViews(commNo);
		Board board = service.select(commNo);
		//List<BoardComment> boardCommentList = commentService.selectByPageAndpagePerCount(pageNo, pagePerCount);
		//long count = commentService.countAll();
		List<BoardDTO> boardCommentList = commentService.selectComment(commNo);
		
		model.addAttribute("boardCommentList", boardCommentList);
		model.addAttribute("board", board);
		//model.addAttribute("count",count);
		//model.addAttribute("pageNo",pageNo);
		//viewCountValidation(commNo, board, request, response);
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
	
	//댓글 삭제
	@GetMapping("/boardCommentDelete")
	public String boardCommentDelete(BoardDTO boardDTO , int commentNo, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		commentService.delete(commentNo);
		//System.out.println("댓글삭제cccrrrrrrrrrrrr");
		return "redirect:"+ referer;
	}
	
	//댓글 전체 조회
	@RequestMapping("/boardCommentList")
	public String boardCommentList(BoardDTO boardDTO ,BoardComment boardComment,Model model,UserInfo user) {
		List<BoardDTO> boardCommentList = commentService.selectComment(boardDTO.getComm_no());
	     
		model.addAttribute("boardCommentList", boardCommentList);
		
		////System.out.println("commmmmmment"+boardCommentList);
		return "redirect:/board/read/" + boardComment.getCommNo() + "/" + boardComment.getState();
	}
	

	
	
	@GetMapping("/write")
	public String boardWrite(@RequestParam(value = "mode", required = false) String mode,
	                         @RequestParam(value = "commNo", required = false) Integer commNo,
	                         Model model, Board board, HttpSession session) {
		
	    UserInfo userId = (UserInfo) session.getAttribute("userInfo");
		if ("modify".equals(mode)) {
	        if (commNo != null) {
	            // 게시물 수정 모드일 때
	            Board existingBoard = service.select(commNo);
	            model.addAttribute("detail", existingBoard);
	            //System.out.println("cccccccccc");
	            //System.out.println(mode);
	        }
	    } else if ("add".equals(mode)) {
	        // 게시물 추가 모드일 때
	    	
	    	Board dto = new Board();
	    	dto.setUserId(userId);
	    	
	        model.addAttribute("detail", dto);
	    }

	    model.addAttribute("mode", mode);
	    return "thymeleaf/board/write";
	}

	@PostMapping("/writeAction")
	@ResponseBody
	public Board boardWriteSubmit(Board board
			, HttpSession session
			, Model model,  HttpServletResponse response) {
	    UserInfo user = (UserInfo) session.getAttribute("userInfo");
	    board.setUserId(user);
	    board.setView(0);
	    
	    //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	    //System.out.println(board.toString());
	    
		
	    if ("add".equals(board.getMode())) { 
	    	board = service.insert(board);
		  } else { 
			board = service.boardUpdate(board.getCommNo(), board); 
		}
	    
	    return board;
	
	}

	//게시글 삭제
	@GetMapping("/delete")
	public String delete(int commNo) {
		service.delete(commNo);
		return "redirect:/board/list/0/10";
	}
	
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		
		JsonObject jsonObject = new JsonObject();
		
		String fileRoot = fileUploadLogic.getUploadpath("summernoteimage/"); // 저장될 외부 파일 경로
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
		//System.out.println("ccccccccccccccontroller");
		//System.out.println(jsonObject.toString());
		String jsonvalue = jsonObject.toString();
		return jsonvalue;
	}
	// 조회수 올리는 메소드 (쿠키 기반)

	/*private void viewCountValidation(int commNo, Board board, HttpServletRequest request, HttpServletResponse response) {
	    boolean isVisited = false;
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("visit_cookie") && cookie.getValue().contains("[" + board.getUserId() + "]")) {
	                isVisited = true;
	                break;
	            }
	        }
	    }

	    if (!isVisited) {
	        service.bulletinBoardViews(commNo);
	        Cookie newCookie = new Cookie("visit_cookie", "[" + board.getUserId() + "]");
	        newCookie.setMaxAge(60 * 60 * 2);
	        newCookie.setPath("/");
	        response.addCookie(newCookie);
	    }*/
	//}
	
	@GetMapping("/ajax/list/{page}/{pagePerCount}")
	@ResponseBody 
	public List<Board> getListByUserId(@PathVariable int page,@PathVariable int pagePerCount, HttpSession session){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		List<Board> list = service.selectByPageByUser(page, pagePerCount, user);
		return list;
	}
	@GetMapping("/list/all/{page}/{pagePerCount}")
	@ResponseBody 
	public List<Board> getListAll(@PathVariable int page,@PathVariable int pagePerCount){
		List<Board> list = service.selectByPageAndpagePerCount(page, pagePerCount);
		return list;
	}
	@GetMapping("/list/delete/{page}/{pagePerCount}")
	@ResponseBody 
	public List<Board> getListDetele(@PathVariable int page,@PathVariable int pagePerCount){
		List<Board> list = service.selectByPageAndpagePerCount(page, pagePerCount,1);
		return list;
	}
	
	@GetMapping("/delete/count")
	@ResponseBody 
	public long getallCount(){
		long count = service.countByState(0);
		return count;
	}
	
	@GetMapping("/all/count")
	@ResponseBody 
	public long getDeleteCount(){
		long count =  service.countByState(1);
		return count;
	}
	
	@ResponseBody
	@PostMapping("/ajax/delete")
	public ResponseEntity deleteBoard(@RequestParam("commNo") int commNo) {
		service.delete(commNo);
		return ResponseEntity.ok().build();
	}
	
	@ResponseBody
	@PostMapping("/ajax/restore")
	public ResponseEntity restoreBoard(@RequestParam("commNo") int commNo) {
		Board board = new Board();
		board.setCommNo(commNo);
		service.update(board);
		return ResponseEntity.ok().build();
	}
	
	

}
