package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yorijori.foodcode.jpa.VO.MonthlyRcpVO;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>,JpaSpecificationExecutor<Recipe>{
    Recipe findByRecipeNo(int recipeNo);
    Page<Recipe> findByNameContaining(String name, Pageable pageable);
    Long countByNameContaining(String name);
    Page<Recipe> findByUserId(UserInfo userid, Pageable pageable);
    Page<Recipe> findByRecipeNo(int RecipeNo, Pageable pageable);
    Long countByUserId(UserInfo userId);
    
    @Query(value="SELECT MONTH(r.date) AS Mon," + 
    		"COUNT(*) AS Value " + 
    		"FROM recipe r " + 
    		"GROUP BY Mon", nativeQuery = true)
    List<MonthlyRcpVO> getMonthlyData();
    List<Recipe> findTop15ByOrderByCountDesc();
}
