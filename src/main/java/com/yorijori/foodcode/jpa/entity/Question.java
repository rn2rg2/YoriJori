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
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionNo;
	//private String  user_id;
	private String title;
	private String contents; 
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	private int state ;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userId;
}
