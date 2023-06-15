package com.yorijori.foodcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorijori.foodcode.dto.BoardDTO;
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
	public List<Board> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public int update(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
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
	
}
