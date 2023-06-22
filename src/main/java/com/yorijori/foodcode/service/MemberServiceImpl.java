package com.yorijori.foodcode.service;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.repository.MemberDAO;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	MemberDAO memberDAO;
    
    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
   
    //회원가입

	@Override
	public void save(UserInfo userinfodto) {
		memberDAO.save(userinfodto);	
		
	}
    // - 회원가입 로직

	
	// - 회원가입 중복처리
	//		- 아이디 중복체크
    public boolean idcheck(String userId) {
        return memberDAO.idCheck(userId);
    }
	// - 회원가입 중복처리
	//		- 닉네임 중복체크
    public boolean nicknamecheck(String nickName) {
        return memberDAO.nicknameCheck(nickName);
    }
    
    // 로그인 로직
    // - 일반로그인 
    
    //		- 방식 
    //			사용자 아이디 비밀번호 받아와 DB조회후 로그인처리
	@Override
	public UserInfo login(String user_id, String userPassword) {
		// TODO Auto-generated method stub
        return memberDAO.login(user_id, userPassword);
	}
    // - 카카오로그인 
    // 		- 방식 
    //			카카오 고유번호 받아와 DB조회후 로그인처리
	@Override
	public UserInfo loginKakao(String kakaoID) {
		// TODO Auto-generated method stub
        return memberDAO.loginKakao(kakaoID);
	}
	
	//유저 역할별 인원 수 조회
	@Override
	public long userCount(String role) {
		return memberDAO.userCount(role);
	}
	@Override
	public List<UserInfo> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType){
		return memberDAO.selectListByPageAndSort(pageNo, pagePerCount, sortType);
	}


}