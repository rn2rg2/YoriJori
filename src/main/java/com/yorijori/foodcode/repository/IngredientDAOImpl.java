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
		return repository.findAll();
	}
	
	@Override
	public Ingredients selectByMatlNo(int matlNo) {
		return repository.findByMatlNo(matlNo);
	}

	@Override
	public Ingredients findById(int matlNo) {
		return repository.findById(matlNo).orElseThrow(()-> new RuntimeException());
	}
	
	@Override
	public long countAll() {
		return repository.count(); 
	}
	@Override
	public long countByMatlNameContaining(String matlName) {
		return repository.countByMatlNameContaining(matlName); 
	}
	@Override
	public List<Ingredients> selectByPagePerCount(int pageNo,int pagePerCount) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNo, 6, Sort.by(Sort.Direction.DESC, "matlNo"));
		Page<Ingredients> page = repository.findAll(pageRequest);
		List<Ingredients> list = page.getContent();
		return list;
	}
	
	@Override
	public List<Ingredients> selectByPage(int pageNo) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNo, 12, Sort.by(Sort.Direction.DESC, "matlNo"));
		Page<Ingredients> page = repository.findAll(pageRequest);
		List<Ingredients> list = page.getContent();
		return list;
	}
	@Override
	public List<Ingredients> selectByCategory(int pageNo, String category) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNo, 12, Sort.by(Sort.Direction.DESC, "matlNo"));
		Page<Ingredients> page = repository.findByCategory(category, pageRequest);
		List<Ingredients> list = page.getContent();
		return list;
	}
	@Override
	public List<Ingredients> selectBySearch(int pageNo, String category, String searchData, int pagePerCount) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "matlNo"));
		Page<Ingredients> page = null;
		if (category.equals("all")) {
			page = repository.findByMatlNameContaining(searchData,pageRequest);
		} else {
			page = repository.findByMatlNameContainingAndCategoryContaining(searchData, category, pageRequest);
		}
		List<Ingredients> list = page.getContent();
		return list;
	}

}
