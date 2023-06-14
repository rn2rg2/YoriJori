package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRecipeQaDTO {
	private int qaNo;
	private int rcpSeq;		//레시피번호
	private String userId;
	private String contents;
	private boolean depthLevel;
	private Date date;
	private Date upDate;
	private int state;
}
