package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.SearchLog;

public interface SearchLogDAO {

	void insertLog(SearchLog searchlog);

	SearchLog updateLog(SearchLog searchlog);

	List<SearchLog> findTop10ByCount();

	SearchLog findSearchLog(String keyword);

}
