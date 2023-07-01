package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yorijori.foodcode.jpa.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>,JpaSpecificationExecutor<Recipe>{
    Recipe findByRecipeNo(int recipeNo);
    Page<Recipe> findByNameContaining(String name, Pageable pageable);
    Long countByNameContaining(String name);
}
