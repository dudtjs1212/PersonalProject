package com.ktds.ysproject.member.dao;

import com.ktds.ysproject.member.vo.MemberVO;

public interface MemberDao {
 
	public int insertNewMember(MemberVO memberVO);
	
	public MemberVO selectOneMember(MemberVO memberVO);
	
	public String selectSaltById(String email);
	
	public int selectOneEmail(String email);
	
	public Integer isBlockUser(String email);
	
	public int unblockUser(String email);
		
	public int increaseLoginFailCount(String email);
	
}
