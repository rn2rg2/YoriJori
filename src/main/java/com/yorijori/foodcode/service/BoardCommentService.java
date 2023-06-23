package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.BoardComment;

public interface BoardCommentService {
	BoardComment insert(BoardComment boardComment);
	List<BoardComment> selectAll();
	List<BoardDTO> selectComment(int commNo);
	int update(BoardComment boardComment);
	long countAll();
	void delete(int commentNo);
	List<BoardComment> selectByPageAndpagePerCount(int pageNo, int pagePerCount);
	int updateGroupNo(BoardDTO boardDTO);
	int insertComment(BoardDTO boardDTO);
}
