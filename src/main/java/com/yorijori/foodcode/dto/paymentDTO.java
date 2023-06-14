package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class paymentDTO {
	private int paymentId;
	private int cookFormNo;
	private int amount;
	private String method;
	private String date;
	private int state;
}
