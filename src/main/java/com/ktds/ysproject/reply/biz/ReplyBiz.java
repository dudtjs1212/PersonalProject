package com.ktds.ysproject.reply.biz;

import java.util.List;

import com.ktds.ysproject.reply.vo.ReplyVO;

public interface ReplyBiz {
	
	public boolean createOneReply(ReplyVO replyVO);
	
	public List<ReplyVO> selectAllReplies(String boardId);
	
	public boolean deleteReply(ReplyVO replyVO);
	
	public ReplyVO readOneReply(String replyId);
	
}
