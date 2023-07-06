package com.yorijori.foodcode.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.InquiryComment;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface InquiryCommentRepository extends JpaRepository<InquiryComment, Integer> {

	Page<InquiryComment> findByState(int state, Pageable pageable);
	
	//List<InquiryComment> findByUserId(UserInfo user);
	
	List<InquiryComment> findByInquiryNo(int inquiryNo);
}
