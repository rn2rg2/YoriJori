package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Ingredients;

public interface IngredientDAO {

	Ingredients insert(Ingredients ingredients);

	void delete(int matlNo);

	void update(Ingredients updatedata);

	List<Ingredients> selectAll();

	List<Ingredients> selectAllByPage(int pageNo, int pagePerCount);

}
