package com.yorijori.foodcode.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ingredients")
public class Ingredients{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int matlNo;
	private String matlCode;
	private String matlName;
	private String category;
	private String energy;
	private String moisture;
	private String protein;
	private String fat;
	private String ash;
	private String carbs;
	private String totalSugar;
	private String imgPath;
	
	@Exclude
	@OneToMany(mappedBy = "matlNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RecipeIngredients> rcpingreList = new ArrayList<RecipeIngredients>();
	
	@Exclude
	@OneToMany(mappedBy = "matlNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserFrige> refriList = new ArrayList<UserFrige>();
}
