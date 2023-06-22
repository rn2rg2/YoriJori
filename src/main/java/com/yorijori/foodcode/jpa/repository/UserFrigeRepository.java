package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface UserFrigeRepository extends JpaRepository<UserFrige, Integer>{
	
	List<UserFrige> findAllByUserId(UserInfo userId);
	long countByUserId(UserInfo userId);

}
