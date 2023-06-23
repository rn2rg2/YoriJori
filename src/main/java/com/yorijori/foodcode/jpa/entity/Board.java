package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import lombok.ToString.Exclude;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="community_info")
//@DynamicInsert
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commNo;
	//private String userId;
	private String title;
	private String contents;
	private String category;
	//@ColumnDefault("0")
	@Column(columnDefinition = "integer default 0", nullable = false)
	private Integer view;
	@CreationTimestamp
	private Date date ;
	@UpdateTimestamp
	private Date upDate;
	//default =0 삭제가 =1  1일때 따로 표시하게!! 삭제된 게시물이라는것을 보여주기
	private int state;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userId;

	@Exclude
	@OneToMany(mappedBy = "commNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BoardComment> commentList = new ArrayList<BoardComment>();

}

