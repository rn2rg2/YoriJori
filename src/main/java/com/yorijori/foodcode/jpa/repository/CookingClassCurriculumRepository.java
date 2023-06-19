package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;

public interface CookingClassCurriculumRepository extends JpaRepository<CookingClassCurriculum, Integer> {
	public List<CookingClassCurriculum> findByCookNo(int CookNo);
}
