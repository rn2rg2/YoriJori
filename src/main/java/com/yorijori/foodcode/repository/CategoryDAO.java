package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.UserFrige;

public interface CategoryDAO {
    List<Category> findAll();
    List<Category> findByLevel(Integer upperLevel);
    List<Category> findByLevelAndUpperLevel(int level, String upperlevel);
    List<Category> findByUpperLevel(String upperlevel);
}
