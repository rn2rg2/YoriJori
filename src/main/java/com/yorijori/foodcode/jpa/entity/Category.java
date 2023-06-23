package com.yorijori.foodcode.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_no")
    private int categoryNo;
    private String name;
    @Column(name = "level")
    private Integer level;
    @Column(name = "upper_level", length = 10)
    private String upperLevel;
}