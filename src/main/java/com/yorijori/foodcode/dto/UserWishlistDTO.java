package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWishlistDTO {
	private int wishlist_id; //컬럼명 id / autoincrement
	private String user_id;
	private int recipe_no; // recipeNo
}
