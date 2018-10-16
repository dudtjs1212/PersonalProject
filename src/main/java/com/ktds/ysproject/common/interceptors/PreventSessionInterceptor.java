package com.ktds.ysproject.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.ysproject.member.vo.MemberVO;

public class PreventSessionInterceptor extends HandlerInterceptorAdapter {
 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("_USER_");
		
		if( memberVO != null) {
			response.sendRedirect("/GameReview/main/home");
			return false;
		}
		return true;
	}

}
