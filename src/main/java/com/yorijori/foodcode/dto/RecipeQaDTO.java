package com.yorijori.foodcode.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeQaDTO {
	private int qa_no;
	private int recipe_no;
	private String user_id;
	private String contents;
	private boolean depth_level;
	private Date date;
	private Date up_date;
	private int state; //default 0
}
