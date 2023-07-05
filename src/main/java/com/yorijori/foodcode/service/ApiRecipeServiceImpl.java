package com.yorijori.foodcode.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.ApiRecipeQa;
import com.yorijori.foodcode.jpa.entity.ApiRecipeReview;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishListApi;
import com.yorijori.foodcode.repository.ApiRecipeDAO;

@Service
@Transactional
public class ApiRecipeServiceImpl implements ApiRecipeService {
	ApiRecipeDAO apiRecipeDAO;
	
	
	@Autowired
	public ApiRecipeServiceImpl(ApiRecipeDAO apiRecipeDAO) {
		super();
		this.apiRecipeDAO = apiRecipeDAO;
	}

	@Override
	public long countAll() {
		return apiRecipeDAO.countAll();
	}
	
	@Override
	public ApiRecipe selectByRcpSeq(int rcpSeq) {
		return apiRecipeDAO.selectByRcpSeq(rcpSeq);
	}
	
	@Override
	public List<ApiRecipe> getServerRecipe(int page, int pagePerCount){
		return apiRecipeDAO.selectListByPage(page, pagePerCount);
	}
	@Override
	public void wishList(UserInfo userinfo, ApiRecipe apirecipe) {
		long count = apiRecipeDAO.countByRcpSeqByWishList(apirecipe);
		UserWishListApi userwishlistapi = new UserWishListApi();
		userwishlistapi.setRcpSeq(apirecipe);
		userwishlistapi.setUserId(userinfo);
		if (count > 0 ) {
			apiRecipeDAO.deleteWishList(apirecipe);
		} else {
			apiRecipeDAO.addWishList(userwishlistapi);
		}
	}
	@Override
	public void viewCountUp(int rcpSeq) {
		ApiRecipe apirecipe = apiRecipeDAO.findById(rcpSeq);
		apirecipe.viewCountUp(apirecipe);
	}
	
	@Override
	public List<ApiRecipe> selectListByPageAndSort(int page, int pagePerCount, String sortType){
		return apiRecipeDAO.selectListByPageAndSort(page, pagePerCount, sortType);
	}

	@Override
	public List<ApiRecipeReview> findByRcpSeq(int rcpSeq) {
		// TODO Auto-generated method stub
		return apiRecipeDAO.findByRcpSeq(rcpSeq);
	}

	@Override
	public void reviewsave(ApiRecipeReview apirecipereview) {
		apiRecipeDAO.reviewsave(apirecipereview);
		
	}

	@Override
	public List<ApiRecipeReview> getAPIByRecipeNo(ApiRecipe recipe) {
		// TODO Auto-generated method stub
		return apiRecipeDAO.getByRecipeNo(recipe);
	}

}
