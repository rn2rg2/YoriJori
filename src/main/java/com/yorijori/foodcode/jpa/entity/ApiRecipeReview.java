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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="api_recipe_review")
public class ApiRecipeReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo;
	//private String userId;
	//private int rcpSeq;
	private BigDecimal star;
	private String comment;
	@CreationTimestamp
	private Date date;		//작성일
	@UpdateTimestamp
	private Date upDate;	//수정일
	private int state;		//field
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "rcpSeq", nullable=false)
	@JsonBackReference
	private ApiRecipe rcpSeq;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "userId", nullable=false)
	private UserInfo userId;
}
