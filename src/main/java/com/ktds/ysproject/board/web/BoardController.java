package com.ktds.ysproject.board.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.ysproject.User;
import com.ktds.ysproject.board.service.BoardService;
import com.ktds.ysproject.board.vo.BoardSearchVO;
import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.common.web.DownloadUtil;
import com.ktds.ysproject.member.vo.MemberVO;
import com.nhncorp.lucy.security.xss.XssFilter;

import io.github.seccoding.web.pager.explorer.PageExplorer;
  

@Controller

@Secured({"ROLE_ADMIN", "ROLE_REVIEW", "ROLE_USER"})
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	@Qualifier("videoUploadPath")
	private String videoUploadPath;
	
	@Autowired
	@Qualifier("posterUploadPath")
	private String posterUploadPath;
	
	@RequestMapping("/board/list/{boardDivision}/init")
	public String viewBoardListPageForInitiate(@PathVariable int boardDivision, @ModelAttribute BoardVO boardVO, HttpSession session) {
		session.removeAttribute("_SEARCH_");
		return "redirect:/board/list/" + boardVO.getBoardDivision();
	}
	
	@GetMapping("/main/home")
	public ModelAndView viewMainPage() {
		ModelAndView view = new ModelAndView("/main/home");
		List<BoardVO> boardZero = boardService.readAllDivisionZeroBoard();
		List<BoardVO> boardOne = boardService.readAllDivisionOneBoard();
		List<BoardVO> boardTwo = boardService.readAllDivisionTwoBoard();
		List<BoardVO> boardThree = boardService.readAllDivisionThreeBoard();
		
		view.addObject("boardZero", boardZero);
		view.addObject("boardOne", boardOne);
		view.addObject("boardTwo", boardTwo);
		view.addObject("boardThree", boardThree);
		//MemberVO member = boardService.readOneBoard(boardId, memberVO);
		//view.addObject("param",param);
		return view;
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_REVIEW"})
	@GetMapping("/board/write/{boardDivision}")
	public ModelAndView viewCreateOneBoardPage(@PathVariable int boardDivision) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		ModelAndView view = new ModelAndView("board/write");
		
		if ( boardDivision == 0 ) {
			if ( !user.getGrade().equals("ROLE_ADMIN")) {
				view = new ModelAndView("redirect:/board/list/" + boardDivision);
			}
		}
		else if ( boardDivision != 4 ) {
			if ( !user.getGrade().equals("ROLE_ADMIN") && !user.getGrade().equals("ROLE_REVIEW")) {
				view = new ModelAndView("redirect:/board/list/" + boardDivision);
			}
		}
		
		
		view.addObject("boardDivision", boardDivision);
		return view;
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_REVIEW" })
	@PostMapping("/board/write")
	public ModelAndView doBoardWriteAction(@Valid @ModelAttribute BoardVO boardVO, Errors errors, @SessionAttribute("_USER_") MemberVO memberVO, HttpSession session) {
		ModelAndView view = new ModelAndView("redirect:/board/list/" + boardVO.getBoardDivision());
		
		String sessionToken = (String) session.getAttribute("_TOKEN_");
		if ( !boardVO.getToken().equals(sessionToken) ){
			throw new RuntimeException("잘못된 접근입니다.");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
		if ( boardVO.getBoardDivision() == 0 ) {
			if ( !user.getGrade().equals("ROLE_ADMIN")) {
				view = new ModelAndView("redirect:/board/list/" + boardVO.getBoardDivision());
				return view;
			}
		}
		else if ( boardVO.getBoardDivision() != 4 ) {
			if ( !user.getGrade().equals("ROLE_ADMIN") && !user.getGrade().equals("ROLE_REVIEW")) {
				view = new ModelAndView("redirect:/board/list/" + boardVO.getBoardDivision());
				return view;
			}
		}
		
		String email = ((MemberVO) session.getAttribute("_USER_")).getEmail();
		boardVO.setEmail(email);
		
		
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
		boardService.createOneBoard(boardVO);
		
		return view;
	}
	
	
	@RequestMapping("/board/list/{boardDivision}")
	public ModelAndView viewBoardListPage(@PathVariable int boardDivision, @ModelAttribute BoardSearchVO boardSearchVO, HttpServletRequest request, HttpSession session) {
		session.removeAttribute("_SEARCH_");
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
		
		System.out.println(pageExplorer.getList());
		
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
	
	@Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_REVIEW" })
	@RequestMapping("/board/detail/{id}")
	public ModelAndView viewOneBoardDetailPage(@SessionAttribute("_USER_") MemberVO memberVO, @PathVariable String id, HttpSession session) {
		ModelAndView view = new ModelAndView("board/detail");
		BoardVO board = boardService.readOneBoard(id, session);
		view.addObject("board", board);
		return view;
		
	}
	
	@GetMapping("/board/modify/{id}")
	public ModelAndView viewOneBoardModifyPage(@SessionAttribute("_USER_") MemberVO memberVO, @PathVariable String id, @ModelAttribute BoardVO boardVO) {
		ModelAndView view = new ModelAndView("board/modify");
		BoardVO board = boardService.readOneBoard(id);
		if ( !board.getEmail().equals(memberVO.getEmail()) ) {
			view = new ModelAndView("redirect:/board/detail/" + id);
		}
		
		view.addObject("board", board);
		return view;
		
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_REVIEW" })
	@PostMapping("/board/modify")
	public ModelAndView doBoardmodifyAction(@Valid @ModelAttribute BoardVO boardVO, @SessionAttribute("_USER_") MemberVO memberVO, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView("redirect:/board/detail/"+boardVO.getBoardId());
		
		/*String sessionToken = (String) session.getAttribute("_TOKEN_");
		if ( !boardVO.getToken().equals(sessionToken) ){
			throw new RuntimeException("잘못된 접근입니다.");
		}*/
		
		if ( !boardVO.getToken().equals(session.getAttribute("_TOKEN_")) ) {
			return view = new ModelAndView("redirect:/board/detail/" + boardVO.getBoardId());
		}
		
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
		
		if ( errors.hasErrors() ) {
			view.setViewName("board/modify/"+boardVO.getBoardId());
			view.addObject("boardVO", boardVO);
			return view;
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
	
	@GetMapping("/board/delete")
	public String deleteOneBoardAction(@RequestParam String boardId, @RequestParam int boardDivision) {
		
		boolean isSuccess = boardService.deleteOneBoard(boardId);
		
		if ( isSuccess ) {
			return "redirect:/board/list/" + boardDivision;
		}
		else {
			return "redirect:/board/detail/" + boardId;
		}
		
		
		
	}
}
