package com.yorijori.foodcode.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.MemberRepository;

@Repository
public class ProfileDAOImpl implements ProfileDAO {
	
	
	private MemberRepository userrepo;
	
	@Autowired
	public ProfileDAOImpl(MemberRepository userrepo) {
		super();
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
		user.setAllergy(user_id.getAllergy());
		return user;
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

	@Override
	public UserInfo readuserinfo(String nickname) {
		UserInfo user = userrepo.findByUserId(nickname);
		return user;
	}

	@Override
	public UserInfo updatepassword(UserInfo user) {
		UserInfo updateuser = userrepo.findByUserId(user.getUserId());
		updateuser.setPass(user.getPass());
		return updateuser;
	}

	@Override
	public UserInfo checknickname(String nickname) {
		UserInfo user = userrepo.findByNickname(nickname);
		return user;
	}

	@Override
	public UserInfo readuser(String userId) {
		// TODO Auto-generated method stub
		return userrepo.findByUserId(userId);
	}
}
