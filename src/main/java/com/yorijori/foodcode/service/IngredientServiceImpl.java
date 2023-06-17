package com.yorijori.foodcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.repository.IngredientDAO;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
	IngredientDAO ingredientDAO;

	@Autowired
	public IngredientServiceImpl(IngredientDAO ingredientDao) {
		super();
		this.ingredientDAO = ingredientDao;
	}

	@Override
	public Ingredients insert(Ingredients ingredients) {
		// TODO Auto-generated method stub
		return ingredientDAO.insert(ingredients);
	}

	@Override
	public void update(Ingredients updatedata) {
		// TODO Auto-generated method stub
		ingredientDAO.update(updatedata);

	}

	@Override
	public List<Ingredients> selectAll() {
		// TODO Auto-generated method stub
		return ingredientDAO.selectAll();
	}

	@Override
	public void delete(int matlNo) {
		ingredientDAO.delete(matlNo);
		// TODO Auto-generated method stub
	}

	@Override
	public long countAll() {
		return ingredientDAO.countAll();
	}
	
	@Override
	public List<Ingredients> selectByPagePerCount(int pageNo, int pagePerCount) {
		List<Ingredients> list = null;
			list = ingredientDAO.selectByPagePerCount(pageNo,pagePerCount);
		return list;
	}

	@Override
	public List<Ingredients> selectByPage(int pageNo, String category) {
		List<Ingredients> list = null;
		if (category.equals("all")) {
			list = ingredientDAO.selectByPage(pageNo);
		} else {
			list = ingredientDAO.selectByCategory(pageNo, category);
		}
		return list;
	}
	
	@Override
	public List<Ingredients> selectBySearch(int pageNo, String category, String searchData) {
		return ingredientDAO.selectBySearch(pageNo, category, searchData);
	}

}
