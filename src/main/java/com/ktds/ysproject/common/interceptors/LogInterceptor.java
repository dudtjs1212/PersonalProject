package com.ktds.ysproject.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {

	
	private Logger qosLogger = LoggerFactory.getLogger("com.ktds");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		qosLogger.debug("#"+request.getRemoteAddr() + "#" + request.getRequestURI() + "#" + request.getMethod() );
		return true;
	}
	
}
