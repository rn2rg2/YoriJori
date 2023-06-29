package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_info")
public class ChatInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String chatId;			// 채팅 ID
	private String user1id;		// 채팅유저1
	private String user2id;		// 채팅유저2
	@CreationTimestamp
	private Date chetDate;				// 채팅방 생성시간
	@UpdateTimestamp
	private Date upDate;	
}
