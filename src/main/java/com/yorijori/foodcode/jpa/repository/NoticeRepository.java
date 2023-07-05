package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.yorijori.foodcode.jpa.entity.CustomerService;
import com.yorijori.foodcode.jpa.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice,Integer> {
	
	Page<Notice> findByState(int state, Pageable pageable);
	
	

}
