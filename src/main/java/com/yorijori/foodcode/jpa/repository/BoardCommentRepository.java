package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
	List<BoardComment> findByCommNo(int commNo);

}
