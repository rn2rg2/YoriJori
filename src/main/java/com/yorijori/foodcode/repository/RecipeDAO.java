package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Recipe;

public interface RecipeDAO {

	long countAll();

	List<Recipe> selectListByPage(int pageNo, int pagePerCount);
}
