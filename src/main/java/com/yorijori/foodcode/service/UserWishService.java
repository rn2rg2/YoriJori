package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

public interface UserWishService {

	List<UserWishlist> selectAll(UserInfo userId, int pageNo, int pagePerCount);

	long countAll();

	long countAllByUserId(UserInfo userinfo);

	List<UserWishlist> findRecipeNoByUserId(UserInfo user);


}
