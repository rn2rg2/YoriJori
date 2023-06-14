package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsgDTO {
	private String msg_id;			// 메세지ID
	private String chat_id;			// 채팅 ID
	private String sender_id;		// 메세지를 보낸 사용자
	private String msg;				// 메세지 내용
	private Date date;				// 등록날짜
}
