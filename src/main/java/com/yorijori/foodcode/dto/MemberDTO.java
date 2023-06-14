package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private String user_id;
	private String role;
	private String nickname;
	private String pass;
	private String email;
	private String name;
	private int phone_number;
	private int ssn;
	private String prefer;
	private String purpose;
	private boolean status;
	private Date registered_at;
}
