package com.yorijori.foodcode.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeReviewDTO {
	private int comment_no;
	private String user_id;
	private int recipe_no;
	private BigDecimal  star;
	private String comment;
	private Date date;
	private Date up_date;
	private Date state;
}
