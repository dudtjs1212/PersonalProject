package com.ktds.ysproject.board.dao;

import java.util.List;

import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;

public interface BoardDao {
 
	public int insertOneBoard(BoardVO boardVO);
	
	public BoardVO selectOneBoard(String boardId);
	
	public List<BoardVO> selectAllBoards(BoardSearchVO boardSearchVO);
	
	public int selectAllBoardsCount(BoardSearchVO boardSearchVO);
	
	public int updateOneBoard(BoardVO boardVO);
	
	public int updateViewCount(String boardId);
	
	public List<BoardVO> selectAllDivisionZeroBoard();

	public List<BoardVO> selectAllDivisionOneBoard();
	
	public List<BoardVO> selectAllDivisionTwoBoard();
	
	public List<BoardVO> selectAllDivisionThreeBoard();
	
	public int deleteOneBoard(String boardId);
	
}
