package com.ktds.ysproject.member.dao;

import com.ktds.ysproject.member.vo.MemberVO;

public interface MemberDao {

	public int insertNewMember(MemberVO memberVO);
	
	public MemberVO selectOneMember(MemberVO memberVO);
	
}
