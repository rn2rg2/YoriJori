package com.yorijori.foodcode.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.repository.CategoryDAO;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

	@Override
	public List<Category> findByLevel(Integer upperLevel) {
		return categoryDAO.findByLevel(upperLevel);
	}
}