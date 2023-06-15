package com.yorijori.foodcode.jpa.repository;
 
import com.yorijori.foodcode.jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
}