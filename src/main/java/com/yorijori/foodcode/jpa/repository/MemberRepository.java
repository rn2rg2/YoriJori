package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.VO.UserForChatVO;
import com.yorijori.foodcode.jpa.entity.UserInfo;

@Repository
public interface MemberRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserIdAndPass(String user_id, String pass);
    UserInfo findByKakaoID(String kakaoID);
    UserInfo findByUserId(String userId);    
    UserInfo findByNickname(String nickname);
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    long countByRole(String role);
    long countByPoint(int point);

    
    Page<UserInfo> findAllByRole( Pageable pageable, String role);
    List<UserInfo> findByState(int state);
    
    @Query(value="SELECT u.user_id, u.nickname, u.img_path " + 
    		"FROM user_info u " + 
    		"WHERE u.user_id = :userId", nativeQuery = true)
    UserForChatVO getUserForChat(@Param("userId") String userId);
    
    List<UserInfo> findTop10ByOrderByPointDesc();
    
}