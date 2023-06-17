package com.yorijori.foodcode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.repository.RefriTrayDAO;

@Service
public class RefriTrayServiceImpl implements RefriTrayService{
	RefriTrayDAO rtdao;
	
	@Override
	public List<UserFrige> selectAll(){
		return rtdao.selectAll();
	};
}
