package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface RecipeService {

	long countAll();

	List<Recipe> selectListByPage(int pageNo, int pagePerCount);
	
	Recipe select(int recipeNo);

	void wishList(UserInfo userId, Recipe recipeNo);

	void viewCountUp(int recipeNo);

	List<RecipeImage> imgselect(int recipeNo);
	
	void insertAll(Recipe recipedata);

	List<Recipe> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType);
	List<RecipeReview> reviewselect(int recipeNo);
	
	void reviewsave(RecipeReview recipereview);
}
