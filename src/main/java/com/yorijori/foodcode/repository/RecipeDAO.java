package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeIngredients;
import com.yorijori.foodcode.jpa.entity.RecipeQa;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

public interface RecipeDAO {

	long countAll();
	// 레시피 목록
	List<Recipe> selectListByPage(int pageNo, int pagePerCount);
	// 레시피의 대한 정보 조회
	
	// 레시피 좋아요
	void addWishList(UserWishlist userWishList);
	
	long countByRcpSeqByWishList(Recipe recipe);
	void deleteWishList(Recipe recipeNo);
	Recipe findById(int recipeNo);
	
	// 레시피의 대한 정보 조회
	Recipe select(int recipeNo);
	
	// 레시피에 대한 이미지테이블조회
    List<RecipeImage> imgselect(int recipeNo);


	void insertAll(Recipe recipedata);

	void insertImageAll(List<RecipeImage> recipeimg);
	

	List<Recipe> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType);
	
	///레시피에 대한 리뷰 조회 
    List<RecipeReview> reviewselect(int recipeNo);
    //레시피에 대한 Q/A 조회
    List<RecipeQa> QAselect(int recipeNo);
    
	//레시피에 대한 리뷰 작성 (insert)
    void reviewsave(RecipeReview recipereview);



    void recipeqasave(RecipeQa recipeqa);
	List<RecipeIngredients> selectingr(int recipeNo);
	List<Recipe> findAll(Specification<Recipe> spec);
    

}
