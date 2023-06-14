package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchLogDTO {
	private String keyword;			// 검색키워드
	private Date registered_at;		// 등록날짜
	private Date updated_at;		// 등록날짜

}
