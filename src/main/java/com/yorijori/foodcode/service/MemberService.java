package com.yorijori.foodcode.service;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.dto.UserInfoDTO;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	
    private final MemberRepository userRepository;
    //로그인
    public UserInfo loginUser(String userName, String userPassword) {
        return userRepository.findByUserIdAndPass(userName, userPassword);
    }
    public UserInfo	loginKakao(String kakaoID) {
        return userRepository.findByKakaoID(kakaoID);
    }
    //회원가입
    
	public void save(UserInfoDTO userinfodto) {
		// TODO Auto-generated method stub
		//UserInfo userinfo = UserInfo.toUserInfo(userinfodto);
		//userRepository.save(userinfo);
	}

    

}