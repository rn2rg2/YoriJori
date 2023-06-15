package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.Board;

public interface BoardService {
	public Board insert(Board board);
	List<BoardDTO> selectAll();
	int update(BoardDTO board);
	int delete(BoardDTO board);
	List<BoardDTO> search();
	List<BoardDTO> search(String tag,String data);
	List<BoardDTO> findByCategory(String category);		
}
