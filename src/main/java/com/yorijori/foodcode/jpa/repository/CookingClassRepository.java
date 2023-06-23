package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.CookingClass;

@Repository
public interface CookingClassRepository extends JpaRepository<CookingClass, Integer>{
	public List<CookingClass> findByState(int state);
	public List<CookingClass> findTop5ByOrderByCountAsc();
}
