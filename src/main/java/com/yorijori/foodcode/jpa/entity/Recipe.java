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
	@Column(name = "MANUAL01")
	private String manual01;
	private int time;
	private int level;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	private int state;

	@Exclude
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userId;

	@Exclude
	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<RecipeImage> imgList = new ArrayList<RecipeImage>();

	@Exclude
	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RecipeCategory> categoryList = new ArrayList<RecipeCategory>();

	@Exclude
	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RecipeReview> reviewList = new ArrayList<RecipeReview>();

	@Exclude
	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RecipeQa> qaList = new ArrayList<RecipeQa>();

	@Exclude
	@OneToMany(mappedBy = "recipeNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserWishlist> wishlist = new ArrayList<UserWishlist>();

	public void viewCountUp(Recipe recipe) {
		recipe.count++;
	}

}
