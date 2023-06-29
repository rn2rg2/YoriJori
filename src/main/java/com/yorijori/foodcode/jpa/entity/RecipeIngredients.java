package com.yorijori.foodcode.jpa.entity;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recipe_ingredients")
public class RecipeIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    //private int recipeNo;
    //private int matlNo;
    private String num;
    private int matlNo;

    @Exclude
    @ManyToOne
    @JoinColumn(name = "recipeNo", nullable=false)
    @JsonBackReference
    private Recipe recipeNo;

    @Exclude
    @OneToMany(mappedBy = "matlNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ingredients> matlnoinfo = new ArrayList<Ingredients>();

}
