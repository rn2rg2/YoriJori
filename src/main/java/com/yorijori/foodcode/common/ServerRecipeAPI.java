package com.yorijori.foodcode.common;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

@Service
// 총 1866 레시피
public class ServerRecipeAPI {

	public String getServerRecipe(String pageNo, String pagePerResult) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1390802/AgriFood/FdFoodCkryImage/getKoreanFoodFdFoodCkryImageList"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=v7fIQZ0gf2t0sjvukEM8BxuLrJiLBwmmPvGxI9ySqxzcOXzdPkaaM%2BpOp7iFxPDjN2Kg2PZLmnxf%2FZ08fZcV7A%3D%3D"); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("service_Type", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* xml 과 json 형식 지원 */
		urlBuilder.append(
				"&" + URLEncoder.encode("Page_No", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /* 페이지 번호 */
		urlBuilder.append("&" + URLEncoder.encode("Page_Size", "UTF-8") + "="
				+ URLEncoder.encode(pagePerResult, "UTF-8")); /* 한 페이지 결과 수 */
//		urlBuilder.append("&" + URLEncoder.encode("food_Name", "UTF-8") + "="
//				+ URLEncoder.encode("밥", "UTF-8")); /* 음식 명 (검색어 입력값 포함 검색) */
//		urlBuilder.append("&" + URLEncoder.encode("ckry_Name", "UTF-8") + "="
//				+ URLEncoder.encode("조리", "UTF-8")); /* 조리법 명 (검색어 입력값 포함 검색) */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		//System.out.println(sb.toString());
		
		return sb.toString();
	}
}
