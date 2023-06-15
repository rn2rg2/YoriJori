package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.dto.CookingClassDTO;

public interface CookingClassService {
	public int insert(); //파라미터 추가
	public int delete(); //파라미터 추가
	public List<CookingClassDTO> selectAllClass();
}
