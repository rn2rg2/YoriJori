package com.yorijori.foodcode.jpa.entity;

import java.util.Date;

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
@Table(name="recipe_qa")
public class RecipeQa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int qaNo;
	private int recipeno;
	private String userId;
	private String contents;
	private boolean depthLevel;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	private int state; //default 0
}
