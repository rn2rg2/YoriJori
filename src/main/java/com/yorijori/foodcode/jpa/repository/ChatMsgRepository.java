package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.ChatMsg;


@Repository
public interface ChatMsgRepository extends JpaRepository<ChatMsg, Integer> { 
	List<ChatMsg> findAllByChatId(int chatId);
}

	