package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recipeNo;
	private String name;
	private String des;
	private int count;
	private int totalKcal;
	@Column(name = "RCP_NA_TIP")
	private String rcpNaTip;
	private String serving;
	private String time;
	private String level;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	private int state;
	private String thumbnail;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	@Exclude
	@JsonBackReference
	private UserInfo userId;
	
	public String getUserNickname() {
		
	    return userId.getNickname();
	}
	public String getUserImgPath() {
		
	    return userId.getImgPath();
	}

	
	
	
	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Exclude
	@JsonManagedReference 
	private List<RecipeImage> imglist = new ArrayList<RecipeImage>();

	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Exclude
	@JsonManagedReference
	private List<RecipeCategory> categorylist = new ArrayList<RecipeCategory>();

	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Exclude
	@JsonManagedReference
	private List<RecipeReview> reviewlist = new ArrayList<RecipeReview>();

	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Exclude
	@JsonManagedReference
	private List<RecipeQa> qalist = new ArrayList<RecipeQa>();

	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Exclude
	@JsonBackReference  
	private List<UserWishlist> wishlist = new ArrayList<UserWishlist>();
	
	@OneToMany(mappedBy="recipeNo", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	@Exclude
	@JsonManagedReference
	private List<RecipeIngredients> ingrelist = new ArrayList<RecipeIngredients>();

	public void viewCountUp(Recipe recipe) {
		recipe.count++;
	}
	

}