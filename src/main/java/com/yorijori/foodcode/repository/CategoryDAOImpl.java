package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.repository.CategoryRepository;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryDAOImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

	@Override
	public List<Category> findByLevel(Integer upperLevel) {
		return categoryRepository.findByLevel(upperLevel);
	}

}

