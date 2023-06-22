package com.yorijori.foodcode.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.repository.RefriTrayDAO;

@Service
@Transactional
public class RefriTrayServiceImpl implements RefriTrayService{
	RefriTrayDAO rtdao;
	
	@Autowired
	public RefriTrayServiceImpl(RefriTrayDAO rtdao) {
		super();
		this.rtdao = rtdao;
	}

	@Override
	public List<UserFrige> selectAll(UserInfo userId){
		return rtdao.selectAll(userId);
	}
	@Override
	public long countByUserId(UserInfo userId) {
		return  rtdao.countByUserId(userId);
	}
}
