package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCategoryDTO {
	private int recipe_category_id; //컬럼명은 id
	private int recipe_no;
	private int category_no;
}
