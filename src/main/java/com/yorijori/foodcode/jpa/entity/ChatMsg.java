package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_msg")
public class ChatMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int msgId;			// 메세지ID
	private int chatId;			// 채팅 ID
	private String senderId;		// 메세지를 보낸 사용자
	private String msg;				// 메세지 내용
	@CreationTimestamp
	private Date date ;
}
