package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Ingredients;

public interface IngredientService {

	Ingredients insert(Ingredients ingredients);

	void update(Ingredients updatedata);

	List<Ingredients> selectAll();

	void delete(int matlNo);

	long countAll();

	List<Ingredients> selectByPage(int pageNo, String category);

	List<Ingredients> selectBySearch(int pageNo, String category, String searchData);

	List<Ingredients> selectByPagePerCount(int pageNo, int pagePerCount);

}
