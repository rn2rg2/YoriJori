package com.yorijori.foodcode.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.repository.RecipeDAO;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
	RecipeDAO recipeDAO;
	
	@Autowired
	public RecipeServiceImpl(RecipeDAO recipeDAO) {
		super();
		this.recipeDAO = recipeDAO;
	}

	@Override
	public long countAll() {
		return recipeDAO.countAll();
	}
	
	@Override
	public List<Recipe> selectListByPage(int pageNo,int pagePerCount){
		return recipeDAO.selectListByPage(pageNo, pagePerCount);
	}
	
	@Override
	public void wishList(UserInfo userId, Recipe recipeNo) {
		long count = recipeDAO.countByRcpSeqByWishList(recipeNo);
		UserWishlist userwishlist = new UserWishlist();
		userwishlist.setRecipeNo(recipeNo);
		userwishlist.setUserId(userId);
		if (count > 0 ) {
			recipeDAO.deleteWishList(recipeNo);
		} else {
			recipeDAO.addWishList(userwishlist);
		}
	}
	
}
