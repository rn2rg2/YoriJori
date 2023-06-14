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
	private String RCP_NA_TIP ;
	private String MANUAL01 ;
	private int time ;
	private int level;
	private Date date ;
	private Date up_date;
	private int state;
	
}
