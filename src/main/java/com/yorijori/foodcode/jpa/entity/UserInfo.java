package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_info")
@ToString(exclude = {"recipeList","reviewList"})
public class UserInfo {
	@Id
	private String userId;
	private String role;	
	private String nickname;
	private String pass;
	private String email;
	private String name;
	private Integer phoneNumber;
	private String ssn;
	private String imgPath;
	private Integer point;
	private String prefer;
	private String purpose;
	private String allergy;
	private Integer state;
	@CreationTimestamp
	private Date date;
	private String kakaoID;
	
	@OneToMany(mappedBy="userId", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Recipe> recipeList = new ArrayList<Recipe>();
	
	@OneToMany(mappedBy="userId", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RecipeReview> reviewList = new ArrayList<RecipeReview>();
	
}
