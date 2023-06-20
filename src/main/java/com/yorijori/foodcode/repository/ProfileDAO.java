package com.yorijori.foodcode.repository;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ProfileDAO {
	public void updateprofile();
	public void updatepassword();
	UserInfo updateprofile2(UserInfo user_id, String email, String nickname);
}
