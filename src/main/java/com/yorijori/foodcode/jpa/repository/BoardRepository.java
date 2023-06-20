package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yorijori.foodcode.jpa.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	Board findByCommNo(int commNo);
	public List<Board> findByState(int state);
	
	@Modifying
	@Query(value="update community_info a set a.view = a.view + 1 where comm_no = ?1", nativeQuery = true)
	void bulletinBoardViews(int commNo);
	
	
	
	
}
