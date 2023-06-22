package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="community_comment")
public class BoardComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo ;
	//private int  commNo ;
	//private String userId;
	private String contents ;
	@CreationTimestamp
	private Date date ;
	@UpdateTimestamp
	private Date upDate;
	private int state;
	private int groupNo;
	private int displayOrderNo;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userId;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "commNo", nullable = false)
	private Board commNo;
	
}
