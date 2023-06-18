package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.repository.RecipeCategoryRepository;
import com.yorijori.foodcode.jpa.repository.RecipeImageRepository;
import com.yorijori.foodcode.jpa.repository.RecipeQaRepository;
import com.yorijori.foodcode.jpa.repository.RecipeRepository;
import com.yorijori.foodcode.jpa.repository.RecipeReviewRepository;

@Repository
public class RecipeDAOImpl implements RecipeDAO {
	RecipeRepository reciperepository;
	RecipeImageRepository recipeimagerepository;
	RecipeQaRepository recipeqarepository;
	RecipeReviewRepository recipereviewrepository;
	RecipeCategoryRepository recipecategoryrepository;
	
	@Autowired
	public RecipeDAOImpl(RecipeRepository reciperepository, RecipeImageRepository recipeimagerepository,
			RecipeQaRepository recipeqarepository, RecipeReviewRepository recipereviewrepository,
			RecipeCategoryRepository recipecategoryrepository) {
		super();
		this.reciperepository = reciperepository;
		this.recipeimagerepository = recipeimagerepository;
		this.recipeqarepository = recipeqarepository;
		this.recipereviewrepository = recipereviewrepository;
		this.recipecategoryrepository = recipecategoryrepository;
	}
	
	@Override
	public long countAll() {
		return reciperepository.count();
	}
	@Override
	public List<Recipe> selectListByPage(int pageNo,int pagePerCount){
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "recipeNo"));
		Page<Recipe> page = reciperepository.findAll(pageRequest);
		List<Recipe> list = page.getContent();
		
		return list;
	}
	
	
}
