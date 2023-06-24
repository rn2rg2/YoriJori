package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeIngredients;

public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Integer> {
    List<RecipeIngredients> findByRecipeNo(Recipe recipe);

}
