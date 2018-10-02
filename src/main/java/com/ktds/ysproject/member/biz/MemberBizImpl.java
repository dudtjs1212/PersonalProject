package com.ktds.ysproject.member.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.ysproject.common.util.SHA256Util;
import com.ktds.ysproject.member.dao.MemberDao;
import com.ktds.ysproject.member.vo.MemberVO;

@Component
public class MemberBizImpl implements MemberBiz {

	@Autowired
	private MemberDao memberDao;
	
	private String getHashedPassword(String salt, String password){
		return SHA256Util.getEncrypt(password, salt);
	}
	
	@Override
	public boolean registNewMember(MemberVO memberVO) {
		String salt = SHA256Util.generateSalt();
		String password = this.getHashedPassword(salt, memberVO.getPassword());
		memberVO.setPassword(password);
		memberVO.setSalt(salt);
		return memberDao.insertNewMember(memberVO) > 0;
	}

	@Override
	public MemberVO readOneMember(MemberVO memberVO) {
		String salt = this.memberDao.selectSaltById(memberVO.getEmail());
		String password = this.getHashedPassword(salt, memberVO.getPassword());
		memberVO.setPassword(password);
		return memberDao.selectOneMember(memberVO);
	}

	@Override
	public boolean readOneEmail(String email) {
		return memberDao.selectOneEmail(email) > 0;
	}
	
	

}