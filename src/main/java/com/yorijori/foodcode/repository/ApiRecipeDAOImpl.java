package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.UserWishListApi;
import com.yorijori.foodcode.jpa.repository.ApiRecipeRepository;
import com.yorijori.foodcode.jpa.repository.UserWishlistApiRepository;

@Repository
public class ApiRecipeDAOImpl implements ApiRecipeDAO {
	ApiRecipeRepository apiRecipeRepository;
	UserWishlistApiRepository userWishListApiRepo;
	
	@Autowired
	public ApiRecipeDAOImpl(ApiRecipeRepository apiRecipeRepository,UserWishlistApiRepository userWishListApiRepo) {
		super();
		this.apiRecipeRepository = apiRecipeRepository;
		this.userWishListApiRepo = userWishListApiRepo;
	}

	@Override
	public long countAll() {
		return apiRecipeRepository.count();
	}
	
	@Override
	public long countByRcpSeqByWishList(ApiRecipe apirecipe) {
		return userWishListApiRepo.countByRcpSeq(apirecipe);
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
	
	@Override
	public void addWishList(UserWishListApi userWishListApi) {
		userWishListApiRepo.save(userWishListApi);
	}
	@Override
	public void deleteWishList(ApiRecipe apirecipe) {
		userWishListApiRepo.deleteByRcpSeq(apirecipe);
	}
}
