package com.yorijori.foodcode.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yorijori.foodcode.jpa.entity.Board;

public interface BoardService {
	public Board insert(Board board);
	List<Board> selectAll();
	void update(Board board);
	void delete(int commNo);
	long countAll();
	List<Board> search();
	List<Board> search(String tag,String data);
	Page<Board> searchingBoardList(String keyword, Pageable pageable);
	List<Board> findByCategory(String category);
	Board select(int commNo);		
	List<Board> selectByPage(int pageNo);
	void bulletinBoardViews(int commNo);
	List<Board> selectByPageAndpagePerCount(int pageNo, int pagePerCount);		
	List<Board> selectByPageAndpagePerCountSearch(int pageNo, int pagePerCount,String contentKeyword);
	public long getCountByContentsAndState(String content);
	List<Board> selectByCategoryAndState(String category,int pageNo, int pagePerCount);
	public long getCountByCategorysAndState(String category);
	
	



}
