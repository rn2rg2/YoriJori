package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Board;

public interface BoardService {
	public Board insert(Board board);
	List<Board> selectAll();

	int update(Board board);
	int delete(Board board);

	List<Board> search();
	List<Board> search(String tag,String data);
	List<Board> findByCategory(String category);		
}
