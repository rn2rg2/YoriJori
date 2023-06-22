package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeReview;

public interface RecipeReviewRepository extends JpaRepository<RecipeReview, Integer> {
	List<RecipeReview> findByRecipeNo(Recipe recipe);

}
