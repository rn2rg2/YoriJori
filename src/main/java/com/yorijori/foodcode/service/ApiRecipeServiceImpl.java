package com.yorijori.foodcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.repository.ApiRecipeDAO;

@Service
public class ApiRecipeServiceImpl implements ApiRecipeService {
	ApiRecipeDAO apiRecipeDAO;
	
	
	@Autowired
	public ApiRecipeServiceImpl(ApiRecipeDAO apiRecipeDAO) {
		super();
		this.apiRecipeDAO = apiRecipeDAO;
	}

	@Override
	public long countAll() {
		return apiRecipeDAO.countAll();
	}
	
	@Override
	public List<ApiRecipe> getServerRecipe(int page, int pagePerCount){
		return apiRecipeDAO.selectListByPage(page, pagePerCount);
	}
}
