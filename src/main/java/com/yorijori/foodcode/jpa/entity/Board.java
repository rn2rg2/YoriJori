package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="community_info")
//@DynamicInsert
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commNo;
	private String userId;
	private String title;
	private String contents;
	private String category;
	//@ColumnDefault("0")
	private Integer view=0 ;
	@CreationTimestamp
	private Date date ;
	@UpdateTimestamp
	private Date upDate;
	//default =0 삭제가 =1  1일때 따로 표시하게!! 삭제된 게시물이라는것을 보여주기
	//타임리프 조건문 으로 일때 style 따로 주기
	private int state;


}

