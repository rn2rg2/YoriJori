package com.yorijori.foodcode.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {
    private List<String> order; // 많은 순
    private List<String> date; // 날짜별
    private List<String> min; // 조리시간
    private List<String> serving; // 1인분 2인분3인분 4인분
    private List<String> country; //한식,중식,양식,일식

}

////시간별 메뉴 
//private String breakfast; // 아침메뉴
//private String lunch;	// 점심메뉴
//private String dinner;	// 저녁메뉴
//// 메뉴타입
//private String mainmenu; // 메인메뉴
//private String sidemenu; // 반찬
//private String dessert; // 디저트