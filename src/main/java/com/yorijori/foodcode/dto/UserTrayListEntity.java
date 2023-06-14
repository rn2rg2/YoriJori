package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTrayListEntity {
	private int id;
	private int trayNo;
	private int trayOrder;
	private int recipeNo;
	private int state;
}
