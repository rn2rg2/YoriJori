package com.yorijori.foodcode.service;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.repository.MemberDAO;
import com.yorijori.foodcode.repository.RecipeDAO;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	MemberDAO memberDAO;
	RecipeDAO recipeDAO;
    
    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO,RecipeDAO recipeDAO) {
        this.memberDAO = memberDAO;
        this.recipeDAO = recipeDAO;
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


    @Override
    public void updateUserStateByUserId(String userId, int state) {
        memberDAO.updateUserStateByUserId(userId, state);
    }


	@Override
	public List<UserInfo> selectall(int state) {
		// TODO Auto-generated method stub
		return memberDAO.selectall(state);
	}

	@Override
	public List<Long> countByUserRole(int startRole, int endRole) {
		// TODO Auto-generated method stub
		return memberDAO.countByUserRole(startRole, endRole);
	}

	@Override
	public List<Long> countByUserPoint(int startPoint, int endPoint) {
		// TODO Auto-generated method stub
		return memberDAO.countByUserPoint(startPoint, endPoint);
	}
	
	@Override
	public  List<UserInfo> getTop10User(){
		return memberDAO.getTop10User();
	}
	
	@Override
	public List<UserInfo> getCookUser(int pageNo, int pagePerCount, String sortType){
		return memberDAO.selectCookByPage(pageNo, pagePerCount, sortType);
	}
	
	@Override
	public UserInfo updatePoint(UserInfo userInfo) {
		long count = recipeDAO.countByUserId(userInfo);
		if (count >= 10) {
			List<Recipe> list = recipeDAO.findByUserId(userInfo);
			int size = 0;
			float sum = 0;
			for ( Recipe rcp : list) {
				BigDecimal avg = rcp.getAverage();
				size ++;
				sum += avg.floatValue();
			}
			int result = (int) (sum / size);
			System.out.println("==================point avg ==========================");
			System.out.println(result);
			System.out.println("==================point avg ==========================");
			userInfo.setPoint(result);
			if ( result >= 4 ) {
				userInfo.setRole("전문가");
			} else {
				userInfo.setRole("회원");
			}
			userInfo = memberDAO.update(userInfo);
		}
		return userInfo;
	}

}