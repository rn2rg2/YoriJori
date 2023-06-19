package com.yorijori.foodcode.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.dto.UserInfoDTO;
import com.yorijori.foodcode.jpa.entity.UserInfo;

@Repository
public class ProfileDAOImpl implements ProfileDAO {
	
	private EntityManager entitymanager;
	
	
	@Autowired
	public ProfileDAOImpl(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	@Override
	public void updateprofile() { //UserInfoDTO user_id, String email, String nickname
		// TODO Auto-generated method stub
		//UserInfoDTO user = entitymanager.find(UserInfoDTO.class, user_id.getUser_id());
	}
	@Override
	public void updateprofile2(UserInfo user_id, String email, String nickname) {
		UserInfo user = entitymanager.find(UserInfo.class, user_id.getUserId());
		System.out.println("수정직전의 user 정보 : "+user);
		
		System.out.println(email+" "+nickname);
		user.setEmail(email);
		user.setNickname(nickname);
		
		System.out.println("수정직후의 user 정보 : "+user);
	}
	
	@Override
	public void updatepassword() {
		// TODO Auto-generated method stub
		
	}



}
