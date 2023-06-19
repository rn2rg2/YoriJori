package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.CookingClassContent;

public interface CookingClassContentRepository extends JpaRepository<CookingClassContent, Integer> {
	public List<CookingClassContent> findByCookNo(int CookNo);
}
