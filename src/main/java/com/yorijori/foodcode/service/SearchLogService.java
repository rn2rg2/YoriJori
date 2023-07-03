package com.yorijori.foodcode.service;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.SearchLog;

public interface SearchLogService {

	void insertLog(SearchLog searchlog);

	List<SearchLog> findTop10ByCount();

	SearchLog findSearchLog(String keyword);

}
