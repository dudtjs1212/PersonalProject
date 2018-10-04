package com.ktds.ysproject.member.biz;

import com.ktds.ysproject.member.vo.MemberVO;

public interface MemberBiz {

	public boolean registNewMember(MemberVO memberVO);
	
	public MemberVO readOneMember(MemberVO memberVO);
	
	public boolean readOneEmail(String email);
	
	public boolean isBlockUser(String email);
}
