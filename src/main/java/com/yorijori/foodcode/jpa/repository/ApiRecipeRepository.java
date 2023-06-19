package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;

public interface ApiRecipeRepository extends JpaRepository<ApiRecipe, Integer>{

}
