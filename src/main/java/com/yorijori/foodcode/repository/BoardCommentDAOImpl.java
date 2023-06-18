package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.BoardComment;
import com.yorijori.foodcode.jpa.repository.BoardCommentRepository;
@Repository
public class BoardCommentDAOImpl implements BoardCommentDAO {
	BoardCommentRepository repository;
	
	@Autowired
	public BoardCommentDAOImpl(BoardCommentRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public BoardComment insert(BoardComment boardComment) {
		return repository.save(boardComment);
	}

	@Override
	public List<BoardComment> selectAll() {
		Sort sort = Sort.by(
			      Sort.Order.asc("groupNo "),
			      Sort.Order.asc("displayOrderNo")
			    );
		return repository.findAll(sort);
	}

	@Override
	public int update(BoardComment boardComment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BoardComment boardComment) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public long countAll() {
		return repository.count();
	}

	@Override
	public List<BoardComment> selectComment(int commNo) {
		List<BoardComment> list = repository.findByCommNo(commNo);
		return list;
	}



}
