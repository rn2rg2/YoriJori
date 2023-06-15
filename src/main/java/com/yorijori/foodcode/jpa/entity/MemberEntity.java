package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="user_info")
@Entity
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private String user_id;
	@Column
	private String role;
	@Column
	private String nickname;
	@Column(name = "pass")	
	private String pass;
	@Column
	private String email;
	@Column
	private String name;
	@Column
	private int phone_number;
	@Column
	private String ssn;
	@Column
	private String img_path;
	@Column
	private int point;
	@Column
	private String prefer;
	@Column
	private String purpose;
	@Column
	private String allergy;
	@Column
	private int state;
	@Column
	private Date date;
	@Column
	private int kakaoID;
}
