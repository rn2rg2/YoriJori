package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardCommentDTO {
	private int comment_no ;
	private int  comm_no ;
	private String user_id;
	private String contents ;
	private Date date ;
	private Date up_date;
	private int state ;
}
