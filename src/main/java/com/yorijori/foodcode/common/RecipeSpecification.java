package com.yorijori.foodcode.common;

import org.springframework.data.jpa.domain.Specification;

import com.yorijori.foodcode.dto.FilterDTO;
import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeCategory;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserWishlist;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class RecipeSpecification {
	public static Specification<Recipe> any() {
	    return new Specification<Recipe>() {
	        @Override
	        public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            return criteriaBuilder.conjunction();
	        }
	    };
	}


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

	public static Specification<Recipe> equalServing(List<String> servings) {
	    return new Specification<Recipe>() {
	        @Override
	        public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            List<Predicate> predicates = new ArrayList<>();

	            // Create predicates for all serving sizes in the list
	            for (String serving : servings) {
	                predicates.add(criteriaBuilder.equal(root.get("serving"), serving));
	            }

	            // Combine all predicates using 'or' clause
	            return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
	        }
	    };
	}
	public static Specification<Recipe> equalmin(List<String> time) {
	    return new Specification<Recipe>() {
	        @Override
	        public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            List<Predicate> predicates = new ArrayList<>();

	            // Create predicates for all serving sizes in the list
	            for (String time : time) {
	                predicates.add(criteriaBuilder.equal(root.get("time"), time));
	            }

	            // Combine all predicates using 'or' clause
	            return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
	        }
	    };
	}
	public static Specification<Recipe> orderByViewCount() {
	    return new Specification<Recipe>() {
	        @Override
	        public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            // Replace "viewCount" with the correct attribute name in your Recipe entity
	            Order viewCountOrder = criteriaBuilder.desc(root.get("count"));
	            query.orderBy(viewCountOrder);
	            
	            return criteriaBuilder.conjunction();
	        }
	    };
	}

	public static Specification<Recipe> orderByCreatedDatetime() {
	    return new Specification<Recipe>() {
	        @Override
	        public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            // Replace "createdDatetime" with the correct attribute name in your Recipe entity
	            Order createDateOrder = criteriaBuilder.desc(root.get("date"));
	            query.orderBy(createDateOrder);

	            return criteriaBuilder.conjunction();
	        }
	    };
	}
	public static Specification<Recipe> orderByReviewCount() {
	    return (Specification<Recipe>) (root, query, criteriaBuilder) -> {
	        Join<Recipe, RecipeReview> join = root.join("reviewlist", JoinType.LEFT); // 수정된 필드 이름으로 연관 관계

	        query.groupBy(root);
	        query.orderBy(criteriaBuilder.desc(criteriaBuilder.count(join)));

	        return criteriaBuilder.conjunction();
	    };
	}
	public static Specification<Recipe> orderByWishlist() {
	    return (Specification<Recipe>) (root, query, criteriaBuilder) -> {
	        Join<Recipe, UserWishlist> join = root.join("wishlist", JoinType.LEFT); 

	        query.groupBy(root.get("recipeNo"));
	        query.orderBy(criteriaBuilder.desc(criteriaBuilder.count(join.get("recipeNo"))));

	        return criteriaBuilder.conjunction();
	    };
	}
	public static Specification<Recipe> findByCountry(String country) {
	    return new Specification<Recipe>() {
	        @Override
	        public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            Join<Recipe, RecipeCategory> recipeCategoryJoin = root.join("categorylist");
	            Join<RecipeCategory, Category> categoryJoin = recipeCategoryJoin.join("categoryNo");

	            Predicate countryPredicate = criteriaBuilder.equal(categoryJoin.get("name"), country);

	            return countryPredicate;
	        }
	    };
	}
	public static Specification<Recipe> findByRecipe(String recipetype) {
	    return new Specification<Recipe>() {
	        @Override
	        public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            Join<Recipe, RecipeCategory> recipeCategoryJoin = root.join("categorylist");
	            Join<RecipeCategory, Category> categoryJoin = recipeCategoryJoin.join("categoryNo");

	            Predicate countryPredicate = criteriaBuilder.equal(categoryJoin.get("name"), recipetype);

	            return countryPredicate;
	        }
	    };
	}
}
	

