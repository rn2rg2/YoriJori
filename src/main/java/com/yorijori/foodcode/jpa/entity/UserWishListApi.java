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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_wishlist_api")
@ToString(exclude = {"userId","rcpSeq"})
public class UserWishListApi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;			//key
	//private String userId;	
	//private int rcpSeq;		//레시피번호
	@ManyToOne
	@JoinColumn(name = "userId", nullable=false)
	private UserInfo userId;
	
	@ManyToOne
	@JoinColumn(name = "rcpSeq", nullable=false)
	@JsonBackReference
	private ApiRecipe rcpSeq;
}
