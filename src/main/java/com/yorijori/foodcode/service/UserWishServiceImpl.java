package com.yorijori.foodcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.repository.UserWishDAO;

@Service
public class UserWishServiceImpl implements UserWishService {
	UserWishDAO userWishDAO;
	
	@Autowired
	public UserWishServiceImpl(UserWishDAO userWishDAO) {
		super();
		this.userWishDAO = userWishDAO;
	}


	@Override
	public List<UserWishlist> selectAll (UserInfo userId, int pageNo, int pagePerCount){
		return userWishDAO.selectAll(userId, pageNo,pagePerCount);
	}
	
	@Override
	public long countAll() {
		return userWishDAO.countAll();
	}

}
