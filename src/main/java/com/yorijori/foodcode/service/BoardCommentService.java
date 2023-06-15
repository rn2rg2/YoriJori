package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.BoardComment;

public interface BoardCommentService {
	BoardComment insert(BoardComment boardComment);
	List<BoardComment> selectAll();
	int update(BoardComment boardComment);
	int delete(BoardComment boardComment);
}
