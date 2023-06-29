package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yorijori.foodcode.jpa.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>,JpaSpecificationExecutor<Recipe>{
    Recipe findByRecipeNo(int recipeNo);
}
