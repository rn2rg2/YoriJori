package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;

public interface ApiRecipeService {

	List<ApiRecipe> getServerRecipe(int page, int pagePerCount);

	long countAll();

	ApiRecipe selectByRcpSeq(int rcpSeq);

}
