package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	Page<Question> findByState(int state, Pageable pageable);

}
