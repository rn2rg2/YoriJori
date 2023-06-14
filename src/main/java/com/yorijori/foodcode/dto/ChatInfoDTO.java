package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatInfoDTO {
	private String chat_id;			// 채팅 ID
	private String user1_id;		// 채팅유저1
	private String user2_id;		// 채팅유저2
	private Date chet_date;				// 채팅방 생성시간
//	private Date up_date;			보류

}
