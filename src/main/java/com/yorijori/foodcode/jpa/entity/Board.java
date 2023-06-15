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
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comm_no;
	private String user_id;
	private String title;
	private String contents;
	private String category;
	private String view ;
	@CreationTimestamp
	private Date date ;
	@UpdateTimestamp
	private Date up_date;
	private int state;

}

