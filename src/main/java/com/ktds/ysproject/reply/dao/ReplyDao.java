package com.ktds.ysproject.reply.dao;

import java.util.List;

import com.ktds.ysproject.reply.vo.ReplyVO;

public interface ReplyDao {
 
	public int insertOneReply(ReplyVO replyVO);
	
	public List<ReplyVO> selectAllReplies(String boardId);
	
	public int deleteReply(ReplyVO replyVO);
	
	public ReplyVO selectOneReply(String replyId);
	
}
