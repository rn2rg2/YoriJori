package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yorijori.foodcode.dto.UserInfoDTO;

import lombok.Data;

@Data
@Entity
@Table(name="user_info")

public class UserInfo {
	@Id
	private String userId;
	private String role;	
	private String nickname;
	private String pass;
	private String email;
	private String name;
	private Integer phoneNumber;
	private String ssn;
	private String imgPath;
	private Integer point;
	private String prefer;
	private String purpose;
	private String allergy;
	private Integer state;
	private Date date;
	private String kakaoID;
	
	
	public static UserInfo toUserInfo(UserInfoDTO userinfoDTO) {
	    UserInfo userinfo = new UserInfo();
	    userinfo.setUserId(userinfoDTO.getUser_id());
	    userinfo.setRole(userinfoDTO.getRole());
	    userinfo.setNickname(userinfoDTO.getNickname());
	    userinfo.setPass(userinfoDTO.getPass());
	    userinfo.setEmail(userinfoDTO.getEmail());
	    userinfo.setName(userinfoDTO.getName());
	    userinfo.setPhoneNumber(userinfoDTO.getPhone_number());
	    userinfo.setSsn(userinfoDTO.getSsn());
	    userinfo.setImgPath(userinfoDTO.getImg_path());
	    userinfo.setPoint(userinfoDTO.getPoint());
	    userinfo.setPrefer(userinfoDTO.getPrefer());
	    userinfo.setPurpose(userinfoDTO.getPurpose());
	    userinfo.setAllergy(userinfoDTO.getAllergy());
	    userinfo.setState(userinfoDTO.getState());
	    userinfo.setDate(userinfoDTO.getDATE());
	    userinfo.setKakaoID(userinfoDTO.getKakaoID());
	    return userinfo;
	}
}
