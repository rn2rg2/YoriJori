package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.jpa.repository.IngredientsRepository;

@Repository
public class IngredientDAOImpl implements IngredientDAO {
	IngredientsRepository repository;

	@Autowired
	public IngredientDAOImpl(IngredientsRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Ingredients insert(Ingredients ingredients) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int matlNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Ingredients updatedata) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Ingredients> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredients> selectAllByPage(int pageNo, int pagePerCount) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "matlNo"));
		Page<Ingredients> page = repository.findAll(pageRequest);
		List<Ingredients> list = page.getContent();
		return list;
	}
}
