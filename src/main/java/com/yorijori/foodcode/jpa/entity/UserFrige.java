package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_frige")
public class UserFrige {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userId;
	private int matlNo;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;

//	@Exclude
//	@ManyToOne
//	@JoinColumn(name = "userId", nullable = false)
//	@JsonBackReference
//	private UserInfo userId;
	
	@Exclude
	@Transient
    private Ingredients ingredients;

	@Exclude
	@Transient
	private List<Ingredients> matlList = new ArrayList<Ingredients>();

}
