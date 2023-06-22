package com.yorijori.foodcode.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.MemberRepository;

@Repository
public class MemberDAOImpl implements MemberDAO {
    MemberRepository memberRepository;

    @Autowired
    public MemberDAOImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void save(UserInfo userInfo) {
        memberRepository.save(userInfo);
    }

    @Override
    public boolean idCheck(String userId) {
        return memberRepository.existsByUserId(userId);
    }

    @Override
    public boolean nicknameCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public UserInfo login(String userId, String userPassword) {
        return memberRepository.findByUserIdAndPass(userId, userPassword);
    }

    @Override
    public UserInfo loginKakao(String kakaoID) {
        return memberRepository.findByKakaoID(kakaoID);
        
    }
	@Override
	public void update(UserInfo userInfo) {
		// TODO Auto-generated method stub

	}
}
