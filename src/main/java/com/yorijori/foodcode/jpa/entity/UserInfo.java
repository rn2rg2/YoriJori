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
@Entity
@Table(name="user_info")

public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String userId;
	private String role;
	private String nickname;
	private String pass;
	private String email;
	private String name;
	private int phoneNumber;
	private String ssn;
	private String imgPath;
	private int point;
	private String prefer;
	private String purpose;
	private String allergy;
	private int state;
	private Date date;
	private int kakaoID;
}
