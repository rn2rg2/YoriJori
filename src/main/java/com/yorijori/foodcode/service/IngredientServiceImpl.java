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
}
