package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.jpa.repository.UserWishlistRepository;

@Repository
public class UserWishDAOImpl implements UserWishDAO {
	UserWishlistRepository userWishListRepo;

	@Autowired
	public UserWishDAOImpl(UserWishlistRepository userWishListRepo) {
		super();
		this.userWishListRepo = userWishListRepo;
	}
	
	@Override
	public List<UserWishlist> selectAll(UserInfo userId, int pageNo,int pagePerCount) {
		// TODO Auto-generated method stub){
		PageRequest pageRequest = PageRequest.of(pageNo,pagePerCount, Sort.by(Sort.Direction.DESC,"id"));
		Page<UserWishlist> page = userWishListRepo.findAllByUserId(userId,pageRequest);
		List<UserWishlist> list = page.getContent();
		return list;
	}
	
	@Override
	public long countAll() {
		return userWishListRepo.count();
	}
	
	@Override
	public List<Recipe> findRecipeNoByUserId(String userId){
		return userWishListRepo.findRecipeNoByUserId(userId);
	}
	@Override
	public List<String> findRcpAndCategory(String userId, int recipeNo){
		return userWishListRepo.findRcpAndCategory(userId, recipeNo);
	}
	
	
	
	
}
