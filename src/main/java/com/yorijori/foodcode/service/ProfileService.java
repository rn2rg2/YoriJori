package com.yorijori.foodcode.service;

import javax.servlet.http.HttpSession;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ProfileService {
	public void updatepassword();
	UserInfo updateprofile(UserInfo user_id);
	UserInfo updateprofileimage(UserInfo user);
	public void updatestate(UserInfo user);
}
