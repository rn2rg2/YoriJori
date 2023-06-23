package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.repository.UserFrigeRepository;
import com.yorijori.foodcode.jpa.repository.UserTrayListRepository;
import com.yorijori.foodcode.jpa.repository.UserTrayRepository;

@Repository
public class RefriTrayDAOImpl implements RefriTrayDAO {

	UserFrigeRepository frigerepository;
	UserTrayRepository trayrepository;
	UserTrayListRepository traylistrepository;
	
	@Autowired
	public RefriTrayDAOImpl(UserFrigeRepository frigerepository, UserTrayRepository trayrepository,
			UserTrayListRepository traylistrepository) {
		super();
		this.frigerepository = frigerepository;
		this.trayrepository = trayrepository;
		this.traylistrepository = traylistrepository;
	}
	@Override
	public List<UserFrige> selectAll(String userId){
		return frigerepository.findAllByUserId(userId);
	}
	@Override
	public long countByUserId(String userId) {
		return frigerepository.countByUserId(userId);
	}
	@Override
	public void insertAll(List<UserFrige> userfrigelist) {
		//System.out.println("DAO");
		//System.out.println(board.toString());

		frigerepository.saveAll(userfrigelist);
	}
	
	@Override
	public void deleteByUserId(String userId) {
		//frigerepository.findAllByUserId(userFrige.getUserId());
		//frigerepository.delete(userFrige);
		System.out.println("delet by user id");
		frigerepository.deleteByUserId(userId);
	}
	

	
	
}
