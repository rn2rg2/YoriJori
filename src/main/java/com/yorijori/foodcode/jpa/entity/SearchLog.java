package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="search_log")
public class SearchLog{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String keyword;
	private int count;
	@CreationTimestamp
	private Date registeredAt;
	@UpdateTimestamp
	private Date updatedAt;
}
