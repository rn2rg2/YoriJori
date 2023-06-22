package com.yorijori.foodcode.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="api_recipe_img")
public class ApiRecipeImg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int	id;
	//private int recipeNo;		//레시피번호
	private int count;		//순서
	private String manual;	//컨텐츠1
	private String manualImg;	//이미지1
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "rcpSeq", nullable=false)
	@JsonBackReference
	private ApiRecipe rcpSeq;
}
