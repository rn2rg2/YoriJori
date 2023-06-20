package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.repository.CategoryReposityory;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	CategoryReposityory categoryreposityory;

	@Override
	public List<Category> selectAll() {
		// TODO Auto-generated method stub
		return categoryreposityory.findAll();
	}
		


}
