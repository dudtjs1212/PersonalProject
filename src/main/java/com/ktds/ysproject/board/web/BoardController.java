package com.ktds.ysproject.board.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.ysproject.board.service.BoardService;
import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.member.vo.MemberVO;


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
		/*MemberVO member = boardService.readOneBoard();
		view.addObject("videoList",videoList);*/
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
		
		String videoPath = UUID.randomUUID().toString();
		String posterPath = UUID.randomUUID().toString();
		System.out.println(videoPath);
		System.out.println(posterPath);
		
		File videodir = new File(videoUploadPath);
		if ( !videodir.exists() ) {
			videodir.mkdirs();
		}
		File posterdir = new File(posterUploadPath);
		if ( !posterdir.exists() ) {
			posterdir.mkdirs();
		}
		
		File destVideoFile = new File(videoUploadPath, videoPath);
		File destposterFile = new File(posterUploadPath, posterPath);
		
		try {
			video.transferTo(destVideoFile);
			poster.transferTo(destposterFile);
			boardVO.setVideoPath(videoPath);
			boardVO.setPosterPath(posterPath);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		boardVO.setEmail(memberVO.getEmail());
		if ( boardVO.getUrlAddress() == null ) {
			boardVO.setUrlAddress("");
		}
		boardService.createOneBoard(boardVO);
		
		return view;
	}
	
	@GetMapping("/board/list")
	public String viewBoardListPage() {
		return "board/list";
	}
	
}
