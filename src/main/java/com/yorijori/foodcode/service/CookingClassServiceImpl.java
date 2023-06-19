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
	public void delete(int cookNo) {
		dao.delete(cookNo);
	}

	@Override
	public List<CookingClass> selectAllClass() {
		return dao.selectAllClass();
	}

	@Override
	public void insert(CookingClass cookingclass, CookingClassContent content, CookingClassCurriculum curriculum) {
		dao.insertClass(cookingclass);
		
		//컨텐츠 insert
		String[] temp1=content.getContent().split(",");
		String[] temp2=content.getConCategory().split(",");
		for(int i=0;i<temp1.length;i++) {
			content.setCookNo(cookingclass.getCookNo());
			content.setContent(temp1[i]);
			content.setConCategory(temp2[i]);
			dao.insertContent(content);
		}
		
		//커리큘럼 insert
		temp1=curriculum.getCurName().split(",");
		temp2=curriculum.getCurTime().split(",");
		for(int i=0;i<temp1.length;i++)
		{
			curriculum.setCookNo(cookingclass.getCookNo());
			curriculum.setCurName(temp1[i]);
			curriculum.setCurTime(temp2[i]);
			curriculum.setCurNo(i+1);
			dao.insertCurriculum(curriculum);
		}
		curriculum.setCookNo(cookingclass.getCookNo());
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
		return dao.readClass(cookNo);
	}


	@Override
	public CookingClassContent readContent(int cookNo) {
		return dao.readContent(cookNo);
	}


	@Override
	public CookingClassCurriculum readCurriculum(int cookNo) {
		return dao.readCurriculum(cookNo);
	}

	

}
