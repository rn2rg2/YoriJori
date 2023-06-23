package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.UserFrige;

public interface UserFrigeRepository extends JpaRepository<UserFrige, Integer>{
	
	List<UserFrige> findAllByUserId(String userId);
	long countByUserId(String userId);
	void delete(UserFrige userFrige);
	
	List<UserFrige> deleteByUserId(String userId);

}
