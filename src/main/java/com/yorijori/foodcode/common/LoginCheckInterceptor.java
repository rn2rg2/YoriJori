package com.yorijori.foodcode.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yorijori.foodcode.jpa.entity.UserInfo;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인사용자인 경우 세션에 user라는 이름의 어트리뷰트가 저장되어 있으므로
		// user가 없으면 로그인이 처리되지 않음을 의미
		HttpSession session = request.getSession(false);
		if (session != null) {
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			if (user == null) {// 로그인하지 않은 상태
				response.sendRedirect("/yorijori/main");
				// 로그인하지 않은 사용자는 다음(뷰에서 선택한 path로 이동되지 않도록)으로 넘어가지 않도록 false를리턴
				return false;
			} else {
				return true;
			}
		}
		// 로그인된 사용자는 다음으로 넘어갈 수 있도록 true리턴
		/*
		 * MemberVO lvo = (MemberVO)session.getAttribute("member");
		 * 
		 * if(lvo == null || lvo.getAdminCk() == 0) { // 관리자 계정 아닌 경우
		 * 
		 * response.sendRedirect("/main"); // 메인페이지로 리다이렉트
		 * 
		 * return false;
		 * 
		 * }
		 * 
		 * return true; // 관리자 계정 로그인 경우(lvo != null && lvo.getAdminCk() == 1)
		 */
		return false;

	}

}
