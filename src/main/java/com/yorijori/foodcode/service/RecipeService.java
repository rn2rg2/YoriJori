package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Recipe;

public interface RecipeService {

	long countAll();

	List<Recipe> selectListByPage(int pageNo, int pagePerCount);

}
