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
    public Category findById(Category category) {
    	return categoryRepository.findById(category.getCategoryNo()).get();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

	@Override
	public List<Category> findByLevel(Integer upperLevel) {
		return categoryRepository.findByLevel(upperLevel);
	}

	@Override
	public List<Category> findByLevelAndUpperLevel(int level, String upperlevel) {
		return categoryRepository.findByLevelAndUpperLevel(level, upperlevel);
	}

	@Override
	public List<Category> findByUpperLevel(String upperlevel) {
		
		return categoryRepository.findByUpperLevel(upperlevel);
	}

}

