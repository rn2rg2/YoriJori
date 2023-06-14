package com.yorijori.foodcode.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.dto.CookingClassDTO;
import com.yorijori.foodcode.jpa.repository.CookingClassRepository;

@Repository
public class CookingClassDAOImpl implements CookingClassDAO {
	private EntityManager entityManager;
	@Autowired
	public CookingClassDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CookingClassDTO> selectAllClass() {
		String jpql="select cookingclass from CookingClassDTO as cookingclass";
		List<CookingClassDTO> classList=entityManager.createQuery(jpql,CookingClassDTO.class).getResultList();
		return classList;
	}

}
