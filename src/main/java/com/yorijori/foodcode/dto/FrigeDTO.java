package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrigeDTO {
	private String user_id;
	private int matl_no ;
	private Date date;
	private Date up_date ;
}
