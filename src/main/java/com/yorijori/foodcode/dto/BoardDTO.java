package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private int comment_no;
	private int comm_no;
	private String user_id;
	private String title;
	private String contents;
	private String category;
	private String view ;
	private Date date ;
	private Date up_date;
	private int state;
	private int group_no;
	private int display_order_no;
}
