package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.Board;
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
	public List<Board> selectAll() {

		return repository.findAll(Sort.by(Sort.Direction.DESC, "commNo"));
		
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
	public List<Board> search(String tag, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Board> findByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
