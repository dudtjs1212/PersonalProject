package com.ktds.ysproject.board.dao;

import com.ktds.ysproject.board.vo.BoardVO;

public interface BoardDao {

	public int insertOneBoard(BoardVO boardVO);
	
	public BoardVO selectOneBoard(BoardVO boardVO);
	
}
