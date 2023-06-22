package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface RefriTrayDAO {

	List<UserFrige> selectAll(UserInfo userId);

	long countByUserId(UserInfo userId);

}
