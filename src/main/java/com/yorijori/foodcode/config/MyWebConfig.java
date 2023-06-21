package com.yorijori.foodcode.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yorijori.foodcode.common.LoginCheckInterceptor;

//자동으로 구성된 스프링 MVC구성을 변경없이 추가 작업을 하기 위해 사용
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
	/*
	 * 파일 업로드 경로 세팅
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//특정 path로 요청하는 경우 실제 파일이 저장된 위치를 연결해서 리소스를 가져올 수 있도록 처리
		registry.addResourceHandler("/data/**")
		.addResourceLocations("file:///C:/project/upload/");
	}

	/*
	 * 인터셉터 세팅
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LoginCheckInterceptor())//.order(1) //
		  .addPathPatterns("/board/CommentInsert","/board/write","/recipe/insert","/mypage/**","/cookingclass/in","/cs/inquiry", "/recipe/like/**"); //
		  //.excludePathPatterns("/yorijori/main","/emp/login.do","/emp/spring/login",
		  //"/board/list.do","/board/ajax/list.do","/images/**","/common/css/**","/common/js/**","/common/font/**");
	}

	/*
	 * CORS 세팅
	*/
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8088/yorijori").allowedMethods("*")
				.allowCredentials(false).maxAge(3000);
	}

}
