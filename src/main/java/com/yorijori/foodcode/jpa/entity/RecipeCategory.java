package com.yorijori.foodcode.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recipe_category")
public class RecipeCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //컬럼명은 id
	//private int recipeNo;
	//private int categoryNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipeNo", nullable=false)
	@Exclude
	private Recipe recipeNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="categoryNo", nullable = false)
	@Exclude
	private Category categoryNo;
	
}
