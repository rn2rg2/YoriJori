package com.yorijori.foodcode.jpa.entity;

import java.util.Date;

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
@Table(name="recipe_qa")
@ToString(exclude = {"recipeNo"})
public class RecipeQa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int qaNo;
	//private int recipeNo;
	//	private String userId;
	private String title;
	private String contents;
	private int depthLevel;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	private int state; //default 0
	
	@ManyToOne
	@JoinColumn(name = "recipeNo", nullable=false)
	private Recipe recipeNo;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable=false)
	private UserInfo userId;
}
