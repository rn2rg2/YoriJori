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
@Table(name="usertray")
public class UserTray{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trayNo;
	private String userId;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
}
