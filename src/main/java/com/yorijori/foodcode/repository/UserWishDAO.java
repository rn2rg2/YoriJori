package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

public interface UserWishDAO {

	List<UserWishlist> selectAll(UserInfo userId, int pageNo, int pagePerCount);

}
