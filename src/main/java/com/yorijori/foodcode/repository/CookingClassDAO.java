package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;
import com.yorijori.foodcode.jpa.entity.CookingClassForm;
import com.yorijori.foodcode.jpa.entity.CookingClassImage;
import com.yorijori.foodcode.jpa.entity.Payment;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface CookingClassDAO {
	public void insertImage(CookingClassImage image);
	public void delete(int cookNo);
	public void updateClass(CookingClass cookingclass);
	public void updateContent(CookingClassContent content);
	public void updateCurriculum(CookingClassCurriculum curriculum);
	public CookingClass readClass(int cookNo);
	public List<CookingClassContent> readContent(int cookNo);
	public List<CookingClassCurriculum> readCurriculum(int cookNo);
	void insertClassTest(CookingClass cookingclass);
	public List<CookingClass> findTop5ByOrderByCount();
	public long countAll();
	public void formInsert(CookingClassForm form);
	public CookingClass findById(Integer cookNo);
	public List<CookingClass> selectAllClass(int state);
	void restore(int cookNo);
	List<CookingClass> findByUserId(UserInfo userId);

	public List<CookingClass> classlistByPage(int pageNo, int pagePerCount, UserInfo user);

	long countByUserId(UserInfo user);
	List<CookingClass> selectBySearch(int pageNo, String searchData, int pagePerCount);
	long countByTitleContaining(String name);
	List<CookingClass> selectByPageAndpagePerCount(int pageNo);
	List<CookingClass> selectBySort(int pageNo, String sort);
	List<CookingClass> selectBySortAndCategory(int pageNo, String sort, String category);

	
}
