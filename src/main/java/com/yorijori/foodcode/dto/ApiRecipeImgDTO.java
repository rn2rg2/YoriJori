package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRecipeImgDTO {
	private int	id;
	private int recipeNo;		//레시피번호
	private int count;		//순서
	private String manual01;	//컨텐츠1
	private String manualImg01;	//이미지1
}
