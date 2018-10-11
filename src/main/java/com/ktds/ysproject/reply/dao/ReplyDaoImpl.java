package com.ktds.ysproject.reply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.ysproject.reply.vo.ReplyVO;

@Repository
public class ReplyDaoImpl extends SqlSessionDaoSupport implements ReplyDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertOneReply(ReplyVO replyVO) {
		return getSqlSession().insert("ReplyDao.insertOneReply", replyVO);
	}

	@Override
	public List<ReplyVO> selectAllReplies(String boardId) {
		return getSqlSession().selectList("ReplyDao.selectAllReplies", boardId);
	}

	@Override
	public int deleteReply(ReplyVO replyVO) {
		return getSqlSession().delete("ReplyDao.deleteReply", replyVO);
	}

	@Override
	public ReplyVO selectOneReply(String replyId) {
		return getSqlSession().selectOne("ReplyDao.selectOneReply", replyId);
	}
 
	
	
}
