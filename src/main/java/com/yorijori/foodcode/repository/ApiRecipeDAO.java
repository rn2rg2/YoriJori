package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;

public interface ApiRecipeDAO {

	List<ApiRecipe> selectListByPage(int page, int pagePerCount);

	long countAll();

	ApiRecipe selectByRcpSeq(int rcpSeq);

}
