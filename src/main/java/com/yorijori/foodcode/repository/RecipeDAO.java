package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

public interface RecipeDAO {

	long countAll();

	List<Recipe> selectListByPage(int pageNo, int pagePerCount);

	void addWishList(UserWishlist userWishList);

	long countByRcpSeqByWishList(Recipe recipe);

	void deleteWishList(Recipe recipeNo);
}
