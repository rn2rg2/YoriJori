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
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cooking_class_curriculum")
@ToString(exclude = {"cookNo"})
public class CookingClassCurriculum {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private int cookNo;
	private String curName;
	private String curTime;
	private int curNo;
	
	@ManyToOne
	@JoinColumn(name = "cookNo", nullable=false)
	private CookingClass cookNo;
}
