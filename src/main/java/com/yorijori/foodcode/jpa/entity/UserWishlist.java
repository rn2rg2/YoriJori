package com.yorijori.foodcode.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="user_wishlist")
public class UserWishlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //컬럼명 id / autoincrement
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable=false)
	@Exclude
	@JsonBackReference
	private UserInfo userId;
	
	@ManyToOne
	@JoinColumn(name = "recipeNo", nullable=false)
	@Exclude
	@JsonManagedReference
	private Recipe recipeNo;
}
