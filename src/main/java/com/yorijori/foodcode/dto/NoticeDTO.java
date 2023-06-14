package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
	private int notice_no;
	private String  user_id  ;
	private String title;
	private String contents; 
	private Date date;
	private Date up_date;
	private int state ;
}
