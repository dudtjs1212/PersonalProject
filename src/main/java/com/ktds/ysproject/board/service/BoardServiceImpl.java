package com.ktds.ysproject.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.ysproject.board.biz.BoardBiz;
import com.ktds.ysproject.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardBiz boardBiz;
	
	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		return boardBiz.createOneBoard(boardVO);
	}

}
