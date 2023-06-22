package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface RefriTrayService {

	List<UserFrige> selectAll(UserInfo userId);

	long countByUserId(UserInfo userId);

}
