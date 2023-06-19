package com.yorijori.foodcode.service;

import javax.servlet.http.HttpSession;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface ProfileService {
	public void updateprofile(); //사진과 요리 목적 변경
	public void updateprofile2(UserInfo user_id, String email, String nickname,HttpSession session); //닉네임, 이멜, 선호, 알러지 변경
	public void updatepassword();
}
