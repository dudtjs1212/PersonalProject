package com.ktds.ysproject.board.dao;

import java.util.List;

import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;

public interface BoardDao {

	public int insertOneBoard(BoardVO boardVO);
	
	public BoardVO selectOneBoard(String boardId);
	
	public List<BoardVO> selectAllBoards(BoardSearchVO boardSearchVO);
	
	public int selectAllBoardsCount(BoardSearchVO boardSearchVO);
	
}
