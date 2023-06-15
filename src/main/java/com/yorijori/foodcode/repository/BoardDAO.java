package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.Board;

public interface BoardDAO {
	//게시물등록
	public Board insert(Board board);
	//게시물전체목록보기
	List<BoardDTO> selectAll();
	//게시물수정
	int update(BoardDTO board);
	//게시물삭제
	int delete(BoardDTO board);
	//검색
	List<BoardDTO> search(String tag,String data);
	//카테고리검색
	List<BoardDTO> findByCategory(String category);		
}
