package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.dto.BoardDTO;
import com.yorijori.foodcode.jpa.entity.BoardComment;
import com.yorijori.foodcode.jpa.repository.BoardCommentRepository;

@Repository
public class BoardCommentDAOImpl implements BoardCommentDAO {
	BoardCommentRepository repository;
	SqlSession sqlSessionTemplate;
	
	@Autowired
	public BoardCommentDAOImpl(BoardCommentRepository repository, SqlSession sqlSessionTemplate) {
		super();
		this.repository = repository;
		this.sqlSessionTemplate = sqlSessionTemplate;
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
		return 0;
	}



	@Override
	public long countAll() {
		return repository.count();
	}

	@Override
	public List<BoardDTO> selectComment(int commNo) {
		/*
		 * Sort sort1 = Sort.by("groupNo").ascending(); Sort sort2 =
		 * Sort.by("displayOrderNo").ascending(); Sort sortAll = sort1.and(sort2);
		 * //and를 이용한 연결 List<BoardComment> list = repository.findByCommNo(commNo);
		 */
		return sqlSessionTemplate.selectList("com.yorijori.board.selectComment", commNo);
	}

	@Override
	public int updateGroupNo(BoardDTO boardDTO) {
		return sqlSessionTemplate.update("com.yorijori.board.updateGroupNo", boardDTO);
	}

	@Override
	public int insertComment(BoardDTO boardDTO) {
		return sqlSessionTemplate.insert("com.yorijori.board.insertComment", boardDTO);
	}

	@Override
	public List<BoardComment> selectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC,"commNo"));								
		Page<BoardComment> page = repository.findAll(pageRequest);
		List<BoardComment> list = page.getContent();
		return list;
	}

	@Override
	public void delete(int commentNo) {
		BoardComment boardComment = repository.findById(commentNo).get();
		boardComment.setCommentNo(commentNo);
		boardComment.setState(1);
		//System.out.println("댓글삭제servvvvvv");

	
	}



}
