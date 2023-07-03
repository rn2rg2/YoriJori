package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.SearchLog;
import com.yorijori.foodcode.jpa.repository.SearchLogRepository;

@Repository
public class SearchLogDAOImpl implements SearchLogDAO {
	
	SearchLogRepository searchlogrepo;

	@Autowired
	public SearchLogDAOImpl(SearchLogRepository searchlogrepo) {
		super();
		this.searchlogrepo = searchlogrepo;
	}
	
	@Override
	public SearchLog findSearchLog(String keyword) {
		return searchlogrepo.findByKeyword(keyword);
	}
	@Override
	public void insertLog(SearchLog searchlog) {
		searchlogrepo.save(searchlog);
	}
	@Override
	public SearchLog updateLog(SearchLog searchlog) {
		searchlog = searchlogrepo.findByKeyword(searchlog.getKeyword());
		int count = searchlog.getCount();
		searchlog.setCount(count+1);
		return searchlog;
	}
	
	@Override
	public List<SearchLog> findTop10ByCount(){
		return searchlogrepo.findAllTop10ByOrderByCountDesc();
	}
	
	
}
