package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookingClassFormDTO {
	private int cookFormNo;
	private int cookNo;
	private String userId;
	private String paymentMethod;
	private String date;
	private String upDate;
	private int state;
}
