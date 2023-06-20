package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.UserInfo;


@Repository
public interface CategoryReposityory extends JpaRepository<Category, Integer>{ 
	List<Category> findByList();
		

}
