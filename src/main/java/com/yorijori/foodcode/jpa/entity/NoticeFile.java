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
@Table(name = "notice_file")
public class NoticeFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private int  noticeNo ;
	private String  orgFileName  ;
	private String  storeFileName  ;
	private int  fileNo  ;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "noticeNo", nullable = false)
	private Notice noticeNo;
		
}
