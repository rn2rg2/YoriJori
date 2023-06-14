package com.yorijori.foodcode.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	USE(0,"사용중"),
	NOTUSE(1,"사용 해지"),
	STOP(2,"정지"),
	PERMISSION(3,"허가 요청중");
	
	private int code;
	private String value;
	
}
