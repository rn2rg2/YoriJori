package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.BoardComment;

public interface BoardCommentDAO {
	BoardComment insert(BoardComment boardComment);
	List<BoardComment> selectAll();
	int update(BoardComment boardComment);
	int delete(BoardComment boardComment);
}
