package com.ktds.ysproject.reply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.ysproject.reply.biz.ReplyBiz;
import com.ktds.ysproject.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyBiz replyBiz;

	@Override
	public boolean createOneReply(ReplyVO replyVO) {
		return replyBiz.createOneReply(replyVO);
	}

	@Override
	public boolean deleteReply(ReplyVO replyVO) {
		return replyBiz.deleteReply(replyVO);
	}

	@Override
	public ReplyVO readOneReply(String replyId) {
		return replyBiz.readOneReply(replyId);
	}
	
	
 
	
	
}
