package com.yorijori.foodcode.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.foodcode.common.FileUploadLogic;
import com.yorijori.foodcode.jpa.VO.RecipeVO;
import com.yorijori.foodcode.jpa.VO.UserTrayListResponse;
import com.yorijori.foodcode.jpa.entity.Recipe;
import com.yorijori.foodcode.jpa.entity.UserFrige;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.entity.UserTray;
import com.yorijori.foodcode.jpa.entity.UserTrayList;
import com.yorijori.foodcode.jpa.entity.UserWishlist;
import com.yorijori.foodcode.service.IngredientService;
import com.yorijori.foodcode.service.RecipeService;
import com.yorijori.foodcode.service.RefriTrayService;
import com.yorijori.foodcode.service.UserWishService;

@Controller
@RequestMapping("/mypage")
public class RefriTrayController {
	IngredientService ingreService;
	RefriTrayService refriTrayService;
	UserWishService userWishService;
	RecipeService recipeService;
	FileUploadLogic fileuploadlogic;

	@Autowired
	public RefriTrayController(IngredientService ingreService, RefriTrayService refriTrayService, UserWishService userWishService, RecipeService recipeService, FileUploadLogic fileuploadlogic) {
		super();
		this.ingreService = ingreService;
		this.refriTrayService = refriTrayService;
		this.userWishService = userWishService;
		this.recipeService = recipeService;
		this.fileuploadlogic = fileuploadlogic;
	}

	@RequestMapping("/refri")
	public String refri(Model model, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		List<UserFrige> refrilist = new ArrayList<UserFrige>();
		refrilist = refriTrayService.selectAll(user);
		long count = ingreService.countAll();
		long refriCount = refriTrayService.countByUserId(user);
		if (refrilist.size() > 0  ) {
			List<RecipeVO> rcplist = new ArrayList<RecipeVO>();
			int maxSize = 4;
			if ( refrilist.size() < 4) {
				maxSize = refrilist.size();
			}
			for ( int i = 0 ; i < maxSize; i ++ ) {
				//System.out.println("=================================");
				//System.out.println(refrilist.get(i));
				//System.out.println("=================================");
				RecipeVO rcp = refriTrayService.getRecommendList(userinfo, refrilist.get(i));
				if (rcp != null ) {
					rcplist.add(rcp);
				}
				//System.out.println("=============rcp====================");
				//System.out.println(rcp);
				//System.out.println("=================================");
			}
			if ( rcplist.size() > 0 ) {
				model.addAttribute("rcplist", rcplist);
			}
		}
		model.addAttribute("refrilist", refrilist);
		model.addAttribute("count", count);
		model.addAttribute("refriCount", refriCount);
		return "thymeleaf/mypage/refri";
	}

	@RequestMapping("/tray")
	public String tray(Model model, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		List<UserTray> tray = refriTrayService.selectTrayByUserId(user);
		model.addAttribute("tray", tray);
		return "thymeleaf/mypage/tray";
	}
	
	@PostMapping("/refri/insert")
	public String refriInsert(UserFrige userfrige,HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		userfrige.setUserId(user);
		refriTrayService.insert(userfrige, user);
		return "redirect:/mypage/refri";
	}
	
	@PostMapping("/tray/insert")
	public String refriInsert(UserTray usertray,HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		usertray.setUserId(user);
		refriTrayService.insertTray(usertray);
		return "redirect:/mypage/tray";
	}
	
	@RequestMapping("/tray/list/{trayNo}")
	@ResponseBody
	public List<UserTrayListResponse> getUserTrayDetail(@PathVariable int trayNo, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		String user = userinfo.getUserId();
		List<UserTrayListResponse> list = new ArrayList<UserTrayListResponse>();
		UserTray tray = refriTrayService.selectTrayDetail(trayNo, user);
		for ( UserTrayList li : tray.getTrayList()) {
			int recipeNo = li.getRecipeNo();
			Recipe rcp = recipeService.select(recipeNo);
			UserTrayListResponse dto = new UserTrayListResponse(li, rcp);
			list.add(dto);
		}
		
		return list;
	}
	
	@GetMapping("/wish/list/{pageNo}/{pagePerCount}")
	@ResponseBody
	public List<UserWishlist> getWishListByPage(@PathVariable int pageNo, @PathVariable int pagePerCount, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		//List<RcpCategoryDTO> list = userWishService.selectRcpAndCategory(userinfo);
		////System.out.println(list);
		List<UserWishlist> list = userWishService.selectAll(userinfo,pageNo, pagePerCount);
		return list;
	}
	
	@PostMapping("/wish/count")
	@ResponseBody
	public long getListCount(HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		long count = userWishService.countAllByUserId(userinfo);
		return count;
	}
	@RequestMapping("/tray/delete/{trayNo}")
	public String deleteTray(@PathVariable int trayNo, HttpSession session) {
		UserInfo userinfo= (UserInfo)session.getAttribute("userInfo");
		UserTray tray = refriTrayService.selectTrayDetail(trayNo, userinfo.getUserId());
		String imgPath = tray.getImgPath();
		String fileRoot = fileuploadlogic.getUploadpath("tray/");
		File targetFile = new File(fileRoot + imgPath);
		FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
		
		refriTrayService.deleteTray(trayNo);
		return "redirect:/mypage/tray";
	}
	
	@RequestMapping(value = "/tray/image", produces = "application/text;charset=utf-8")
    @ResponseBody
    public String imgSaveTest(String imgSrc) throws Exception {
        //ModelMap map = new ModelMap();
		String imgPath = "";
        //System.out.println("^%^%^%^%^%^%");
        //String binaryData = request.getParameter("imgSrc");
        //System.out.println(imgSrc);
        String binaryData = imgSrc;
        //System.out.println(binaryData);
        FileOutputStream stream = null;
        try{
            //System.out.println("binary file   "  + binaryData);
            if(binaryData == null || binaryData.trim().equals("")) {
                throw new Exception();
            }
            binaryData = binaryData.replaceAll("data:image/png;base64,", "");
            byte[] file = Base64.decodeBase64(binaryData);
            String fileName=  UUID.randomUUID().toString();

            String fileRoot = fileuploadlogic.getUploadpath("tray/");
            stream = new FileOutputStream(fileRoot+fileName+".png");
            imgPath = fileName+".png";
            //System.out.println(file);
            //System.out.println(fileName+"------------------------");
            stream.write(file);
            stream.close();
            //System.out.println("캡처 저장");

        }catch(Exception e){
            e.printStackTrace();
            //System.out.println("에러 발생");
        }finally{
            if(stream != null) {
                stream.close();
            }
        }

    //    map.addAttribute("resultMap", "");
        return imgPath;
    }
}
