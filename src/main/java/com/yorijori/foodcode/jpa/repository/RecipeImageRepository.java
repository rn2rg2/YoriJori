package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;

public interface RecipeImageRepository extends JpaRepository<RecipeImage, Integer> {
    List<RecipeImage> findByRecipeNo(Recipe recipe);
	
}
	