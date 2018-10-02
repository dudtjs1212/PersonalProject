package com.ktds.ysproject.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/main/home")
	public String viewMainPage() {
		return "main/home";
	}
	
}
