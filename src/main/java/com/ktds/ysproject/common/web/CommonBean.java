package com.ktds.ysproject.common.web;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommonBean {

	@Bean("posterUploadPath")
	public String posterUploadPath() {
		return "D:/uploads/posterPath";
	}
	
	@Bean("videoUploadPath")
	public String videoUploadPath() {
		return "D:/uploads/videoPath";
	}
	
}
