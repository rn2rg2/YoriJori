package com.yorijori.foodcode.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
		
	

}
