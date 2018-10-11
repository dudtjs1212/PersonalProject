package com.ktds.ysproject.reply.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.ysproject.reply.dao.ReplyDao;
import com.ktds.ysproject.reply.vo.ReplyVO;

@Component
public class ReplyBizImpl implements ReplyBiz{

	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public boolean createOneReply(ReplyVO replyVO) {
		return replyDao.insertOneReply(replyVO) > 0;
	}

	@Override
	public List<ReplyVO> selectAllReplies(String boardId) {
		return replyDao.selectAllReplies(boardId);
	}

	@Override
	public boolean deleteReply(ReplyVO replyVO) {
		return replyDao.deleteReply(replyVO) > 0;
	}

	@Override
	public ReplyVO readOneReply(String replyId) {
		return replyDao.selectOneReply(replyId);
	}
	
	
	
}
