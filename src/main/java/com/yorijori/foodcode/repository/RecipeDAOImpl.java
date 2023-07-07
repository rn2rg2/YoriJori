package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.VO.MonthlyRcpVO;
import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeIngredients;
import com.yorijori.foodcode.jpa.entity.RecipeQa;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.jpa.repository.ApiRecipeRepository;
import com.yorijori.foodcode.jpa.repository.RecipeCategoryRepository;
import com.yorijori.foodcode.jpa.repository.RecipeImageRepository;
import com.yorijori.foodcode.jpa.repository.RecipeIngredientsRepository;
import com.yorijori.foodcode.jpa.repository.RecipeQaRepository;
import com.yorijori.foodcode.jpa.repository.RecipeRepository;
import com.yorijori.foodcode.jpa.repository.RecipeReviewRepository;
import com.yorijori.foodcode.jpa.repository.UserWishlistRepository;

@Repository
public class RecipeDAOImpl implements RecipeDAO {
	RecipeRepository reciperepository;
	RecipeImageRepository recipeimagerepository;
	RecipeQaRepository recipeqarepository;
	RecipeReviewRepository recipereviewrepository;
	RecipeCategoryRepository recipecategoryrepository;
	ApiRecipeRepository apiRecipeRepository;
	UserWishlistRepository userwishlistrepo;
	RecipeIngredientsRepository recipeinger;

	@Autowired
	public RecipeDAOImpl(RecipeRepository reciperepository, RecipeImageRepository recipeimagerepository,
			RecipeQaRepository recipeqarepository, RecipeReviewRepository recipereviewrepository,
			RecipeCategoryRepository recipecategoryrepository, ApiRecipeRepository apiRecipeRepository,
			UserWishlistRepository userwishlistrepo, RecipeIngredientsRepository recipeinger) {
		super();
		this.reciperepository = reciperepository;
		this.recipeimagerepository = recipeimagerepository;
		this.recipeqarepository = recipeqarepository;
		this.recipereviewrepository = recipereviewrepository;
		this.recipecategoryrepository = recipecategoryrepository;
		this.apiRecipeRepository = apiRecipeRepository;
		this.userwishlistrepo = userwishlistrepo;
		this.recipeinger = recipeinger;
	}

	@Override
	public void insertAll(Recipe recipedata) {
		reciperepository.save(recipedata);
	}

	@Override
	public void insertImageAll(List<RecipeImage> recipeimg) {
		recipeimagerepository.saveAll(recipeimg);
	}

	@Override
	public Recipe findById(int recipeNo) {
		return reciperepository.findById(recipeNo).orElseThrow(() -> new RuntimeException());
	}
	@Override
	public List<Recipe> findByUserId(UserInfo userId) {
		return reciperepository.findAllByUserId(userId);
	}

	@Override
	public long countAll() {
		return reciperepository.count();
	}
	@Override
	public long countByUserId(UserInfo userId) {
		return reciperepository.countByUserId(userId);
	}
	
	@Override
	public long countRcpByUserId(UserInfo userId) {
		return reciperepository.countByUserId(userId);
		
	}
	@Override
	public long countWishByUserId(UserInfo userId) {
		return userwishlistrepo.countByUserId(userId);
		
	}

	@Override
	public long countByRcpSeqByWishList(Recipe recipe, UserInfo userId) {
		return userwishlistrepo.countByRecipeNoAndUserId(recipe, userId);
	}

	@Override
	public List<Recipe> selectListByPage(int pageNo, int pagePerCount) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "recipeNo"));
		Page<Recipe> page = reciperepository.findAll(pageRequest);
		List<Recipe> list = page.getContent();

		return list;
	}

	@Override
	public void addWishList(UserWishlist userWishList) {
		userwishlistrepo.save(userWishList);
	}

	@Override
	public void deleteWishList(Recipe recipeNo, UserInfo userId) {
		userwishlistrepo.deleteByRecipeNoAndUserId(recipeNo, userId);
	}

	@Override
	public Recipe select(int recipeNo) {

		return reciperepository.findByRecipeNo(recipeNo);
	}

	@Override
	public List<RecipeImage> imgselect(int recipeNo) {
		Recipe recipe = reciperepository.findById(recipeNo).orElse(null);
		return recipeimagerepository.findByRecipeNo(recipe);
	}

	@Override
	public List<Recipe> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, sortType));
		Page<Recipe> page = reciperepository.findAll(pageRequest);
		List<Recipe> list = page.getContent();

		return list;
	}

	@Override
	public List<RecipeReview> reviewselect(int recipeNo) {

		// TODO Auto-generated method stub
		Recipe recipe = reciperepository.findById(recipeNo).orElse(null);
		return recipereviewrepository.findByRecipeNo(recipe);
	}

	@Override
	public void reviewsave(RecipeReview recipereview) {
		recipereviewrepository.save(recipereview);
	}

	@Override
	public void recipeqasave(RecipeQa recipeqa) {
		recipeqarepository.save(recipeqa);

	}

	@Override
	public List<RecipeQa> QAselect(int recipeNo) {
		// TODO Auto-generated method stub
		Recipe recipe = reciperepository.findById(recipeNo).orElse(null);
		return recipeqarepository.findByRecipeNo(recipe);
	}

	@Override
	public List<RecipeIngredients> selectingr(int recipeNo) {
		Recipe recipe = reciperepository.findById(recipeNo).orElse(null);
		return recipeinger.findByRecipeNo(recipe);
	}

	@Override
	public List<Recipe> findAll(Specification<Recipe> spec) {
		// TODO Auto-generated method stub
		return reciperepository.findAll(spec);
	}

	@Override
	public List<Recipe> findAll(Specification<Recipe> spec, Sort sort) {
		return reciperepository.findAll(spec, sort);
	}

	@Override
	public long countByNameContaining(String name) {
		return reciperepository.countByNameContaining(name);
	}

	@Override
	public List<Recipe> selectBySearch(int pageNo, String searchData, int pagePerCount) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "count"));
		Page<Recipe> page = reciperepository.findByNameContaining(searchData, pageRequest);
		List<Recipe> list = page.getContent();
		return list;
	}

	@Override
	public List<Recipe> profileselectListByPage(int pageNo, int pagePerCount, UserInfo userId) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "recipeNo"));
		Page<Recipe> page = reciperepository.findByUserId(userId, pageRequest);
		List<Recipe> list = page.getContent();
		return list;
	}

	@Override
	public List<UserWishlist> mylikelist(UserInfo user) {
		List<UserWishlist> userwishlist = userwishlistrepo.findByUserId(user);
		return userwishlist;
	}

	@Override
	public List<Recipe> userwishListByPage(int pageNo, int pagePerCount, int RecipeNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "recipeNo"));
		Page<Recipe> page = reciperepository.findByRecipeNo(RecipeNo, pageRequest);
		List<Recipe> list = page.getContent();

		return list;
	}

	@Override
	public long countByCategoryNo(Category categoryNo) {
		return recipecategoryrepository.countByCategoryNo(categoryNo);

	}
	
	@Override
	public List<MonthlyRcpVO> getMonthlyData(){
		return reciperepository.getMonthlyData();
	}
    @Override
    public List<RecipeReview> getByRecipeNo(Recipe recipe) {
        return recipereviewrepository.findByRecipeNo(recipe);
    }
    
    @Override
    public List<Recipe> getTop15Recipe(){
    	return reciperepository.findTop15ByOrderByCountDesc();
    }
    

}
