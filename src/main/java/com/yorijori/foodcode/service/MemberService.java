package com.yorijori.foodcode.service;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface MemberService {
	public UserInfo login(String user_id, String userPassword);
	public UserInfo loginKakao(String kakaoID);
	public boolean nicknamecheck(String nickName);   
	public boolean idcheck(String userId);   
	public void save(UserInfo userinfodto);
	long userCount(String role);
}