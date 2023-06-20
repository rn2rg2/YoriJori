package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yorijori.foodcode.jpa.entity.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
	@Query("SELECT i FROM Ingredients i WHERE Function('replace', i.category, ' ', '') LIKE %:category%")
	Page<Ingredients> findByCategory(@Param("category") String category, Pageable pageable);

	@Query("SELECT i FROM Ingredients i WHERE i.matlName LIKE %:matlName% AND Function('replace', i.category, ' ', '') LIKE %:category%")
	Page<Ingredients> findByMatlNameContainingAndCategoryContaining(@Param("matlName") String matlName,
			@Param("category") String category, Pageable pageable);

	Page<Ingredients> findByMatlNameContaining(String matlName, Pageable pageable);

	@Query("SELECT COUNT(e) FROM Ingredients e WHERE e.matlName LIKE %:matlName%")
    Long countByMatlNameContaining(String matlName);
	
	Ingredients findByMatlNo(int matlNo);
}
