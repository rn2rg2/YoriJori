package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeFileDTO {
	private int  notice_no ;
	private String  org_fileName  ;
	private String  store_fileName  ;
	private int  file_no  ;
		
}
