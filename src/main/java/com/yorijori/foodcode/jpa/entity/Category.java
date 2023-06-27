package com.yorijori.foodcode.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryNo;
    private String name;
    @Column(name = "level")
    private Integer level;
    @Column(name = "upper_level", length = 10)
    private String upperLevel;
    
    @OneToMany(mappedBy = "categoryNo",fetch = FetchType.LAZY)
    @Exclude
    private List<RecipeCategory> categorys;
}