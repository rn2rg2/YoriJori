package com.yorijori.foodcode.service;



import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.MemberRepository;


@Service
public class MemberServiceImpl implements MemberService {
	
	
    private MemberRepository memberRepository;
    
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
   
    //회원가입

	@Override
	public void save(UserInfo userinfodto) {
		memberRepository.save(userinfodto);	
		
	}
    // - 회원가입 로직

	
	// - 회원가입 중복처리
	//		- 아이디 중복체크
    public boolean idcheck(String userId) {
        return memberRepository.existsByUserId(userId);
    }
	// - 회원가입 중복처리
	//		- 닉네임 중복체크
    public boolean nicknamecheck(String nickName) {
        return memberRepository.existsByNickname(nickName);
    }
    
    
    // 로그인 로직
    // - 일반로그인 
    
    //		- 방식 
    //			사용자 아이디 비밀번호 받아와 DB조회후 로그인처리
	@Override
	public UserInfo login(String user_id, String userPassword) {
		// TODO Auto-generated method stub
        return memberRepository.findByUserIdAndPass(user_id, userPassword);
	}
    // - 카카오로그인 
    // 		- 방식 
    //			카카오 고유번호 받아와 DB조회후 로그인처리
	@Override
	public UserInfo loginKakao(String kakaoID) {
		// TODO Auto-generated method stub
        return memberRepository.findByKakaoID(kakaoID);
	}
	
}