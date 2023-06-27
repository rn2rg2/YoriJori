package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_log")
public class UserLog{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;
	private String userId;
	@CreationTimestamp
	private Date dated;
	private String logger;
	private String level;
	private String message;
}
