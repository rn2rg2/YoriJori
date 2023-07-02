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
	public UserInfo updateprofile(UserInfo user_id) {
		return profiledao.updateprofile(user_id);
	}

	@Override
	public UserInfo updateprofileimage(UserInfo user) {
		// TODO Auto-generated method stub
		return profiledao.updateprofileimage(user);
	}
	@Override
	public void updatestate(UserInfo user) {
		profiledao.updatestate(user);
		
	}

	@Override
	public UserInfo readuserinfo(String nickname) {
		return profiledao.readuserinfo(nickname);
	}

	@Override
	public UserInfo updatepassword(UserInfo user) {
		
		return profiledao.updatepassword(user);
	}

	@Override
	public UserInfo checknickname(String nickname) {
		
		return profiledao.checknickname(nickname);
	}



}
