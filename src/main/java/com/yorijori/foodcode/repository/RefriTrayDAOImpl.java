package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.VO.RecipeVO;
import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserTray;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.jpa.repository.UserFrigeRepository;
import com.yorijori.foodcode.jpa.repository.UserTrayListRepository;
import com.yorijori.foodcode.jpa.repository.UserTrayRepository;
import com.yorijori.foodcode.jpa.repository.UserWishlistRepository;

@Repository
public class RefriTrayDAOImpl implements RefriTrayDAO {

	UserFrigeRepository frigerepository;
	UserTrayRepository trayrepository;
	UserTrayListRepository traylistrepository;
	UserWishlistRepository userwishlistrepo;

	@Autowired
	public RefriTrayDAOImpl(UserFrigeRepository frigerepository, UserTrayRepository trayrepository,
			UserTrayListRepository traylistrepository,UserWishlistRepository userwishlistrepo) {
		super();
		this.frigerepository = frigerepository;
		this.trayrepository = trayrepository;
		this.traylistrepository = traylistrepository;
		this.userwishlistrepo = userwishlistrepo;
	}

	@Override
	public List<UserFrige> selectAll(String userId) {
		return frigerepository.findAllByUserId(userId);
	}
	@Override
	public List<UserTray> selectTrayByUserId(String userId) {
		return trayrepository.findAllByUserId(userId);
	}
	@Override
	public UserTray selectTrayDetailByUserId(int trayNo,String userId) {
		return trayrepository.findByTrayNoAndUserId(trayNo,userId);
	}

	@Override
	public long countByUserId(String userId) {
		return frigerepository.countByUserId(userId);
	}

	@Override
	public List<UserWishlist> selectWishListALl(int pageNo,String userId, int pagePerCount) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, userId));
		Page<UserWishlist> page = userwishlistrepo.findAll(pageRequest);
		List<UserWishlist> list = page.getContent();
		return list;
	}

	@Override
	public void insertAll(List<UserFrige> userfrigelist) {
		frigerepository.saveAll(userfrigelist);
	}
	@Override
	public void insertTray(UserTray usertray) {
		trayrepository.save(usertray);
	}

	@Override
	public void deleteByUserId(String userId) {
		// frigerepository.findAllByUserId(userFrige.getUserId());
		// frigerepository.delete(userFrige);
		//////System.out.println("delet by user id");
		frigerepository.deleteByUserId(userId);
	}
	
	@Override
	public RecipeVO findByPreferAndByMatlNo(String prefer, UserFrige userfrige){
		return frigerepository.searchPreferMatlNo(prefer, userfrige.getMatlNo());
	}
	
	@Override
	public void deleteTray(int trayNo) {
		UserTray tray = new UserTray();
		tray.setTrayNo(trayNo);
		trayrepository.delete(tray);
	}

}
