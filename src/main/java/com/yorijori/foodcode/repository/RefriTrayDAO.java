package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

public interface RefriTrayDAO {

	List<UserFrige> selectAll(String userId);

	long countByUserId(String userId);

	void insertAll(List<UserFrige> userfrigelist);

	void deleteByUserId(String userId);

	List<UserWishlist> selectWishListALl(int pageNo, String userId, int pagePerCount);

}
