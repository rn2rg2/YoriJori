package com.yorijori.foodcode.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorijori.foodcode.jpa.entity.Board;
import com.yorijori.foodcode.repository.BoardDAO;
@Transactional
@Service
public class BoardServiceImpl implements BoardService {
	BoardDAO dao;
	
	@Autowired
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}
	@Override
	public Board select(int commNo) {
		return dao.select(commNo);
	}
	
	@Override
	public List<Board> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public List<Board> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Board> search(String tag, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Board> findByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board insert(Board board) {
		// TODO Auto-generated method stub
		//System.out.println("Serivce");
		//System.out.println(board.toString());
		return dao.insert(board);
	}
	@Override
	public long countAll() {
		return dao.countAll();
	}
	@Override
	public List<Board> selectByPage(int pageNo) {
		// TODO Auto-generated method stub
		return dao.selectByPage(pageNo);
	}
	@Override
	public List<Board> selectByPageAndpagePerCount(int pageNo,  int pagePerCount) {
	
		return dao.selectByPageAndpagePerCount(pageNo, pagePerCount);
	}
	@Override
	public void update(Board board) {
		dao.update(board);
	}
	@Override
	public void delete(int commNo) {
		dao.delete(commNo);
	}
	
	@Override
	public void bulletinBoardViews(int commNo) {
		dao.bulletinBoardViews(commNo);
		System.out.println("service count");
		//board.viewCountUp();
	}

	
}
