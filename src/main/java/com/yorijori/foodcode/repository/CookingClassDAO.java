package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;
import com.yorijori.foodcode.jpa.entity.CookingClassForm;
import com.yorijori.foodcode.jpa.entity.CookingClassImage;
import com.yorijori.foodcode.jpa.entity.Payment;

public interface CookingClassDAO {
	public void insertImage(CookingClassImage image);
	public void delete(int cookNo);
	public void updateClass(CookingClass cookingclass);
	public void updateContent(CookingClassContent content);
	public void updateCurriculum(CookingClassCurriculum curriculum);
	public List<CookingClass> selectAllClass();
	public CookingClass readClass(int cookNo);
	public List<CookingClassContent> readContent(int cookNo);
	public List<CookingClassCurriculum> readCurriculum(int cookNo);
	void insertClassTest(CookingClass cookingclass);
	public List<CookingClass> findTop5ByOrderByCount();
	public long countAll();
	List<CookingClass> selectByPageAndpagePerCount(int pageNo, int pagePerCount);
	void formInsert(CookingClassForm form);
	CookingClass findById(Integer cookNo);
	
}
