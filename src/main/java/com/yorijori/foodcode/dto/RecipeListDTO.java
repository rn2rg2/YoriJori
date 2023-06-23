package com.yorijori.foodcode.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeCategory;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeIngredients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeListDTO {
	private String name;
	private String des; // 요리 간단 설명(내용)
	List<RecipeCategoryDTO> categorylist;	
	private String serving;
	private String time;
	private String level;
	List<RecipeIngredientsDTO> recipeingredientslist;
	List<RecipeImageDTO> recipeimagelist;
	List<MultipartFile> multipartfilelist;
	private String rcpnatip;
	MultipartFile  thumbnail;
	
}
