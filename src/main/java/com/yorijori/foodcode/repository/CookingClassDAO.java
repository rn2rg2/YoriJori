package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.dto.CookingClassDTO;

public interface CookingClassDAO {
	public int insert();
	public int delete();
	public List<CookingClassDTO> selectAllClass();
}
