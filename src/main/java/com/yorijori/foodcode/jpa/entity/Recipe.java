package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Column;
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
@Table(name="recipe")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recipeNo;
	private String userId;
	private String name ;
	private String des ;
	private int count ;
	private int  totalKcal ;
	@Column(name = "RCP_NA_TIP")
	private String rcpNaTip;
	@Column(name="MANUAL01")
	private String manual01 ;
	private int time ;
	private int level;
	@CreationTimestamp
	private Date date ;
	@UpdateTimestamp
	private Date upDate;
	private int state;
	
}
