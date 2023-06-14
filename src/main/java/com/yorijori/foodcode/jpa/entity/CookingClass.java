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
@Table(name="cooking_class")
public class CookingClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cookNo;
	private String userId;
	private String title;
	private String thumbnail;
	private int price;
	private String category;
	private int time;
	private int count;
	private int priceDc;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date up_date;
	private int state;
}
