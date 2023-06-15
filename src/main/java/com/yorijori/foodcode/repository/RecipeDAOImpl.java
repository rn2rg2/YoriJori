package com.yorijori.foodcode.repository;

import com.yorijori.foodcode.jpa.repository.RecipeCategoryRepository;
import com.yorijori.foodcode.jpa.repository.RecipeImageRepository;
import com.yorijori.foodcode.jpa.repository.RecipeQaRepository;
import com.yorijori.foodcode.jpa.repository.RecipeRepository;
import com.yorijori.foodcode.jpa.repository.RecipeReviewRepository;

public class RecipeDAOImpl implements RecipeDAO {
	RecipeRepository reciperepository;
	RecipeImageRepository recipeimagerepository;
	RecipeQaRepository recipeqarepository;
	RecipeReviewRepository recipereviewrepository;
	RecipeCategoryRepository recipecategoryrepository;
}
