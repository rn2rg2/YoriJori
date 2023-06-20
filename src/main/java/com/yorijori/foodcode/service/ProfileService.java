package com.yorijori.foodcode.service;

import javax.servlet.http.HttpSession;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ProfileService {
	public void updateprofile(); //사진과 요리 목적 변경
	public void updatepassword();
	UserInfo updateprofile2(UserInfo user_id, String email, String nickname);
}
