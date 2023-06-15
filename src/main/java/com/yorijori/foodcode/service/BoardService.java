package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.Board;

public interface BoardService {
	public Board insert(Board board);
	List<Board> selectAll();
	int update(BoardDTO board);
	int delete(BoardDTO board);
	List<Board> search();
	List<Board> search(String tag,String data);
	List<Board> findByCategory(String category);		
}
