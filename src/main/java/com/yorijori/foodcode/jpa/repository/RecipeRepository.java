package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
    Recipe findByRecipeNo(int recipeNo);
}
