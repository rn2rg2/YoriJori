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
@Table(name="community_comment")
public class BoardComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comment_no ;
	private int  comm_no ;
	private String user_id;
	private String contents ;
	@CreationTimestamp
	private Date date ;
	@UpdateTimestamp
	private Date up_date;
	private int state ;
}
