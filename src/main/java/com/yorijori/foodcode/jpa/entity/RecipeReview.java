package com.yorijori.foodcode.jpa.entity;

import java.math.BigDecimal;
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
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recipe_review")
@ToString(exclude = {"recipeNo","userId"})
public class RecipeReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo;
	//private String userId;
	//private int recipeNo;
	private BigDecimal star;
	private String comment;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	private int state;
	
	@ManyToOne
	@JoinColumn(name = "recipeNo")
	private Recipe recipeNo;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable=false)
	private UserInfo userId;
}
