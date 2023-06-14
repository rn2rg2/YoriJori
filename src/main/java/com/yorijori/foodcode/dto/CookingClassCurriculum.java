package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookingClassCurriculum {
	private int id;
	private int cookNo;
	private String name;
	private int time;
	private int cur_no;
}
