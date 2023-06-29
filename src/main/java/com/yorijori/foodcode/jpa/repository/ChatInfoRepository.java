package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.ChatInfo;


@Repository
public interface ChatInfoRepository extends JpaRepository<ChatInfo, Integer> { 
	
	@Query("SELECT c FROM ChatInfo c " + 
			"WHERE c.user1id = :userId "
			+ "OR c.user2id = :userId")
	List<ChatInfo> findAllRoomUserId(@Param(value="userId") String userId);
}

	