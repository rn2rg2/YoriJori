package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeQa;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface RecipeService {
	
	long countAll();
	// 레시피 목록
	List<Recipe> selectListByPage(int pageNo, int pagePerCount);
	
	// 레시피의 대한 정보 조회
	Recipe select(int recipeNo);
	
	// 레시피 좋아요
	void wishList(UserInfo userId, Recipe recipeNo);
	
	// 레시피 조회수
	void viewCountUp(int recipeNo);
	
	// 레시피에 대한 이미지테이블조회
	List<RecipeImage> imgselect(int recipeNo);
	
	//
	List<Recipe> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType);
	
	///레시피에 대한 리뷰 조회 
	List<RecipeReview> reviewselect(int recipeNo);
    //레시피에 대한 Q/A 조회
    List<RecipeQa> QAselect(int recipeNo);
	//레시피에 대한 리뷰 작성 (insert)
	void reviewsave(RecipeReview recipereview);
	//레시피에 대한 QA 작성 (insert)
    void recipeqasave(RecipeQa recipeqa);
}
