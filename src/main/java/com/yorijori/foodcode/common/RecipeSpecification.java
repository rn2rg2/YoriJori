package com.yorijori.foodcode.common;

import org.springframework.data.jpa.domain.Specification;

import com.yorijori.foodcode.jpa.entity.Recipe;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class RecipeSpecification {

	public static Specification<Recipe> equalRecipeId(int recipeNo) {
		return new Specification<Recipe>() {
			@Override
			public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("recipe_No"), recipeNo);
			}
		};
	}

	public static Specification<Recipe> likeContents(String contents) {
		return new Specification<Recipe>() {
			@Override
			public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// 2) like
				return criteriaBuilder.like(root.get("contents"), "%" + contents + "%");
			}
		};
	}

	public static Specification<Recipe> betweenCreatedDatetime(LocalDateTime startDatetime, LocalDateTime endDatetime) {
		return new Specification<Recipe>() {
			@Override
			public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// 3) between
				return criteriaBuilder.between(root.get("createdDatetime"), startDatetime, endDatetime);
			}
		};
	}

	public static Specification<Recipe> equalServing(String serving) {
		return new Specification<Recipe>() {
			@Override
			public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// 1) equal
				return criteriaBuilder.equal(root.get("serving"), serving);
			}
		};
	}
}