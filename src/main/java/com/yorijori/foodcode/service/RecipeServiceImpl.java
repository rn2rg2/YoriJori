package com.yorijori.foodcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.repository.RecipeDAO;

@Service
public class RecipeServiceImpl implements RecipeService {
	RecipeDAO recipeDAO;
	
	@Autowired
	public RecipeServiceImpl(RecipeDAO recipeDAO) {
		super();
		this.recipeDAO = recipeDAO;
	}

	@Override
	public long countAll() {
		return recipeDAO.countAll();
	}
	
	@Override
	public List<Recipe> selectListByPage(int pageNo,int pagePerCount){
		return recipeDAO.selectListByPage(pageNo, pagePerCount);
	}
	
}
