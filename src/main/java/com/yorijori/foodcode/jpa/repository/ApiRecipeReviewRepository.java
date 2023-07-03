package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.ApiRecipeQa;
import com.yorijori.foodcode.jpa.entity.ApiRecipeReview;



public interface ApiRecipeReviewRepository extends JpaRepository<ApiRecipeReview, Integer> {
    List<ApiRecipeReview> findByRcpSeq(ApiRecipe rcpSeq);

}
