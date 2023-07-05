package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.ApiRecipeQa;
import com.yorijori.foodcode.jpa.entity.ApiRecipeReview;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ApiRecipeService {

	List<ApiRecipe> getServerRecipe(int page, int pagePerCount);

	long countAll();

	ApiRecipe selectByRcpSeq(int rcpSeq);

	void wishList(UserInfo userinfo, ApiRecipe rcpSeq);

	void viewCountUp(int rcpSeq);
	void reviewsave(ApiRecipeReview apirecipereview);

	List<ApiRecipe> selectListByPageAndSort(int page, int pagePerCount, String sortType);
	List<ApiRecipeReview> findByRcpSeq(int rcpSeq);
    List<ApiRecipeReview> getAPIByRecipeNo(ApiRecipe recipe);

}
