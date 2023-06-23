package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.BoardComment;

public interface BoardCommentDAO {
	BoardComment insert(BoardComment boardComment);
	
	List<BoardComment> selectAll();
	
	long countAll();

	List<BoardDTO> selectComment(int commNo);

	int update(BoardComment boardComment);
	
	int delete(BoardComment boardComment);

	int updateGroupNo(BoardDTO boardDTO);

	int insertComment(BoardDTO boardDTO);
	
	List<BoardComment> selectByPageAndpagePerCount (int pageNo, int pagePerCount);
}
