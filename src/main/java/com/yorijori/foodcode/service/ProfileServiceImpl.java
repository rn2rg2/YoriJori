package com.yorijori.foodcode.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.repository.ProfileDAO;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	private ProfileDAO profiledao;
	
	@Autowired
	public ProfileServiceImpl(ProfileDAO profiledao) {
		super();
		this.profiledao = profiledao;
	}
	@Override
	public void updateprofile() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void updateprofile2(UserInfo user_id, String email, String nickname, HttpSession session) {
		profiledao.updateprofile2(user_id, email, nickname, session);
	}

	@Override
	public void updatepassword() {
		// TODO Auto-generated method stub

	}



}
