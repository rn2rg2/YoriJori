package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookingClassDTO {
	private int cookNo;
	private String userId;
	private String title;
	private String thumbnail;
	private int price;
	private String category;
	private int time;
	private int count;
	private int priceDc;
	private Date date;
	private Date up_date;
	private int state;
}
