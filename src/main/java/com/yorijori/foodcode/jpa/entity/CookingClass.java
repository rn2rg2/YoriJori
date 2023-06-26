package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cooking_class")
public class CookingClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cookNo;
	//private String userId;
	private String title;
	private String thumbnail;
	private int price;
	private String category;
	private int time;
	private int count;
	private int priceDc;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	private int state;
	
	
	@Exclude
	@OneToMany(mappedBy = "cookNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CookingClassContent> contentList = new ArrayList<CookingClassContent>();
	
	@Exclude
	@OneToMany(mappedBy = "cookNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CookingClassCurriculum> curriList = new ArrayList<CookingClassCurriculum>();
	
	@Exclude
	@OneToMany(mappedBy = "cookNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CookingClassForm> formList = new ArrayList<CookingClassForm>();
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userId;
	// 실제 read페이지에서
	
}