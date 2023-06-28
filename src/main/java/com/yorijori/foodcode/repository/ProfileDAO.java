package com.yorijori.foodcode.repository;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ProfileDAO {
	public void updateprofile();
	public void updatepassword();
	UserInfo updateprofile(UserInfo user_id);
	public UserInfo updateprofileimage(UserInfo user);
	public void updatestate(UserInfo user);
}
