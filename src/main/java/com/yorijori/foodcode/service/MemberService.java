package com.yorijori.foodcode.service;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface MemberService {
	UserInfo login(String user_id, String userPassword);
	UserInfo loginKakao(String kakaoID);
	boolean nicknamecheck(String nickName);   
	boolean idcheck(String userId);   
	void save(UserInfo userinfodto);
}