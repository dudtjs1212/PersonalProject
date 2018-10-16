package com.ktds.ysproject.board.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
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
import com.ktds.ysproject.common.web.DownloadUtil;
import com.ktds.ysproject.member.vo.MemberVO;
import com.nhncorp.lucy.security.xss.XssFilter;

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
	
	@RequestMapping("/board/list/init")
	public String viewBoardListPageForInitiate(HttpSession session) {
		session.removeAttribute("_SEARCH_");
		return "redirect:/board/list";
	}
	
	@GetMapping("/main/home")
	public ModelAndView viewMainPage() {
		ModelAndView view = new ModelAndView("/main/home");
		//MemberVO member = boardService.readOneBoard(boardId, memberVO);
		//view.addObject("param",param);
		return view;
	}
	
	@GetMapping("/board/write/{boardDivision}")
	public ModelAndView viewCreateOneBoardPage(@PathVariable int boardDivision) {
		ModelAndView view = new ModelAndView("board/write");
		view.addObject("boardDivision", boardDivision);
		return view;
	}
	
	@PostMapping("/board/write")
	public ModelAndView doBoardWriteAction(@Valid @ModelAttribute BoardVO boardVO, Errors errors, @SessionAttribute("_USER_") MemberVO memberVO) {
		ModelAndView view = new ModelAndView("redirect:/board/list/" + boardVO.getBoardDivision());
		
		XssFilter xssFilter = XssFilter.getInstance("lucy-xss-superset.xml");
		boardVO.setContent(xssFilter.doFilter(boardVO.getTitle()));
		boardVO.setContent(xssFilter.doFilter(boardVO.getContent()));
		
		MultipartFile video = boardVO.getVideo();
		MultipartFile poster = boardVO.getPoster();
		
		
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
		if ( errors.hasErrors() ) {
			view.setViewName("board/write/" + boardVO.getBoardDivision());
			view.addObject("boardVO", boardVO);
			return view;
		}
		System.out.println("boardVO" + boardVO.getBoardDivision());
		boardService.createOneBoard(boardVO);
		
		return view;
	}
	
	@RequestMapping("/board/list/{boardDivision}")
	public ModelAndView viewBoardListPage(@PathVariable int boardDivision, @ModelAttribute BoardSearchVO boardSearchVO, HttpServletRequest request, HttpSession session) {
		
		// 전체 검색 or 상세 -> 목록 or 글쓰기
		if ( boardSearchVO.getSearchKeyword() == null ) {
			boardSearchVO = (BoardSearchVO) session.getAttribute("_SEARCH_");
			if ( boardSearchVO == null ) {
				boardSearchVO = new BoardSearchVO();
				boardSearchVO.setPageNo(0);
			}
		}
		
		boardSearchVO.setBoardDivision(boardDivision);
		
		//html태그, 게시글, 페이지정보
		PageExplorer pageExplorer = this.boardService.readAllBoards(boardSearchVO);
		
		for ( Object boardVO : pageExplorer.getList() ) {
			BoardVO convertVO = (BoardVO) boardVO;
		}
		
		session.setAttribute("_SEARCH_", boardSearchVO);
		
		
		ModelAndView view = new ModelAndView("/board/list");
		view.addObject("boardDivision", boardSearchVO.getBoardDivision());
		view.addObject("boardList", pageExplorer.getList());
		view.addObject("pagenation", pageExplorer.make());
		view.addObject("boardSearchVO", boardSearchVO);
		return view;
	}
	
	@RequestMapping("/board/detail/{id}")
	public ModelAndView viewOneBoardDetailPage(@SessionAttribute("_USER_") MemberVO memberVO, @PathVariable String id) {
		ModelAndView view = new ModelAndView("board/detail");
		BoardVO board = boardService.readOneBoard(id);
		view.addObject("board", board);
		return view;
		
	}
	
	@GetMapping("/board/modify/{id}")
	public ModelAndView viewOneBoardModifyPage(@SessionAttribute("_USER_") MemberVO memberVO, @PathVariable String id, @ModelAttribute BoardVO boardVO) {
		ModelAndView view = new ModelAndView("board/modify");
		
		BoardVO board = boardService.readOneBoard(id);
		view.addObject("board", board);
		return view;
		
	}
	
	@PostMapping("/board/modify")
	public ModelAndView doBoardmodifyAction(@ModelAttribute BoardVO boardVO, @SessionAttribute("_USER_") MemberVO memberVO) {
		ModelAndView view = new ModelAndView("redirect:/board/detail/"+boardVO.getBoardId());
		
		XssFilter xssFilter = XssFilter.getInstance("lucy-xss-superset.xml");
		boardVO.setContent(xssFilter.doFilter(boardVO.getTitle()));
		boardVO.setContent(xssFilter.doFilter(boardVO.getContent()));
		
		MultipartFile video = boardVO.getVideo();
		MultipartFile poster = boardVO.getPoster();
		
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
		}
		
		if ( boardVO.getUrlAddress() == null ) {
			boardVO.setUrlAddress("");
		}
		
		boolean isSuccess = boardService.updateOneBoard(boardVO);
		if ( !isSuccess ) {
			view = new ModelAndView("redirect:/board/modify/"+boardVO.getBoardId());
			BoardVO board = boardService.readOneBoard(boardVO.getBoardId());
			view.addObject("board", board);
		}
		return view;
	}
	
	@GetMapping("/board/video/download/{fileName}")
	public void videodownload(
                  @PathVariable String fileName
                  ,HttpServletRequest request
                  , HttpServletResponse response) {
		try {
		   new DownloadUtil(this.videoUploadPath + File.separator + fileName)
		               .download(request, response, fileName);
		} catch (UnsupportedEncodingException e) {
		   throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@GetMapping("/board/poster/download/{fileName}")
	public void posterdownload(
                  @PathVariable String fileName
                  ,HttpServletRequest request
                  , HttpServletResponse response) {
		try {
		   new DownloadUtil(this.posterUploadPath + File.separator + fileName)
		               .download(request, response, fileName);
		} catch (UnsupportedEncodingException e) {
		   throw new RuntimeException(e.getMessage(), e);
		}
	}
}
