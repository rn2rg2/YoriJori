package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;

public interface CookingClassService {
	public void insert(CookingClass cookingclass,CookingClassContent content,CookingClassCurriculum curriculum); //파라미터 추가
	public void delete(int cookNo); //파라미터 추가
	public void update(CookingClass cookingclass,CookingClassContent content,CookingClassCurriculum curriculum);
	public List<CookingClass> selectAllClass();
	public CookingClass readClass(int cookNo);
	public CookingClassContent readContent(int cookNo);
	public CookingClassCurriculum readCurriculum(int cookNo);
}
