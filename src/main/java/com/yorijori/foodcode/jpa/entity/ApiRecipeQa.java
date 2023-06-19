package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="api_recipe_qa")
@ToString(exclude = {"rcpSeq"})
public class ApiRecipeQa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int qaNo;
	//private int rcpSeq;		//레시피번호
	private String userId;
	private String contents;
	private boolean depthLevel;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	private int state;
	
	@ManyToOne
	@JoinColumn(name = "rcpSeq", nullable=false)
	@JsonBackReference
	private ApiRecipe rcpSeq;
}
