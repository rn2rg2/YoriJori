package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.ApiRecipeQa;
import com.yorijori.foodcode.jpa.entity.ApiRecipeReview;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserWishListApi;
import com.yorijori.foodcode.jpa.repository.ApiRecipeReviewRepository;
import com.yorijori.foodcode.jpa.repository.ApiRecipeRepository;
import com.yorijori.foodcode.jpa.repository.RecipeQaRepository;
import com.yorijori.foodcode.jpa.repository.UserWishlistApiRepository;

@Repository
public class ApiRecipeDAOImpl implements ApiRecipeDAO {
	ApiRecipeRepository apiRecipeRepository;
	UserWishlistApiRepository userWishListApiRepo;
	ApiRecipeReviewRepository apirecipeqarepository;
	
	@Autowired
	public ApiRecipeDAOImpl(ApiRecipeRepository apiRecipeRepository,UserWishlistApiRepository userWishListApiRepo,ApiRecipeReviewRepository apirecipeqarepository) {
		super();
		this.apiRecipeRepository = apiRecipeRepository;
		this.userWishListApiRepo = userWishListApiRepo;
		this.apirecipeqarepository = apirecipeqarepository;
	}
	@Override
	public ApiRecipe findById(int rcpSeq) {
		return apiRecipeRepository.findById(rcpSeq).orElseThrow(()-> new RuntimeException());
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
	public List<ApiRecipe> selectListByPageAndSort(int page, int pagePerCount, String sortType) {
		PageRequest pageRequest = PageRequest.of(page, pagePerCount, Sort.by(Sort.Direction.DESC, sortType));
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

	@Override
	public void reviewsave(ApiRecipeReview apirecipereview) {
		apirecipeqarepository.save(apirecipereview);
	}
	@Override
	public List<ApiRecipeReview> findByRcpSeq(int rcpSeq) {
	    ApiRecipe apirecipe = apiRecipeRepository.findById(rcpSeq).orElse(null);
		return apirecipeqarepository.findByRcpSeq(apirecipe);
	}

	@Override
	public List<ApiRecipeReview> getByRecipeNo(ApiRecipe recipe) {
		// TODO Auto-generated method stub
		return apirecipeqarepository.findByRcpSeq(recipe);
	}
	
}
