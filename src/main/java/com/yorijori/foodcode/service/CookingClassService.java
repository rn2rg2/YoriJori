package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;
import com.yorijori.foodcode.jpa.entity.CookingClassForm;
import com.yorijori.foodcode.jpa.entity.CookingClassImage;
import com.yorijori.foodcode.jpa.entity.Payment;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface CookingClassService {
	public void insert(CookingClass cookingclass); //파라미터 추가
	public void delete(int cookNo); //파라미터 추가
	public void update(CookingClass cookingclass,CookingClassContent content,CookingClassCurriculum curriculum);
	public CookingClass readClass(int cookNo);
	public List<CookingClassContent> readContent(int cookNo);
	public List<CookingClassCurriculum> readCurriculum(int cookNo);
	public void insertImage(CookingClassImage image);
	public List<CookingClass> findTop5ByOrderByCount();
	public long countAll();
	List<CookingClass> selectByPageAndpagePerCount(int pageNo, int pagePerCount);
	void formInsert(CookingClassForm form);
	CookingClass findById(Integer cookNo);
	public List<CookingClass> selectAllClass(int state);
	void restore(int cookNo);
	List<CookingClass> findByUserId(UserInfo userId);
}
