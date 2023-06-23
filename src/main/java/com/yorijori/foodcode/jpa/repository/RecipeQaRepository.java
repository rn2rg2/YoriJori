package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeQa;

public interface RecipeQaRepository extends JpaRepository<RecipeQa, Integer> {

	List<RecipeQa> findByRecipeNo(Recipe recipe);

}
