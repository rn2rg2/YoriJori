package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeImageDTO {
	private int recipe_image_id; //컬렴명은 id
	private int recipe_no;
	private int img_no;
	private String ore_img;//원본 이미지 이름
	private String store_img;// 저장할 이미지 이름
	private String content;
}	
