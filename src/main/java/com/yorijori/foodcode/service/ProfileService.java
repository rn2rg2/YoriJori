package com.yorijori.foodcode.service;

import javax.servlet.http.HttpSession;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ProfileService {
	UserInfo updateprofile(UserInfo user_id);
	UserInfo updateprofileimage(UserInfo user);
	public void updatestate(UserInfo user);
	public UserInfo readuserinfo(String nickname);
	public UserInfo updatepassword(UserInfo user);
	UserInfo checknickname(String nickname);
}
