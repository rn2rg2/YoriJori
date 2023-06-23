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
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="question_file")
public class QuestionFile{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private int questionNo;
	private String orgFileName;
	private String storeFileName;
	private int fileNo;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "questionNo", nullable = false)
	private Question questionNo;
}
