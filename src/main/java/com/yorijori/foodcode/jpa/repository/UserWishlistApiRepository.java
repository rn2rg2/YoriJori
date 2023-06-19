package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.UserWishListApi;

public interface UserWishlistApiRepository extends JpaRepository<UserWishListApi, Integer> {
	void deleteByRcpSeq(ApiRecipe rcpSeq);
	long countByRcpSeq(ApiRecipe rcpSeq);
}
