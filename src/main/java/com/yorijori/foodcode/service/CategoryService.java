package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Category;


public interface CategoryService {
	List<Category> selectAll();

}
