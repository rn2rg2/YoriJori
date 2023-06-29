package com.yorijori.foodcode.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.dto.RecipeDTO;
import com.yorijori.foodcode.jpa.VO.RecipeVO;
import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
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
	EntityManager em;

	@Autowired
	public RefriTrayDAOImpl(UserFrigeRepository frigerepository, UserTrayRepository trayrepository,
			UserTrayListRepository traylistrepository,UserWishlistRepository userwishlistrepo, EntityManager em) {
		super();
		this.frigerepository = frigerepository;
		this.trayrepository = trayrepository;
		this.traylistrepository = traylistrepository;
		this.userwishlistrepo = userwishlistrepo;
		this.em = em;
	}

	@Override
	public List<UserFrige> selectAll(String userId) {
		return frigerepository.findAllByUserId(userId);
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
		// System.out.println("DAO");
		// System.out.println(board.toString());

		frigerepository.saveAll(userfrigelist);
	}

	@Override
	public void deleteByUserId(String userId) {
		// frigerepository.findAllByUserId(userFrige.getUserId());
		// frigerepository.delete(userFrige);
		//System.out.println("delet by user id");
		frigerepository.deleteByUserId(userId);
	}
	
	@Override
	public List<RecipeVO> findByPreferAndByMatlNo(UserInfo user, UserFrige userfrige){
		return frigerepository.searchPreferMatlNo(user.getPrefer(), userfrige.getMatlNo());
	}

}
