package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yorijori.foodcode.jpa.entity.Board;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	Board findByCommNo(int commNo);
	// public List<Board> findByState(int state);

	@Modifying
	@Query(value = "update community_info a set a.view = a.view + 1 where comm_no = ?1", nativeQuery = true)
	void bulletinBoardViews(int commNo);

	Page<Board> findByState(int state, Pageable pageable);

	Page<Board> findByUserIdAndState(UserInfo userId,int state, Pageable pageable);

	List<Board> findByCategory(String category);

	Page<Board> findByContentsContainingAndState(String contents, int state, Pageable pageable);

	long countByContentsContainingAndState(String content, int state);

	Page<Board> findByCategoryAndState(String category, int state, Pageable pageable);

	long countByCategoryAndState(String category, int state);
	
	List<Board> findByUserId(UserInfo user);

	long countByUserId(UserInfo user);
	
	long countBycommNo(Board commNo);
	
	long countByState(int state);

}