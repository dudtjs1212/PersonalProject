package com.ktds.ysproject.member.service;

import com.ktds.ysproject.member.vo.MemberVO;

public interface MemberService {

	public boolean registNewMember(MemberVO memberVO);
	
	public MemberVO readOneMember(MemberVO memberVO);
	
}
