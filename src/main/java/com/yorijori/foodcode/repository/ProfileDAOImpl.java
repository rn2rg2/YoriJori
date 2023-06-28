package com.yorijori.foodcode.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.MemberRepository;

@Repository
public class ProfileDAOImpl implements ProfileDAO {
	
	private EntityManager entitymanager;
	private MemberRepository userrepo;
	
	@Autowired
	public ProfileDAOImpl(EntityManager entitymanager, MemberRepository userrepo) {
		super();
		this.entitymanager = entitymanager;
		this.userrepo = userrepo;
	}

	@Override
	public void updateprofile() { //UserInfoDTO user_id, String email, String nickname
		// TODO Auto-generated method stub
		//UserInfoDTO user = entitymanager.find(UserInfoDTO.class, user_id.getUser_id());
	}
	
	@Override
	public UserInfo updateprofile(UserInfo user_id) {
		UserInfo user = userrepo.findByUserId(user_id.getUserId());
		user.setPrefer(user_id.getPrefer());
		user.setEmail(user_id.getEmail());
		user.setNickname(user_id.getNickname());
		return user;
	}
	
	@Override
	public void updatepassword() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserInfo updateprofileimage(UserInfo user) {
		UserInfo updateuser =  userrepo.findByUserId(user.getUserId());
		updateuser.setImgPath(user.getImgPath());
		updateuser.setPurpose(user.getPurpose());
		return updateuser;
	}

	@Override
	public void updatestate(UserInfo user) {
		UserInfo deleteuser = userrepo.findByUserId(user.getUserId());
		
		deleteuser.setState(user.getState());
	}



}
