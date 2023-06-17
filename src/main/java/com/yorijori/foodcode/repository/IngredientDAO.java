package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Ingredients;

public interface IngredientDAO {

	Ingredients insert(Ingredients ingredients);

	void delete(int matlNo);

	void update(Ingredients updatedata);

	List<Ingredients> selectAll();

	long countAll();

	List<Ingredients> selectByPage(int pageNo);

	List<Ingredients> selectByCategory(int pageNo, String category);

	List<Ingredients> selectBySearch(int pageNo, String category, String searchData);

	List<Ingredients> selectByPagePerCount(int pageNo, int pagePerCount);

}
