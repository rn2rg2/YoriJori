package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.UserTray;

public interface UserTrayRepository extends JpaRepository<UserTray, Integer>{
	List<UserTray> findAllByUserId(String userId);
	UserTray findByTrayNoAndUserId(int trayNo, String userId);

}
