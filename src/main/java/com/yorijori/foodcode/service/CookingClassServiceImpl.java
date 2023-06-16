package com.yorijori.foodcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;
import com.yorijori.foodcode.repository.CookingClassDAO;

@Service
@Transactional
public class CookingClassServiceImpl implements CookingClassService {
	CookingClassDAO dao;
	@Autowired
	public CookingClassServiceImpl(CookingClassDAO dao) {
		super();
		this.dao = dao;
	}


	@Override
	public void delete(int state,int cookNo) {
		dao.delete(state, cookNo);
	}

	@Override
	public List<CookingClass> selectAllClass() {
		return dao.selectAllClass();
	}

	@Override
	public void insert(CookingClass cookingclass, CookingClassContent content, CookingClassCurriculum curriculum) {
		dao.insertClass(cookingclass);
		dao.insertContent(content);
		dao.insertCurriculum(curriculum);
	}


	@Override
	public void update(CookingClass cookingclass, CookingClassContent content, CookingClassCurriculum curriculum) {
		dao.updateClass(cookingclass);
		dao.updateContent(content);
		dao.updateCurriculum(curriculum);
	}


	@Override
	public CookingClass readClass(int cookNo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CookingClassContent readContent(int cookNo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CookingClassCurriculum readCurriculum(int cookNo) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
