package com.ktds.ysproject.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.ysproject.board.biz.BoardBiz;
import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardBiz boardBiz;
	
	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		return boardBiz.createOneBoard(boardVO);
	}

	@Override
	public BoardVO readOneBoard(String boardId, MemberVO memberVO) {
		return boardBiz.readOneBoard(boardId, memberVO);
	}

	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) {
		return boardBiz.readAllBoards(boardSearchVO);
	}

}
