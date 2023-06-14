package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
	private int categoryNo;		//카테고리번호
	private String name;		//카테고리명
	private int level;		//카테고리레벨
	private String upperLevel;		//카테고리상위폴더
}
