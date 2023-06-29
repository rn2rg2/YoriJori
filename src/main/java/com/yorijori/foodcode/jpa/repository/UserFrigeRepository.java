package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yorijori.foodcode.jpa.VO.RecipeVO;
import com.yorijori.foodcode.jpa.entity.UserFrige;

public interface UserFrigeRepository extends JpaRepository<UserFrige, Integer>{
	
	List<UserFrige> findAllByUserId(String userId);
	long countByUserId(String userId);
	void delete(UserFrige userFrige);
	
	List<UserFrige> deleteByUserId(String userId);

	@Query(value="SELECT r.* from recipe r " + 
			"JOIN recipe_ingredients ri " + 
			"ON r.recipe_no = ri.recipe_no " + 
			"JOIN ingredients i " + 
			"ON ri.matl_no = i.matl_no " + 
			"JOIN recipe_category rc " + 
			"ON rc.recipe_no = r.recipe_no " + 
			"JOIN category c " + 
			"ON rc.category_no = c.category_no " + 
			"WHERE c.name = :name " + 
			"AND i.matl_no = :matlNo "+
			"ORDER BY r.count Limit 1", nativeQuery = true)
	List<RecipeVO> searchPreferMatlNo(@Param("name") String name, @Param("matlNo") int matlNo);
}
