package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


import com.yorijori.foodcode.jpa.entity.Board;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.BoardRepository;

@Repository
public class BoardDAOImpl implements BoardDAO {
	private BoardRepository repository;
	
	@Autowired
	public BoardDAOImpl(BoardRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Board insert(Board board) {
		//System.out.println("DAO");
		//System.out.println(board.toString());

		return repository.save(board);
	}

	@Override
	public Board select(int commNo) {
		return repository.findByCommNo(commNo);
	}
	@Override
	public List<Board> selectAll() {
		//return repository.findByState(0);
		return repository.findAll(Sort.by(Sort.Direction.DESC, "commNo"));
		
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		Board dto =repository.findById(board.getCommNo()).get();
		dto.setState(0);
		
	}

	@Override
	public long countAll() {
		return repository.count(); 
	}
	@Override
	public long countByState(int state) {
		return repository.countByState(state); 
	}
	
	@Override
	public long countByUserId(UserInfo user) {
		return repository.countByUserId(user); 
	}

	@Override
	public List<Board> selectByPage(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 10, Sort.by(Sort.Direction.DESC,"commNo"));								
		Page<Board> page = repository.findAll(pageRequest);
		List<Board> list = page.getContent();
		return list;
	}
	
	

	@Override
	public List<Board> selectByPageAndpagePerCountandSearch(int pageNo, int pagePerCount,String contentKeyword) {
		System.out.println(pageNo);
		System.out.println(pagePerCount);
		Pageable pageable = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "commNo"));
		//Page<Board> page =repository.findAll(pageRequest); 
		Page<Board> page = repository.findByContentsContainingAndState(contentKeyword, 0,pageable);
		List<Board> list = page.getContent(); 
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!serarhdao"+list);
		return list; 
	}
	
	@Override
	public List<Board> selectByCategoryAndState(String category, int pageNo, int pagePerCount) {
		System.out.println("daaaaaaao"+category);
		Pageable pageable = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "commNo"));		
		Page<Board> page = repository.findByCategoryAndState(category, 0, pageable);
		List<Board> list = page.getContent(); 
		return list;
	}

	@Override
	public long getCountByCategorysAndState(String category) {
		int state = 0; // state 값이 0인 경우
	    return repository.countByCategoryAndState(category, state);
	}
	@Override
	public void delete(int commNo) {
		Board board=repository.findById(commNo).get();
		board.setCommNo(commNo);
		board.setState(1);
	}

	@Override
	public void bulletinBoardViews(int commNo) {
		//System.out.println("service count");
		repository.bulletinBoardViews(commNo);
	
	}

	@Override
	public List<Board> selectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC,"commNo"));								
		//Page<Board> page = repository.findAll(pageRequest);
		Page<Board> page = repository.findByState(0, pageRequest);
		List<Board> list = page.getContent();
		return list;
	}
	@Override
	public List<Board> selectByPageAndpagePerCount(int pageNo, int pagePerCount, int state) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC,"commNo"));								
		//Page<Board> page = repository.findAll(pageRequest);
		Page<Board> page = repository.findByState(state, pageRequest);
		List<Board> list = page.getContent();
		return list;
	}

	@Override
	public long getCountByContentsAndState(String contents) {
		int state = 0; // state 값이 0인 경우
	    return repository.countByContentsContainingAndState(contents, state);
	}

	public List<Board> findmyboardlist(UserInfo user) {
		return repository.findByUserId(user);
	}
	
	@Override
	public List<Board> selectByPageByUser(int pageNo, int pagePerCount,UserInfo user) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC,"commNo"));								
		//Page<Board> page = repository.findAll(pageRequest);
		Page<Board> page = repository.findByUserIdAndState(user, 0, pageRequest);
		List<Board> list = page.getContent();
		return list;
	}

	@Override
	public long countBycommNo(Board commNo) {
		return repository.countBycommNo(commNo);
	}
}
