package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
	private int recipe_no;
	private String user_id;
	private String name ;
	private String des ;
	private int count ;
	private int  total_kcal ;
	private String rcp_na_tip ;
	private String time ;
	private String level;
	private String serving;
	private Date date ;
	private Date up_date;
	private int state;
	private String thumbnail;
	
}
