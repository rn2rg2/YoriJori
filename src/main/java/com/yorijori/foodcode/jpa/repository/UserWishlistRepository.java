package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

public interface UserWishlistRepository extends JpaRepository<UserWishlist, Integer> {
	void deleteByRecipeNo(Recipe recipeNo);
	long countByRecipeNo(Recipe recipeNo);
	Page<UserWishlist> findAllByUserId(UserInfo userId, Pageable pageable);
	
	@Query(value="SELECT DISTINCT j.*"
			+ "FROM user_wishlist uw "
			+ "JOIN recipe j "
			+ "ON uw.recipe_no = j.recipe_no "
			+ "JOIN recipe_category rc "
			+ "ON j.recipe_no = rc.recipe_no "
			+ "WHERE uw.user_id = :userId"
			,nativeQuery = true)
	List<Recipe> findRecipeNoByUserId(@Param("userId") String userId);
	
	@Query(value="SELECT c.name FROM user_wishlist uw " + 
			"JOIN recipe j " + 
			"ON uw.recipe_no = j.recipe_no" + 
			"JOIN recipe_category rc" + 
			"ON j.recipe_no = rc.recipe_no" + 
			"JOIN category c" + 
			"ON rc.category_no = c.category_no" + 
			"WHERE uw.user_id = :userId " + 
			"AND j.recipe_no = :recipeNo", nativeQuery = true)
	List<String> findRcpAndCategory(@Param("userId") String userId, @Param("recipeNo") int recipeNo);
	
	long countByUserId(UserInfo userId);
}
