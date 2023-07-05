package com.yorijori.foodcode.jpa.VO;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.yorijori.foodcode.jpa.entity.ChatInfo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserForChatResponse {
	private String chatId;			// 채팅 ID
	//private String user1id;		// 채팅유저1
	//private String user2id;		// 채팅유저2
	private LocalDateTime chetDate;				// 채팅방 생성시간
	private LocalDateTime upDate;	
	private String userId; 
	private String nickname;
	private String imgPath;
	
	public UserForChatResponse(ChatInfo info) {
		this.chatId = info.getChatId();
		//this.user1id = info.getUser1id();
		//this.user2id = info.getUser2id();
		this.chetDate = info.getChetDate();
		this.upDate = info.getUpDate();
	}
	public void setUserForChat(UserForChatVO vo){
		this.userId = vo.getUser_id();
		this.nickname = vo.getNickname();
		this.imgPath = vo.getImg_path();
	}
	
}
