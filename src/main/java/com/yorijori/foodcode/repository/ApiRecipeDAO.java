package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.ApiRecipeQa;
import com.yorijori.foodcode.jpa.entity.ApiRecipeReview;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserWishListApi;
import com.yorijori.foodcode.service.ApiRecipeService;

public interface ApiRecipeDAO {

	List<ApiRecipe> selectListByPage(int page, int pagePerCount);

	long countAll();

	ApiRecipe selectByRcpSeq(int rcpSeq);

	void addWishList(UserWishListApi userWishListApi);

	long countByRcpSeqByWishList(ApiRecipe apirecipe);

	void deleteWishList(ApiRecipe apirecipe);
	void reviewsave(ApiRecipeReview apirecipereview);

	ApiRecipe findById(int rcpSeq);

	List<ApiRecipe> selectListByPageAndSort(int page, int pagePerCount, String sortType);
    List<ApiRecipeReview> findByRcpSeq(int rcpSeq);
    List<ApiRecipeReview> getByRecipeNo(ApiRecipe recipe);


}
