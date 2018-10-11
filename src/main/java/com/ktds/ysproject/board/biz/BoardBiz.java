package com.ktds.ysproject.board.biz;

import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;
  
public interface BoardBiz {

	public boolean createOneBoard(BoardVO boardVO);
	
	public BoardVO readOneBoard(String boardId);
	
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO);
	
	public boolean updateOneBoard(BoardVO boardVO);
}
