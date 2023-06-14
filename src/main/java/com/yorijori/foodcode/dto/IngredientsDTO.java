package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsDTO {
	private int matlNo;
	private String matlCode;
	private String matlName;
	private String category;
	private String energy;
	private String moisture;
	private String protein;
	private String fat;
	private String ash;
	private String carbs;
	private String totalSugar;
	private String imgPath;
}
