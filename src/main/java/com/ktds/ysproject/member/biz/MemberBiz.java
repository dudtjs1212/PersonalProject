package com.ktds.ysproject.member.biz;

import javax.servlet.http.HttpSession;

import com.ktds.ysproject.member.vo.MemberVO;

public interface MemberBiz {

	public boolean registNewMember(MemberVO memberVO);
	
	public MemberVO readOneMember(MemberVO memberVO);
	
	public boolean readOneEmail(String email);
	
	public boolean isBlockUser(String email);
	
	public boolean increaseLoginFailCount(String email);
	
	public boolean unblockUser(String email);
}
