package com.yorijori.foodcode.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cooking_class_image")
public class CookingClassImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int contentNo;
	private int cookNo;
	private String image;
	private String imageStore;
	private String imgCategory;
	private int imageNo;
}
