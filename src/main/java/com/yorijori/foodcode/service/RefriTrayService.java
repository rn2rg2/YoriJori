package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.VO.RecipeVO;
import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserTray;

public interface RefriTrayService {

	List<UserFrige> selectAll(String userId);

	long countByUserId(String userId);

	void insert(UserFrige userfrige, String userId);

	RecipeVO getRecommendList(UserInfo user, UserFrige userfrige);

	void insertTray(UserTray usertray);

	List<UserTray> selectTrayByUserId(String userId);

	UserTray selectTrayDetail(int trayNo, String userId);

}
