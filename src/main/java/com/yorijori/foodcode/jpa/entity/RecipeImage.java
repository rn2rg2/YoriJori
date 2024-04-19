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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString.Exclude;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recipe_image")
public class RecipeImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //컬렴명은 id
	//private int recipeNo;
	private int imgNo;
	private String oreImg;//원본 이미지 이름
	private String storeImg;// 저장할 이미지 이름
	private String content;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "recipeNo", nullable=false)
	@JsonBackReference
	private Recipe recipeNo;

	public void setImgNo(int i) {
		// TODO Auto-generated method stub
		
	}
}	
