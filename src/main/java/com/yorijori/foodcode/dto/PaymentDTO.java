package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	private int paymentId;
	private int cookFormNo;
	private int amount;
	private String method;
	private Date date;
	private int state;
}
