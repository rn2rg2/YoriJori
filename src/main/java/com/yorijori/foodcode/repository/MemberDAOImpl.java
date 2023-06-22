package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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


    
    @Override
    public long userCount(String role) {
    	return memberRepository.countByRole(role);
    }
    
  //userId로 UserInfo찾기
    @Override
    public UserInfo findByUserId(String userId) {
       return memberRepository.findByUserId(userId);
    }
    
    @Override
    public List<UserInfo> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType){
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, sortType));
		Page<UserInfo> page = memberRepository.findAll(pageRequest);
		List<UserInfo> list = page.getContent();
		
		return list;

	}
}
