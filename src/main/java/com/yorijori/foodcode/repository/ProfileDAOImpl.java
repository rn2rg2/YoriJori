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
	public UserInfo updateprofile2(UserInfo user_id, String email, String nickname) {
		UserInfo user = entitymanager.find(UserInfo.class, user_id.getUserId());
		user.setEmail(email);
		user.setNickname(nickname);
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
		if(user.getImgPath()!=null) {
		updateuser.setPurpose(user.getPurpose());
		}
		return updateuser;
	}



}
