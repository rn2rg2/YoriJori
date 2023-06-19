package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookingClassImageDTO {
	private int id;
	private int contentNo;
	private int cookNo;
	private String image;
	private String imageStore;
	private String imgCategory;
	private int imageNo;
}
