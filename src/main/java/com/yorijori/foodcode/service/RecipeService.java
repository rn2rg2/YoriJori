package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface RecipeService {

	long countAll();

	List<Recipe> selectListByPage(int pageNo, int pagePerCount);

	void wishList(UserInfo userId, Recipe recipeNo);

}
