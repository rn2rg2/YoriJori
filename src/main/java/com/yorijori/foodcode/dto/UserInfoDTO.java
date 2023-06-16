package com.yorijori.foodcode.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDTO {
	private String user_id;		// 유저아이디
	private String role; 		// 구분 - 회원/전문가/관리자
	private String nickname;	// 유저닉네임
	private String pass;		// 유저 비밀번호
	private String email;		// 유저 이메일
	private String name;		// 유저 이름
	private Integer phone_number;	// 휴대폰번
	private String ssn;			// 주민등록번호
	private String img_path;	// 사진 경로
	private Integer point;			// 유저 포인트
	private String prefer;		// 사용자 선호식품
	private String purpose;		// 사용자 요리목적
	private String allergy;		// 사용자 알레르기
	private Integer state;			// 사용자 상태
	private Date DATE;			// 사용자 가입날짜
	private String kakaoID;
	
	
}
