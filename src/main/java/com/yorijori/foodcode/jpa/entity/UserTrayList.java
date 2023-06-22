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
@Table(name="user_tray_list")
public class UserTrayList{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private int trayNo;
	private int trayOrder;
	private int recipeNo;
	private int state;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "trayNo", nullable = false)
	private UserTray trayNo;
}
