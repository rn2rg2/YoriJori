package com.yorijori.foodcode.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

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
    
    @Override
    public long userCount(String role) {
    	return memberRepository.countByRole(role);
    }
    
    @Override
    public List<UserInfo> selectCookByPage(int pageNo, int pagePerCount, String sortType){
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, sortType));
		Page<UserInfo> page = memberRepository.findAllByRole(pageRequest, "전문가");
		List<UserInfo> list = page.getContent();
		
		return list;

	}
    
  //userId로 UserInfo찾기
    @Override
    public UserInfo findByUserId(String userId) {
       return memberRepository.findByUserId(userId);
    }
    
    @Override
    public List<UserInfo> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType){
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, sortType));
		Page<UserInfo> page = memberRepository.findAllByRole(pageRequest, "회원");
		List<UserInfo> list = page.getContent();
		
		return list;

	}


    @Override
    public void updateUserStateByUserId(String userId, int state) {
        UserInfo user = memberRepository.findByUserId(userId);
        if (user != null) {
            user.setState(state);
            memberRepository.save(user);
        }
    }


	@Override
	public List<UserInfo> selectall(int state) {
		return memberRepository.findByState(state);
	}
	
	@Override
	public List<Long> countByUserRole(int startRole, int endRole) {
	    List<Long> list = new ArrayList<Long>();
	    for (int i = startRole; i <= endRole; i++) {
	        String role;
	        if (i == 1) {
	            role = "회원";
	        } else if (i == 2) {
	            role = "전문가";
	        } else {
	            role = "관리자";
	        }
	        long count = memberRepository.countByRole(role);
	        list.add(count);
	    }
	    return list;
	}
	@Override
	public List<Long> countByUserPoint(int startPoint, int endPoint) {
	    List<Long> list = new ArrayList<Long>();
	    for (int i = startPoint; i <= endPoint; i++) {
	        long count = memberRepository.countByPoint(i);
	        list.add(count);
	    }
	    return list;
	}
	
	@Override
	public List<UserInfo> getTop10User(){
		return memberRepository.findTop10ByOrderByPointDesc();
	}
}
