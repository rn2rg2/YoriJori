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
@Table(name="user_wishlist")
public class UserWishlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //컬럼명 id / autoincrement
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", nullable=false)
	@Exclude
	private UserInfo userId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipeNo", nullable=false)
	@Exclude
	private Recipe recipeNo;
}
