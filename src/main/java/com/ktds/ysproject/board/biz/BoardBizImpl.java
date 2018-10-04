package com.ktds.ysproject.board.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.ysproject.board.dao.BoardDao;
import com.ktds.ysproject.board.vo.BoardVO;

@Component
public class BoardBizImpl implements BoardBiz {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		return boardDao.insertOneBoard(boardVO) > 0;
	}

}
