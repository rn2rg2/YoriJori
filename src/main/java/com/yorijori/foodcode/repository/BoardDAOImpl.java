package com.yorijori.foodcode.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.Board;
@Repository
public class BoardDAOImpl implements BoardDAO {
	private EntityManager entityManager;
	
	@Autowired
	public BoardDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Board insert(Board board) {
		//System.out.println("DAO");
		//System.out.println(board.toString());
		entityManager.persist(board);
		return board;
	}

	@Override
	public List<BoardDTO> selectAll() {
		String jpql="select board from Board as board";
		List<BoardDTO> list = entityManager.createQuery(jpql,BoardDTO.class)
							.getResultList();
		return list;
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
	public List<BoardDTO> search(String tag, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardDTO> findByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
