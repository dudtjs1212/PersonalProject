package com.ktds.ysproject.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

public interface BoardService {
 
	public boolean createOneBoard(BoardVO boardVO);
	
	public BoardVO readOneBoard(String boardId);

	public BoardVO readOneBoard(String boardId, HttpSession session);
	
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO);
	
	public boolean updateOneBoard(BoardVO boardVO);
	
	public List<BoardVO> readAllDivisionZeroBoard();

	public List<BoardVO> readAllDivisionOneBoard();
	
	public List<BoardVO> readAllDivisionTwoBoard();
	
	public List<BoardVO> readAllDivisionThreeBoard();
	
}
