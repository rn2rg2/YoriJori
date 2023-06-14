package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDTO {
	private int inquiryNo;
	private String userId;
	private String title;
	private String contents;
	private Date date;
	private Date upDate;
	private int state;
}
