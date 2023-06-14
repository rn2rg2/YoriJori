package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogDTO {
	private Date dated; 	// 등록시간
	private String logger;	// 로그파일
	private String level;	// 로그레벨
	private String message;	// 로그메세지

}
