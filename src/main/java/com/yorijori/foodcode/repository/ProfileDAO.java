package com.yorijori.foodcode.repository;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ProfileDAO {
	public void updateprofile();
	
	UserInfo updateprofile(UserInfo user_id);
	public UserInfo updateprofileimage(UserInfo user);
	public void updatestate(UserInfo user);
	public UserInfo readuserinfo(String nickname);
	public UserInfo updatepassword(UserInfo user);
	public UserInfo checknickname(String nickname);
	public UserInfo readuser(String userId);
}
