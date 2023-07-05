package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface MemberDAO {
    public UserInfo login(String userId, String userPassword);
    public UserInfo loginKakao(String kakaoID);
    public boolean nicknameCheck(String nickname);
    public boolean idCheck(String userId);
    public void save(UserInfo userInfo);

	void update(UserInfo userInfo);

	long userCount(String role);
	UserInfo findByUserId(String userId);
	List<UserInfo> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType);
	void updateUserStateByUserId(String userId, int state);
	
	List<UserInfo> selectall(int state);
	
	List<Long> countByUserRole(int startRole, int endRole);
	List<Long> countByUserPoint(int startPoint, int endPoint);
	List<UserInfo> getTop10User();
}