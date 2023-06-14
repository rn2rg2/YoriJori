package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionFileDTO {
	private int questionNo;
	private String orgFileName;
	private String storeFileName;
	private int fileNo;
}
