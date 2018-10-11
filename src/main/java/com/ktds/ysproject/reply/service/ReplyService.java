package com.ktds.ysproject.reply.service;

import com.ktds.ysproject.reply.vo.ReplyVO;

public interface ReplyService {

	public boolean createOneReply(ReplyVO replyVO);
	
	public boolean deleteReply(ReplyVO replyVO);
	
	public ReplyVO readOneReply(String replyId);
	
}
