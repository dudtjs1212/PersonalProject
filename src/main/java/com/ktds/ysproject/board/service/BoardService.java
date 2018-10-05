package com.ktds.ysproject.board.service;

import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

public interface BoardService {

	public boolean createOneBoard(BoardVO boardVO);
	
	public BoardVO readOneBoard(String boardId, MemberVO memberVO);
	
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO);
	
}
