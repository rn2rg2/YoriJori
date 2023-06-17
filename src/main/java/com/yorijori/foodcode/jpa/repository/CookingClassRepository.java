package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.CookingClass;

public interface CookingClassRepository extends JpaRepository<CookingClass, Integer>{
	public List<CookingClass> findByState(int state);
}
