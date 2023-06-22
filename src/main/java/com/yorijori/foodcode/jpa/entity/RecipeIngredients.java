package com.yorijori.foodcode.jpa.entity;

import javax.persistence.Entity;
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
@Table(name="recipe_ingredients")
public class RecipeIngredients {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	//private int recipeNo;
	//private int matlNo;
	private String num;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "recipeNo", nullable=false)
	private Recipe recipeNo;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "matlNo", nullable=false)
	private Ingredients matlNo;

}
