package com.ktds.ysproject.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.ysproject.board.biz.BoardBiz;
import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.reply.biz.ReplyBiz;
import com.ktds.ysproject.reply.vo.ReplyVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

@Service
public class BoardServiceImpl implements BoardService{
 
	@Autowired
	private BoardBiz boardBiz;
	
	@Autowired
	private ReplyBiz replyBiz;
	
	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		return boardBiz.createOneBoard(boardVO);
	}

	@Override
	public BoardVO readOneBoard(String boardId) {
		BoardVO boardVO = boardBiz.readOneBoard(boardId);
		
		List<ReplyVO> replyList = replyBiz.selectAllReplies(boardId);
		boardVO.setReplyList(replyList);
		return boardVO;
	}

	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) {
		return boardBiz.readAllBoards(boardSearchVO);
	}

	@Override
	public boolean updateOneBoard(BoardVO boardVO) {
		return boardBiz.updateOneBoard(boardVO);
	}

}
