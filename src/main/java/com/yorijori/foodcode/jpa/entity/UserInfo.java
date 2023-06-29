package com.yorijori.foodcode.jpa.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "user_info")
public class UserInfo {
	@Id
	private String userId;
	private String role;
	private String nickname;
	private String pass;
	private String email;
	private String name;
	private Integer phoneNumber;
	private String ssn;
	private String imgPath;
	private Integer point;
	private String prefer;
	private String purpose;
	private String allergy;
	private Integer state;
	@CreationTimestamp
	private Date date;
	private String kakaoID;

	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Recipe> recipeList = new ArrayList<Recipe>();

	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<RecipeReview> reviewList = new ArrayList<RecipeReview>();
	
	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ApiRecipeReview> apireviewList = new ArrayList<ApiRecipeReview>();
	
//	@Exclude
//	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<RecipeQa> qaList = new ArrayList<RecipeQa>();
	
	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Inquiry> inquiryList = new ArrayList<Inquiry>();
	
//	@Exclude
//	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<ApiRecipeQa> apiqaList = new ArrayList<ApiRecipeQa>();

//	@Exclude
//	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private List<UserTray> trayList = new ArrayList<UserTray>();
	
//	@Exclude
//	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private List<UserFrige> frigeList = new ArrayList<UserFrige>();
	
//	@Exclude
//	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<UserLog> logList = new ArrayList<UserLog>();
	
	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Board> boardList = new ArrayList<Board>();
	
	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BoardComment> boardcommentList = new ArrayList<BoardComment>();
	
	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CookingClass> cookingClassList = new ArrayList<CookingClass>();
	
	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CookingClassForm> cookingClassForm = new ArrayList<CookingClassForm>();
	
	@Exclude
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<UserWishlist> userWishList = new ArrayList<UserWishlist>();
//	
//	@Exclude
//	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<UserWishListApi> userWishApiList = new ArrayList<UserWishListApi>();

}
