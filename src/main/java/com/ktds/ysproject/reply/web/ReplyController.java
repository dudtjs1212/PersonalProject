package com.ktds.ysproject.reply.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktds.ysproject.member.vo.MemberVO;
import com.ktds.ysproject.reply.service.ReplyService;
import com.ktds.ysproject.reply.vo.ReplyVO;
import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class ReplyController {
 
	@Autowired
	private ReplyService replyService;
	
	@PostMapping("/reply/write")
	public String doReplyWriteAction(@ModelAttribute ReplyVO replyVO, @SessionAttribute("_USER_") MemberVO memberVO) {
		XssFilter xssFilter = XssFilter.getInstance("lucy-xss-superset.xml");
		replyVO.setContent(xssFilter.doFilter(replyVO.getContent()));
		
		replyVO.setEmail(memberVO.getEmail());
		boolean isSuccess= this.replyService.createOneReply(replyVO);
		
		return "redirect:/board/detail/" +replyVO.getBoardId();
	}
	
	@GetMapping("/reply/delete/{replyId}")
	public String doReplyDeleteAction(@PathVariable String replyId, HttpSession session) {
		ReplyVO reply = replyService.readOneReply(replyId);
		if ( session.getAttribute("_USER_").equals(reply.getEmail()) ) {
			this.replyService.deleteReply(reply);
		}
		return "redirect:/board/detail/" + reply.getBoardId();
	}
	
}
