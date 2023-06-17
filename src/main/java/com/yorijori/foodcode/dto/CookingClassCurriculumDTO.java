package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookingClassCurriculumDTO {
	private int id;
	private int cookNo;
	private String curName;
	private int curTime;
	private int curNo;
}
