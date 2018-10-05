package com.ktds.ysproject.board.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.ysproject.board.service.BoardService;
import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;


@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	@Qualifier("videoUploadPath")
	private String videoUploadPath;
	
	@Autowired
	@Qualifier("posterUploadPath")
	private String posterUploadPath;
	
	@GetMapping("/main/home")
	public ModelAndView viewMainPage() {
		ModelAndView view = new ModelAndView("/main/home");
		//MemberVO member = boardService.readOneBoard(boardId, memberVO);
		//view.addObject("param",param);
		return view;
	}
	
	@GetMapping("/board/write")
	public String viewCreateOneBoardPage() {
		return "board/write";
	}
	
	@PostMapping("/board/write")
	public ModelAndView doBoardWriteAction(@ModelAttribute BoardVO boardVO, @SessionAttribute("_USER_") MemberVO memberVO) {
		ModelAndView view = new ModelAndView("redirect:/board/list");
		
		MultipartFile video = boardVO.getVideo();
		MultipartFile poster = boardVO.getPoster();
		
		System.out.println("!!!:" + video);
		System.out.println("!!!:" + poster);
		
		if ( !video.isEmpty() ) {
			String videoPath = UUID.randomUUID().toString();
			
			File videodir = new File(videoUploadPath);
			if ( !videodir.exists() ) {
				videodir.mkdirs();
			}
			
			File destVideoFile = new File(videoUploadPath, videoPath);
			
			try {
				video.transferTo(destVideoFile);
				boardVO.setVideoPath(videoPath);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		else {
			boardVO.setVideoPath("");
		}
		
		
		if ( !poster.isEmpty() ) { 
			String posterPath = UUID.randomUUID().toString();
			
			File posterdir = new File(posterUploadPath);
			if ( !posterdir.exists() ) {
				posterdir.mkdirs();
			}
			
			
			File destposterFile = new File(posterUploadPath, posterPath);
			
			try {
				poster.transferTo(destposterFile);
				boardVO.setPosterPath(posterPath);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		else {
			boardVO.setPosterPath("");
		}
		
		boardVO.setEmail(memberVO.getEmail());
		if ( boardVO.getUrlAddress() == null ) {
			boardVO.setUrlAddress("");
		}
		boardService.createOneBoard(boardVO);
		
		return view;
	}
	
	@RequestMapping("/board/list")
	public ModelAndView viewBoardListPage(@ModelAttribute BoardSearchVO boardSearchVO, HttpServletRequest request, HttpSession session) {
		
		// 전체 검색 or 상세 -> 목록 or 글쓰기
		if ( boardSearchVO.getSearchKeyword() == null ) {
			boardSearchVO = (BoardSearchVO) session.getAttribute("_SEARCH_");
			if ( boardSearchVO == null ) {
				boardSearchVO = new BoardSearchVO();
				boardSearchVO.setPageNo(0);
			}
		}
		
		//html태그, 게시글, 페이지정보
		PageExplorer pageExplorer = this.boardService.readAllBoards(boardSearchVO);
		for ( Object boardVO : pageExplorer.getList() ) {
			BoardVO convertVO = (BoardVO) boardVO;
		}
		
		
		session.setAttribute("_SEARCH_", boardSearchVO);
		
		ModelAndView view = new ModelAndView("/board/list");
		view.addObject("boardList", pageExplorer.getList());
		view.addObject("pagenation", pageExplorer.make());
		view.addObject("boardSearchVO", boardSearchVO);
		return view;
	}
	
	@RequestMapping("/board/detail/{id}")
	public ModelAndView viewOneBoardDetailPage(@SessionAttribute("_USER_") MemberVO memberVO, @PathVariable String id) {
		ModelAndView view = new ModelAndView("board/detail");
		BoardVO board = boardService.readOneBoard(id, memberVO);
		view.addObject("board", board);
		return view;
		
	}
}
