package com.yorijori.foodcode.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorijori.foodcode.jpa.entity.Board;
import com.yorijori.foodcode.jpa.entity.UserInfo;
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
	public long countByState(int state) {
		return dao.countByState(state);
	}
	@Override
	public long countByUserId(UserInfo user) {
		return dao.countByUserId(user);
	}
	@Override
	public List<Board> selectByPage(int pageNo) {
		// TODO Auto-generated method stub
		return dao.selectByPage(pageNo);
	}
	@Override
	public List<Board> selectByPageAndpagePerCount(int pageNo, int pagePerCount) {
	
		return dao.selectByPageAndpagePerCount(pageNo, pagePerCount);
	}
	
	@Override
	public List<Board> selectByPageAndpagePerCount(int pageNo, int pagePerCount,int state) {
	
		return dao.selectByPageAndpagePerCount(pageNo, pagePerCount,state);
	}


	@Override
	public int boardUpdate(int commNo, Board board) {
		return dao.boardUpdate(commNo, board);
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
	@Override
	public Page<Board> searchingBoardList(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Board> selectByPageAndpagePerCountSearch(int pageNo, int pagePerCount,String contentKeyword) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!searchservice");
		return dao.selectByPageAndpagePerCountandSearch(pageNo, pagePerCount, contentKeyword);
	}
	@Override
	public long getCountByContentsAndState(String content) {
		return dao.getCountByContentsAndState(content);
	}
	@Override
	public List<Board> findByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Board> selectByCategoryAndState(String category, int pageNo, int pagePerCount) {
		// TODO Auto-generated method stub
		System.out.println("ssssssservice category");
		return dao.selectByCategoryAndState(category, pageNo, pagePerCount);
	}
	@Override
	public long getCountByCategorysAndState(String category) {
		// TODO Auto-generated method stub
		return dao.getCountByCategorysAndState(category);
	}
	@Override

	public List<Board> findmyboardlist(UserInfo user) {
		// TODO Auto-generated method stub
		return dao.findmyboardlist(user);
	}
	
	@Override
	public List<Board> selectByPageByUser(int pageNo, int pagePerCount,UserInfo user){
		return dao.selectByPageByUser(pageNo, pagePerCount, user);
	}
	@Override
	public List<Long> countBycommNo(int startnum, int endnum) {
		List<Long> list = new ArrayList<Long>();
		for (int i = startnum; i <= endnum; i++) {
			Board commNo = new Board();
			commNo.setCommNo(i);
			long count = dao.countBycommNo(commNo);
			list.add(count);
		}
		return list;
	}

	@Override
	public void update(Board board) {
		dao.update(board);
	}

	
}
