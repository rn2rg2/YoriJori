package com.yorijori.foodcode.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRecipeReviewDTO {
	private int commentNo;
	private String userId;
	private int rcpSeq;
	private BigDecimal star;
	private String comment;
	private Date date;		//작성일
	private Date upDate;	//수정일
	private Date state;		//field
}
