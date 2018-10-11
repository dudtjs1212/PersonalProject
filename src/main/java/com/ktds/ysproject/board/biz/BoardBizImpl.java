package com.ktds.ysproject.board.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.ysproject.board.dao.BoardDao;
import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.member.vo.MemberVO;

import io.github.seccoding.web.pager.Pager;
import io.github.seccoding.web.pager.PagerFactory;
import io.github.seccoding.web.pager.explorer.ClassicPageExplorer;
import io.github.seccoding.web.pager.explorer.PageExplorer;
 
@Component
public class BoardBizImpl implements BoardBiz {
 
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		return boardDao.insertOneBoard(boardVO) > 0;
	}

	@Override
	public BoardVO readOneBoard(String boardId) {
		this.boardDao.updateViewCount(boardId);
		return boardDao.selectOneBoard(boardId);
	}

	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) {
		int totalCount = this.boardDao.selectAllBoardsCount(boardSearchVO);		// 게시물의 개수를 count해서 페이지의 수 계산
		
		Pager pager = PagerFactory.getPager(Pager.ORACLE, boardSearchVO.getPageNo()+"");	// Oracle페이지, 현재 볼 페이지 선택 (몇번부터 몇번까지의 정보 나옴)
		
		pager.setTotalArticleCount(totalCount);
		
		PageExplorer pageExplorer = pager.makePageExplorer(ClassicPageExplorer.class, boardSearchVO); // 시작번호와 끝번호가 나옴
		
		pageExplorer.setList( this.boardDao.selectAllBoards(boardSearchVO) );
		
		return pageExplorer;
	}

	@Override
	public boolean updateOneBoard(BoardVO boardVO) {
		return boardDao.updateOneBoard(boardVO)>0;
	}
}
