package com.yorijori.foodcode.jpa.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface MemberRepository extends JpaRepository<UserInfo, String> {
}