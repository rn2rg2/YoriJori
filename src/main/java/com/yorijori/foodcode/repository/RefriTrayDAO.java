package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.VO.RecipeVO;
import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserTray;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

public interface RefriTrayDAO {

	List<UserFrige> selectAll(String userId);

	long countByUserId(String userId);

	void insertAll(List<UserFrige> userfrigelist);

	void deleteByUserId(String userId);

	List<UserWishlist> selectWishListALl(int pageNo, String userId, int pagePerCount);

	RecipeVO findByPreferAndByMatlNo(UserInfo user, UserFrige userfrige);

	void insertTray(UserTray usertray);

	List<UserTray> selectTrayByUserId(String userId);

	UserTray selectTrayDetailByUserId(int trayNo, String userId);

	void deleteTray(int trayNo);

}
