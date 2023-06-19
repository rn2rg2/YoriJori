package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.UserWishListApi;

public interface ApiRecipeDAO {

	List<ApiRecipe> selectListByPage(int page, int pagePerCount);

	long countAll();

	ApiRecipe selectByRcpSeq(int rcpSeq);

	void addWishList(UserWishListApi userWishListApi);

	long countByRcpSeqByWishList(ApiRecipe apirecipe);

	void deleteWishList(ApiRecipe apirecipe);

}
