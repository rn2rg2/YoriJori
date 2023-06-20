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
	public void insert(CookingClass cookingclass, CookingClassContent content, CookingClassCurriculum curriculum) {
//		cookingclass = dao.insertClass(cookingclass);
		List<CookingClassContent> contentlist = new ArrayList<CookingClassContent>();
		List<CookingClassCurriculum> curriList = new ArrayList<CookingClassCurriculum>();
		//컨텐츠 insert
		
//		System.out.println("--------------------------------");
//		System.out.println(cookingclass);
//		System.out.println("--------------------------------");
		//int cookNo = cookingclass.getCookNo();
		String[] temp1=content.getContent().split(",");
		String[] temp2=content.getConCategory().split(",");
		for(int i=0;i<temp1.length;i++) {
			CookingClassContent dto = new CookingClassContent();
			dto.setCookNo(cookingclass);
			dto.setContent(temp1[i]);
			dto.setConCategory(temp2[i]);
//			content.setContentNo(content.getContentNo()+1);
//			content.setCookNo(cookingclass.getCookNo());
//			content.setContent(temp1[i]);
//			content.setConCategory(temp2[i]);
			contentlist.add(dto);
//			dao.insertContent(dto);
		}
		
		//커리큘럼 insert
		temp1=curriculum.getCurName().split(",");
		temp2=curriculum.getCurTime().split(",");
		for(int i=0;i<temp1.length;i++)
		{
			CookingClassCurriculum dto = new CookingClassCurriculum();
			dto.setCookNo(cookingclass);
			dto.setCurName(temp1[i]);
			dto.setCurTime(temp2[i]);
			curriList.add(dto);
//			curriculum.setId(curriculum.getId()+1);
//			curriculum.setCookNo(cookingclass.getCookNo());
//			curriculum.setCurName(temp1[i]);
//			curriculum.setCurTime(temp2[i]);
//			curriculum.setCurNo(i+1);
//			dao.insertCurriculum(dto);
		}
		cookingclass.setContentList(contentlist);
		cookingclass.setCurriList(curriList);
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

	

}
