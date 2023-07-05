package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Category;

public interface CategoryDAO {
    List<Category> findAll();
    List<Category> findByLevel(Integer upperLevel);
    List<Category> findByLevelAndUpperLevel(int level, String upperlevel);
    List<Category> findByUpperLevel(String upperlevel);
	Category findById(Category category);
}
