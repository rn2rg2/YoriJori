package com.yorijori.foodcode.jpa.VO;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserTrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTrayListResponse {
	private int id;
	private int trayOrder;
	
	private Recipe recipeNo;
	
	public UserTrayListResponse(UserTrayList tray, Recipe recipe) {
		this.id = tray.getId();
		this.trayOrder = tray.getTrayOrder();
		this.recipeNo = recipe;
	}
	
	
	
	
}
