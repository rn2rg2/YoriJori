package com.yorijori.foodcode.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.VO.RecipeVO;
import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserTray;
import com.yorijori.foodcode.jpa.entity.UserTrayList;
import com.yorijori.foodcode.repository.IngredientDAO;
import com.yorijori.foodcode.repository.RefriTrayDAO;

@Service
@Transactional
public class RefriTrayServiceImpl implements RefriTrayService {
	RefriTrayDAO rtdao;
	IngredientDAO ingredao;

	@Autowired
	public RefriTrayServiceImpl(RefriTrayDAO rtdao,IngredientDAO ingredao) {
		super();
		this.rtdao = rtdao;
		this.ingredao = ingredao;
	}

	@Override
	public List<UserFrige> selectAll(String userId) {
		List<UserFrige> list = new ArrayList<UserFrige>(); 
		list = rtdao.selectAll(userId);
		System.out.println("===============================");
		System.out.println(list);
		System.out.println(list.size());
		System.out.println("===============================");
		for (UserFrige dto : list) {
			Ingredients ingredto = ingredao.selectByMatlNo(dto.getMatlNo());
			dto.setIngredients(ingredto);
		}
		return list;
	}
	@Override
	public List<UserTray> selectTrayByUserId(String userId) {
		return rtdao.selectTrayByUserId(userId);
	}

	@Override
	public UserTray selectTrayDetail(int trayNo, String userId) {
		return rtdao.selectTrayDetailByUserId(trayNo, userId);
	}
	@Override
	public long countByUserId(String userId) {
		return rtdao.countByUserId(userId);
	}

	@Override
	public void insert(UserFrige userfrige, String userId) {
		rtdao.deleteByUserId(userId);
		List<UserFrige> refrilist = new ArrayList<UserFrige>();
		for ( Ingredients ingre : userfrige.getMatlList()) {
			UserFrige frige = new UserFrige();
			frige.setUserId(userfrige.getUserId());
			frige.setMatlNo(ingre.getMatlNo());
			refrilist.add(frige);
		}
		rtdao.insertAll(refrilist);
	}
	
	@Override
	public void insertTray(UserTray usertray) {
		System.out.println("===================");
		System.out.println(usertray);
		System.out.println("===================");
		for (UserTrayList list : usertray.getTrayList()) {
			list.setTrayNo(usertray);
		}
		rtdao.insertTray(usertray);
	}
	
	
	@Override 
	public RecipeVO getRecommendList(UserInfo user, UserFrige userfrige) {
		return rtdao.findByPreferAndByMatlNo(user, userfrige);
		
	}
	@Override
	public void deleteTray(int trayNo) {
		rtdao.deleteTray(trayNo);
	}
}
