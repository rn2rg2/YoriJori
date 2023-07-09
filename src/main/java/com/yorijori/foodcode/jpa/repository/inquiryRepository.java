package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Inquiry;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface inquiryRepository extends JpaRepository<Inquiry, Integer>{

	Page<Inquiry> findByState(int state, Pageable pageable);
	
	List<Inquiry> findByUserIdAndState(UserInfo user, int state);
	
	Inquiry findByInquiryNo(int inquiryNo);

}
