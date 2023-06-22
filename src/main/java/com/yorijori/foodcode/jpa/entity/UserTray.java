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
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usertray")
public class UserTray{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trayNo;
	//private String userId;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date upDate;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userId;
	
	@Exclude
	@OneToMany(mappedBy = "trayNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserTrayList> trayList = new ArrayList<UserTrayList>();
}
