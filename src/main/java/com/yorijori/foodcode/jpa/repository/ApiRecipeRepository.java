package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;

public interface ApiRecipeRepository extends JpaRepository<ApiRecipe, Integer>{
	
	
	/*
	 * @Query("SELECT a FROM ApiRecipe a WHERE a.rcpSeq= :rcpSeq") ApiRecipe
	 * getApirecipeByRcpSeq (@Param("rcpSeq") int rcpSeq);
	 */
	 
	  ApiRecipe findByRcpSeq(int rcpSeq);
	
	/*
	 * @Query(value =
	 * "SELECT a.*, b.* FROM api_recipe a JOIN api_recipe_img b ON a.RCP_SEQ = b.RCP_SEQ WHERE a.RCP_SEQ = :rcpSeq"
	 * , nativeQuery = true) ApiRecipe getApirecipeByRcpSeq(@Param("rcpSeq") int
	 * rcpSeq);
	 */

}
