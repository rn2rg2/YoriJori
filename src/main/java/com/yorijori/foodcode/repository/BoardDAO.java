package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Board;

public interface BoardDAO {
	//게시물등록
	public Board insert(Board board);
	//게시물전체목록보기
	List<Board> selectAll();
	
	long countAll();
	
	List<Board> selectByPage(int pageNo);
	//게시물수정	
	public void update(Board board);
	//게시물삭제
	public void delete(int commNo);
	//검색
	Board select(int commNo);
	
	// 조회수 증가
	void bulletinBoardViews(int commNo);
	List<Board> selectByPageAndpagePerCount(int pageNo, int pagePerCount);		


}
