package com.yorijori.foodcode.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString.Exclude;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "api_recipe")
public class ApiRecipe {
	@Id
	private int rcpSeq; // 레시피번호
	private String rcpNm; // 메뉴명
	private String recipeDes; // 조리방법
	private String rcpPat2; // 요리종류
	private String infoWgt; // 중량(1인분)
	private int infoEng; // 열량
	private int infoCar; // 탄수화물
	private int infoPro; // 단백질
	private int infoFat; // 지방
	private int infoNa; // 나트륨
	private String hashTag; // 해쉬태그
	private String attFileNoMain; // 이미지경로(소)
	private String attFileNoMk; // 이미지경로(대)
	private String rcpPartsDtls; // 재료정보
	private String rcpNaTip; // 저감 조리법 팁
	private int state; // Field
	private int count; //조회수

	@Exclude
	@OneToMany(mappedBy = "rcpSeq", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ApiRecipeImg> imgList = new ArrayList<ApiRecipeImg>();

	@Exclude
	@OneToMany(mappedBy = "rcpSeq",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ApiRecipeReview> reviewList = new ArrayList<ApiRecipeReview>();

	@Exclude
	@OneToMany(mappedBy = "rcpSeq", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ApiRecipeQa> qaList = new ArrayList<ApiRecipeQa>();

	@Exclude
	@OneToMany(mappedBy = "rcpSeq", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<UserWishListApi> wishlist = new ArrayList<UserWishListApi>();

	public void viewCountUp(ApiRecipe apiRecipe) {
		apiRecipe.count++;
	}

	public void setRcpSeq(int asInt) {
		// TODO Auto-generated method stub
		
	}
}