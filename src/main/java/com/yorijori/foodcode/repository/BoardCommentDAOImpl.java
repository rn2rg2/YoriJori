package com.yorijori.foodcode.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.BoardComment;
@Repository
public class BoardCommentDAOImpl implements BoardCommentDAO {
	private EntityManager entityManager;
	
	
	  @Autowired 
	  public BoardCommentDAOImpl(EntityManager entityManager) {
		  super();
		  this.entityManager = entityManager; }
	 

	@Override
	public BoardComment insert(BoardComment boardComment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardComment> selectAll() {
		// TODO Auto-generated method stub
		return null;
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

}
