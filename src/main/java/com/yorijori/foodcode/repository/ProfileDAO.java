package com.yorijori.foodcode.repository;

import javax.servlet.http.HttpSession;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ProfileDAO {
	public void updateprofile();
	public void updateprofile2(UserInfo user_id, String email, String nickname, HttpSession session);
	public void updatepassword();
}
