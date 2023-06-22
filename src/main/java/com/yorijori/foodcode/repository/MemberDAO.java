package com.yorijori.foodcode.repository;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface MemberDAO {
    public UserInfo login(String userId, String userPassword);
    public UserInfo loginKakao(String kakaoID);
    public boolean nicknameCheck(String nickname);
    public boolean idCheck(String userId);
    public void save(UserInfo userInfo);
	void update(UserInfo userInfo);
}