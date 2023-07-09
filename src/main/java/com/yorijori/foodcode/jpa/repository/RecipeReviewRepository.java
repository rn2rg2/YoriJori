package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface RecipeReviewRepository extends JpaRepository<RecipeReview, Integer> {
	List<RecipeReview> findByRecipeNo(Recipe recipe);
	long countByUserIdAndRecipeNo(UserInfo userId, Recipe recipeNo);

}
