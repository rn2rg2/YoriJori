package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.UserInfo;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> { 
    List<Category> findAll();
    List<Category> findByLevel(Integer upperLevel);

}

	