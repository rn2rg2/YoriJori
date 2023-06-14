package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWishListApi {
	private int id;			//key
	private String userId;	
	private int rcpSeq;		//레시피번호
}
