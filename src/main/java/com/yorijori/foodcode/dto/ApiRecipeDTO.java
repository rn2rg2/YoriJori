package com.yorijori.foodcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRecipeDTO {

	private int rcpSeq;		//레시피번호
	private String rcpNm; 	//메뉴명	
	private String recipeDes;	//조리방법
	private String rcpPat2;		//요리종류	
	private String infoWgt;		//중량(1인분)
	private int infoEng;		//열량
	private int infoCar;		//탄수화물
	private int infoPro;		//단백질
	private int infoFat;		//지방
	private int infoNa;		//나트륨
	private String hashTag;		//해쉬태그	
	private String attFileNoMain;	//이미지경로(소)
	private String attFileNoMk;		//이미지경로(대)
	private String rcpPartsDtls;	//재료정보
	private String rcpNaTip;		//저감 조리법 팁 
	private int	state; 		//Field
}
