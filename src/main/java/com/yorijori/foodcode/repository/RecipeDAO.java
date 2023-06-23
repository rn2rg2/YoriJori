package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

public interface RecipeDAO {

	long countAll();

	List<Recipe> selectListByPage(int pageNo, int pagePerCount);

	void addWishList(UserWishlist userWishList);

	long countByRcpSeqByWishList(Recipe recipe);

	void deleteWishList(Recipe recipeNo);

	Recipe findById(int recipeNo);

	Recipe select(int recipeNo);

    List<RecipeImage> imgselect(int recipeNo);


	void insertAll(Recipe recipedata);

	void insertImageAll(List<RecipeImage> recipeimg);
	

	List<Recipe> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType);
    List<RecipeReview> reviewselect(int recipeNo);

    void reviewsave(RecipeReview recipereview);


}
