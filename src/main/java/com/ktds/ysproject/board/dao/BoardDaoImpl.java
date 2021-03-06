package com.ktds.ysproject.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;

@Repository
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao {
 
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertOneBoard(BoardVO boardVO) {
		return getSqlSession().insert("BoardDao.insertOneBoard", boardVO);
	}

	@Override
	public BoardVO selectOneBoard(String boardId) {
		return getSqlSession().selectOne("BoardDao.selectOneBoard", boardId);
	}

	@Override
	public List<BoardVO> selectAllBoards(BoardSearchVO boardSearchVO) {
		return getSqlSession().selectList("BoardDao.selectAllBoards", boardSearchVO);
	}

	@Override
	public int selectAllBoardsCount(BoardSearchVO boardSearchVO) {
		return getSqlSession().selectOne("BoardDao.selectAllBoardsCount", boardSearchVO);
	}

	@Override
	public int updateOneBoard(BoardVO boardVO) {
		return getSqlSession().update("BoardDao.updateOneBoard", boardVO);
	}

	@Override
	public int updateViewCount(String boardId) {
		return getSqlSession().update("BoardDao.updateViewCount", boardId);
	}

	@Override
	public List<BoardVO> selectAllDivisionZeroBoard() {
		return getSqlSession().selectList("BoardDao.selectAllDivisionZeroBoard");
	}

	@Override
	public List<BoardVO> selectAllDivisionOneBoard() {
		return getSqlSession().selectList("BoardDao.selectAllDivisionOneBoard");
	}

	@Override
	public List<BoardVO> selectAllDivisionTwoBoard() {
		return getSqlSession().selectList("BoardDao.selectAllDivisionTwoBoard");
	}

	@Override
	public List<BoardVO> selectAllDivisionThreeBoard() {
		return getSqlSession().selectList("BoardDao.selectAllDivisionThreeBoard");
	}

	@Override
	public int deleteOneBoard(String boardId) {
		return getSqlSession().update("BoardDao.deleteOneBoard", boardId);
	}
	
	

}
