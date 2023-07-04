package com.yorijori.foodcode.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yorijori.foodcode.jpa.VO.MonthlyRcpVO;
import com.yorijori.foodcode.jpa.entity.ApiRecipeReview;
import com.yorijori.foodcode.jpa.entity.Category;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.RecipeCategory;
import com.yorijori.foodcode.jpa.entity.RecipeImage;
import com.yorijori.foodcode.jpa.entity.RecipeIngredients;
import com.yorijori.foodcode.jpa.entity.RecipeQa;
import com.yorijori.foodcode.jpa.entity.RecipeReview;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.repository.CategoryDAO;
import com.yorijori.foodcode.repository.RecipeDAO;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
	RecipeDAO recipeDAO;
	CategoryDAO categoryDAO;

	@Autowired
	public RecipeServiceImpl(RecipeDAO recipeDAO, CategoryDAO categoryDAO) {
		super();
		this.recipeDAO = recipeDAO;
		this.categoryDAO = categoryDAO;
	}

	@Override
	public long countAll() {
		return recipeDAO.countAll();
	}
	
	@Override
	public long countByNameContaining(String name) {
		return recipeDAO.countByNameContaining(name);
	}
	
	@Override
	public long countRcpByUserId(UserInfo userId) {
		return recipeDAO.countRcpByUserId(userId);
	}
	
	@Override
	public long countWishByUserId(UserInfo userId) {
		return recipeDAO.countWishByUserId(userId);
	}

	@Override
	public List<Recipe> selectListByPage(int pageNo, int pagePerCount) {
		return recipeDAO.selectListByPage(pageNo, pagePerCount);
	}

	@Override
	public void wishList(UserInfo userId, Recipe recipeNo) {
		long count = recipeDAO.countByRcpSeqByWishList(recipeNo,userId);
		UserWishlist userwishlist = new UserWishlist();
		userwishlist.setRecipeNo(recipeNo);
		userwishlist.setUserId(userId);
		System.out.println("======================");
		System.out.println(count);
		System.out.println("======================");
		
		if (count > 0) {
			recipeDAO.deleteWishList(recipeNo, userId);
		} else {
			recipeDAO.addWishList(userwishlist);
		}
	}

	@Override
	public void viewCountUp(int recipeNo) {
		Recipe recipe = recipeDAO.findById(recipeNo);
		recipe.viewCountUp(recipe);
	}

	@Override
	public Recipe select(int recipeNo) {
		// TODO Auto-generated method stub
		return recipeDAO.select(recipeNo);
	}

	@Override
	public List<RecipeImage> imgselect(int recipeNo) {
		return recipeDAO.imgselect(recipeNo);
	}

	@Override
	public void insertAll(Recipe recipedata) {
		List<RecipeIngredients> ingredients = recipedata.getIngrelist();
		List<RecipeCategory> categorylist = recipedata.getCategorylist();
		List<RecipeImage> imglist = recipedata.getImglist();

		for (int i = 0; i < ingredients.size(); i++) {
			ingredients.get(i).setRecipeNo(recipedata);
		}
		for (int i = 0; i < categorylist.size(); i++) {
			categorylist.get(i).setRecipeNo(recipedata);

			// Category dto = categoryDAO.findById(categorylist.get(i).getCategoryNo());
			// categorylist.get(i).setCategoryNo(dto);
			System.out.println("============11=============");
			System.out.println(recipedata.getCategorylist().get(i).getCategoryNo());
			System.out.println("=========================");

		}
		for (int i = 0; i < imglist.size(); i++) {
			imglist.get(i).setRecipeNo(recipedata);
		}
		recipeDAO.insertAll(recipedata);
	}

	@Override
	public List<Recipe> selectListByPageAndSort(int pageNo, int pagePerCount, String sortType) {
		return recipeDAO.selectListByPageAndSort(pageNo, pagePerCount, sortType);
	}

	@Override
	public List<RecipeReview> reviewselect(int recipeNo) {
		return recipeDAO.reviewselect(recipeNo);
	}

	@Override
	public void reviewsave(RecipeReview recipereview) {
		recipeDAO.reviewsave(recipereview);
	}

	@Override
	public void recipeqasave(RecipeQa recipeqa) {
		recipeDAO.recipeqasave(recipeqa);

	}

	@Override
	public List<RecipeQa> QAselect(int recipeNo) {
		// TODO Auto-generated method stub
		return recipeDAO.QAselect(recipeNo);
	}

	@Override
	public List<RecipeIngredients> selectingr(int rcpSeq) {
		// TODO Auto-generated method stub
		return recipeDAO.selectingr(rcpSeq);
	}

	@Override
	public List<Recipe> findAll(Specification<Recipe> spec) {
		// TODO Auto-generated method stub
		return recipeDAO.findAll(spec);
	}

	public List<Recipe> findAll(Specification<Recipe> spec, Sort sort) {
		return recipeDAO.findAll(spec, sort);
	}

	@Override
	public void findByRecipeNo(int recipeNo) {
		// TODO Auto-generated method stub

	}



	@Override
	public List<Recipe> selectBySearch(int pageNo, String searchData, int pagePerCount) {
		// TODO Auto-generated method stub

		return recipeDAO.selectBySearch(pageNo, searchData, pagePerCount);
	}

	@Override
	public List<Long> countByCategoryNo(int startnum, int endnum) {
		List<Long> list = new ArrayList<Long>();
		for (int i = startnum; i <= endnum; i++) {
			Category categoryNo = new Category();
			categoryNo.setCategoryNo(i);
			long count = recipeDAO.countByCategoryNo(categoryNo);
			list.add(count);
		}
		return list;
	}

	@Override
	public List<Recipe> profileselectListByPage(int pageNo, int pagePerCount, UserInfo userId) {
		// TODO Auto-generated method stub
		return recipeDAO.profileselectListByPage(pageNo, pagePerCount, userId);
	}

	@Override
	public List<Recipe> mylikeListByPage(int pageNo, int pagePerCount, UserInfo user) {
		List<UserWishlist> wishlistItems = recipeDAO.mylikelist(user);
		System.out.println("mylikelistbyPage: " + wishlistItems + "\n\n\n\n\n");
		List<Recipe> recipewishlist = new ArrayList<>();

		for (UserWishlist wishlistItem : wishlistItems) {
			List<Recipe> recipes = recipeDAO.userwishListByPage(pageNo, pagePerCount, 
					wishlistItem.getRecipeNo().getRecipeNo());
			recipewishlist.addAll(recipes);
		}

		System.out.println("mylikelistbypage에서 레시피 목록: " + recipewishlist);
		return recipewishlist;
	}
	
	@Override
	public List<MonthlyRcpVO> getMonthlyData(){
		return recipeDAO.getMonthlyData();
	}
    @Override
    public BigDecimal APIgetReviewAverage(List<ApiRecipeReview> review) {
        BigDecimal sum = BigDecimal.ZERO;
        for (ApiRecipeReview datareview : review) {
            sum = sum.add(datareview.getStar());
        }

        BigDecimal average;
        if (review.size() > 0) {
            BigDecimal size = BigDecimal.valueOf(review.size());
            average = sum.divide(size, 2, RoundingMode.HALF_UP);
        } else {
            average = BigDecimal.ZERO;
        }
        return average;
    }   
    @Override
    public BigDecimal USERgetReviewAverage(List<RecipeReview> review) {
        BigDecimal sum = BigDecimal.ZERO;
        for (RecipeReview datareview : review) {
            sum = sum.add(datareview.getStar());
        }

        BigDecimal average;
        if (review.size() > 0) {
            BigDecimal size = BigDecimal.valueOf(review.size());
            average = sum.divide(size, 2, RoundingMode.HALF_UP);
        } else {
            average = BigDecimal.ZERO;
        }
        return average;
    }
    @Override
    public List<RecipeReview> getByRecipeNo(Recipe recipe) {
        return recipeDAO.getByRecipeNo(recipe);
    }
	

}
