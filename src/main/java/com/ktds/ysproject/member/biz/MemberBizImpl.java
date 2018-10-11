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
		if ( salt != null ) {
			String password = this.getHashedPassword(salt, memberVO.getPassword());
			memberVO.setPassword(password);
		}
		
		MemberVO readMemberVO = new MemberVO();
		readMemberVO.setEmail(memberVO.getEmail());
		readMemberVO.setPassword(memberVO.getPassword());
		System.out.println(readMemberVO.toString());
		MemberVO member = memberDao.selectOneMember(readMemberVO);
		
		if ( member == null ) {
			memberDao.increaseLoginFailCount(memberVO.getEmail());
		}
		else {
			memberDao.unblockUser(readMemberVO.getEmail());
		}
		
		return member;
		
		/*if (readMemberVO.getEmail() != null) {
			isBlockAccount = memberDao.isBlockUser(readMemberVO.getEmail()) >= 3;
		}
		
		if ( !isBlockAccount ) {
			readMemberVO = memberDao.selectOneMember(memberVO);

			if ( readMemberVO == null ) {
				memberDao.increaseLoginFailCount(memberVO.getEmail());
			}
			else {
				memberDao.unblockUser(memberVO.getEmail());
			}
		}
		else {
			readMemberVO = null;
		}
		return readMemberVO;*/
		
	}

	@Override
	public boolean readOneEmail(String email) {
		return memberDao.selectOneEmail(email) > 0;
	}

	@Override
	public boolean isBlockUser(String email) {
		Integer blockUserAccount = memberDao.isBlockUser(email);
		if (blockUserAccount == null) {
			blockUserAccount = 0;
		}
		return blockUserAccount >= 3;
	}

}