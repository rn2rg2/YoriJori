package com.yorijori.foodcode.apidata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yorijori.foodcode.jpa.entity.ApiRecipe;
import com.yorijori.foodcode.jpa.entity.ApiRecipeImg;
import com.yorijori.foodcode.jpa.repository.ApiRecipeRepository;

@Service
public class RecipeDataFetcher {
	ApiRecipeRepository apiRecipeRepository;
	
	@Autowired
	public RecipeDataFetcher(ApiRecipeRepository apiRecipeRepository) {
		super();
		this.apiRecipeRepository = apiRecipeRepository;
	}
	
	public void fetchRecipeData(int firstIdx, int lastIdx) throws IOException {
		// http://openapi.foodsafetykorea.go.kr/api/"+
		// keyValue+"/COOKRCP01/json/"+firstIdx + "/"+lastIdx;
		String keyValue = "141b49d83b284626a0c9";
		String urlPath = "http://openapi.foodsafetykorea.go.kr/api/" + keyValue + "/COOKRCP01/json/"
				+ Integer.toString(firstIdx) + "/" + Integer.toString(lastIdx);
		StringBuilder urlBuilder = new StringBuilder(urlPath);

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

		String jsonResponse = sb.toString();
		// Parse JSON and extract recipe data
        List<ApiRecipe> recipeDataList = parseRecipeData(jsonResponse);
        apiRecipeRepository.saveAll(recipeDataList);
	}
	
	public List<ApiRecipe> parseRecipeData(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonResponse);

        List<ApiRecipe> recipeDataList = new ArrayList<>();
        JsonNode recipeList = root.path("COOKRCP01");
        for (JsonNode recipeNode : recipeList.get("row")) {
        	ApiRecipe recipeData = new ApiRecipe();
        	List<ApiRecipeImg> apiRecipeImg = new ArrayList<>();
        	recipeData.setRcpSeq(recipeNode.path("RCP_SEQ").asInt());
            recipeData.setRcpNm(recipeNode.path("RCP_NM").asText());
            recipeData.setRecipeDes(recipeNode.path("RCP_WAY2").asText());
            recipeData.setRcpPat2(recipeNode.path("RCP_PAT2").asText());
            recipeData.setInfoWgt(recipeNode.path("INFO_FAT").asText());
            recipeData.setInfoEng(recipeNode.path("INFO_ENG").asInt());
            recipeData.setInfoCar(recipeNode.path("INFO_CAR").asInt());
            recipeData.setInfoPro(recipeNode.path("INFO_PRO").asInt());
            recipeData.setInfoFat(recipeNode.path("INFO_FAT").asInt());
            recipeData.setInfoNa(recipeNode.path("INFO_NA").asInt());
            recipeData.setHashTag(recipeNode.path("HASH_TAG").asText());
            recipeData.setAttFileNoMain(recipeNode.path("ATT_FILE_NO_MAIN").asText());
            recipeData.setAttFileNoMk(recipeNode.path("ATT_FILE_NO_MK").asText());
            recipeData.setRcpPartsDtls(recipeNode.path("RCP_PARTS_DTLS").asText());
            recipeData.setRcpNaTip(recipeNode.path("RCP_NA_TIP").asText());
            recipeData.setState(0);
            // Set other fields accordingly
            for ( int i = 1 ; i <= 20; i++) {
            	ApiRecipeImg ImgeData = new ApiRecipeImg();
            	String no = Integer.toString(i);
            	if ( i < 10 ) {
            		no = "0" + no;
            	} 
            	ImgeData.setCount(i);
            	ImgeData.setRcpSeq(recipeData);
            	if (recipeNode.path("MANUAL" + no).asText().equals("")) {
            		continue;
            	}
            	ImgeData.setManual(recipeNode.path("MANUAL" + no).asText());
            	ImgeData.setManualImg(recipeNode.path("MANUAL_IMG" + no).asText());
            	apiRecipeImg.add(ImgeData);
            	
            }
            recipeData.setImgList(apiRecipeImg);
            recipeDataList.add(recipeData);
        }

        return recipeDataList;
    }


}