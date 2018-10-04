package com.ktds.ysproject.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.ysproject.member.vo.MemberVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertNewMember(MemberVO memberVO) {
		return getSqlSession().insert("MemberDao.insertNewMember", memberVO);
	}

	@Override
	public MemberVO selectOneMember(MemberVO memberVO) {
		return getSqlSession().selectOne("MemberDao.selectOneMember", memberVO);
	}

	@Override
	public String selectSaltById(String email) {
		return getSqlSession().selectOne("MemberDao.selectSaltById", email);
	}

	@Override
	public int selectOneEmail(String email) {
		return getSqlSession().selectOne("MemberDao.selectOneEmail", email);
	}

	@Override
	public Integer isBlockUser(String email) {
		return getSqlSession().selectOne("MemberDao.isBlockUser", email);
	}

	@Override
	public int unblockUser(String email) {
		return getSqlSession().update("MemberDao.unblockUser", email);
	}

	@Override
	public int increaseLoginFailCount(String email) {
		return getSqlSession().update("MemberDao.increaseLoginFailCount", email);
	}
	
	
}