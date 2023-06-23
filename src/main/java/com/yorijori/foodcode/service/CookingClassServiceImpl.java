package com.yorijori.foodcode.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;
import com.yorijori.foodcode.jpa.entity.CookingClassImage;
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
	public void insert(CookingClass cookingclass) {
//		cookingclass = dao.insertClass(cookingclass);
		List<CookingClassContent> contentlist = cookingclass.getContentList();
		List<CookingClassCurriculum> curriList = cookingclass.getCurriList();
		//컨텐츠 insert
		
//		System.out.println("--------------------------------");
//		System.out.println(cookingclass);
//		System.out.println("--------------------------------");

		for(int i=0;i<contentlist.size();i++) {
			contentlist.get(i).setCookNo(cookingclass);
		}
		
		//커리큘럼 insert
		for(int i=0;i<curriList.size();i++)
		{
			curriList.get(i).setCookNo(cookingclass);
		}
		dao.insertClassTest(cookingclass);
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
	public List<CookingClassContent> readContent(int cookNo) {
		return dao.readContent(cookNo);
	}


	@Override
	public List<CookingClassCurriculum> readCurriculum(int cookNo) {
		return dao.readCurriculum(cookNo);
	}


	@Override
	public void insertImage(CookingClassImage image) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<CookingClass> findTop5ByOrderByCount() {
		return dao.findTop5ByOrderByCount();
	}

	

}
