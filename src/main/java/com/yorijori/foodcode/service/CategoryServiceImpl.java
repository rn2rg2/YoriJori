package com.yorijori.foodcode.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.repository.CategoryDAO;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	CategoryDAO categorydao;

	@Override
	public List<Category> selectAll() {
		// TODO Auto-generated method stub
		return categorydao.selectAll();
	}
	
}
