package com.ktds.ysproject.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.ysproject.member.biz.MemberBiz;
import com.ktds.ysproject.member.vo.MemberVO;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		if ( session.getAttribute("_USER_") == null ) {
			response.sendRedirect("/GameReview/member/login");
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		MemberVO sessionMemberVO = (MemberVO) session.getAttribute("_USER_");
		if ( sessionMemberVO != null ) {			
			session.setAttribute("_USER_", sessionMemberVO);
		}
	}
}
