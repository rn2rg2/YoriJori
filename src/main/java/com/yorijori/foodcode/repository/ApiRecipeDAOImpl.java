package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.repository.ApiRecipeRepository;

@Repository
public class ApiRecipeDAOImpl implements ApiRecipeDAO {
	ApiRecipeRepository apiRecipeRepository;

	@Autowired
	public ApiRecipeDAOImpl(ApiRecipeRepository apiRecipeRepository) {
		super();
		this.apiRecipeRepository = apiRecipeRepository;
	}

	@Override
	public long countAll() {
		return apiRecipeRepository.count();
	}

	@Override
	public ApiRecipe selectByRcpSeq(int rcpSeq) {
		return apiRecipeRepository.findByRcpSeq(rcpSeq);
	}

	@Override
	public List<ApiRecipe> selectListByPage(int page, int pagePerCount) {
		PageRequest pageRequest = PageRequest.of(page, pagePerCount, Sort.by(Sort.Direction.DESC, "rcpSeq"));
		Page<ApiRecipe> pagedata = apiRecipeRepository.findAll(pageRequest);
		List<ApiRecipe> list = pagedata.getContent();
		return list;
	}
}
