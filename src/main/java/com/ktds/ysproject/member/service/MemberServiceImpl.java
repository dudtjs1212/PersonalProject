package com.ktds.ysproject.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.ysproject.member.biz.MemberBiz;
import com.ktds.ysproject.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
 
	@Autowired
	private MemberBiz memberBiz;
	
	@Override
	public boolean registNewMember(MemberVO memberVO) {
		return memberBiz.registNewMember(memberVO);
	}

	@Override
	public MemberVO readOneMember(MemberVO memberVO) {
		return memberBiz.readOneMember(memberVO);
	}

	@Override
	public boolean readOneEmail(String email) {
		return memberBiz.readOneEmail(email);
	}

	@Override
	public boolean isBlockUser(String email) {
		return memberBiz.isBlockUser(email);
	}

}