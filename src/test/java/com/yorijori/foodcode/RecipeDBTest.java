package com.yorijori.foodcode;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.repository.RecipeRepository;

@SpringBootTest
@Transactional
class RecipeDBTest {
	@Autowired
	RecipeRepository repository;
	
	@Test
	void test() {
		List<Recipe> recipe = repository.findAll();
		for ( Recipe data : recipe) {
			System.out.println(data.getCategoryList());
		}
			
	}

}
