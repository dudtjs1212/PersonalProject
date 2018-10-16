package com.ktds.ysproject.member.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.ysproject.User;
import com.ktds.ysproject.common.web.DownloadUtil;
import com.ktds.ysproject.member.service.MemberService;
import com.ktds.ysproject.member.validator.MemberValidator;
import com.ktds.ysproject.member.vo.MemberVO;

@Controller
public class MemberController {
 
	@Autowired
	private MemberService memberService;
	
	@Autowired
	@Qualifier("profileImgPath")
	private String profileImgPath;
	
	@GetMapping("/member/regist")
	public String viewRegistPage() {
		return "member/regist";
	}
	
	@GetMapping("/member/logout")
	public String doMemberLogoutAction(HttpSession session) {
		// Logout
		session.invalidate();	//session 다 날려버리기
		return "redirect:/main/home";
	}
	
	@PostMapping("/member/regist")
	public ModelAndView doRegistNewMemberAction(@Validated({MemberValidator.Regist.class}) @ModelAttribute MemberVO memberVO , Errors errors) {
		MultipartFile file = memberVO.getFile();
		if ( !file.isEmpty()) {
			String fileName = UUID.randomUUID().toString();
			memberVO.setProfileImg(fileName);
			
			File dir = new File(profileImgPath);
			if ( !dir.exists() ) {
				dir.mkdirs();
			}
			
			File dest = new File(profileImgPath, fileName);
			
			try {
				file.transferTo(dest);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		else {
			memberVO.setProfileImg(" ");
		}
		ModelAndView view = new ModelAndView("redirect:/member/login");
		
		if ( errors.hasErrors() ) {
			view.setViewName("redirect:/member/regist?error=1");
			view.addObject(memberVO);
			return view;
		}
		
		boolean isSuccess = memberService.registNewMember(memberVO);
		if ( !isSuccess ) {
			view.setViewName("member/regist");
			return view;
		}
		return view;
	}
	
	@PostMapping("/member/duplicate")
	@ResponseBody
	public Map<String, Object> doCheckDuplicateEmail(@RequestParam String email){
		boolean selectCheckEmail = memberService.readOneEmail(email);
		Map<String, Object> result = new HashMap<>();
		if (selectCheckEmail) {
			result.put("status","이미 등록된 email 입니다.");
			result.put("duplicated", selectCheckEmail);  // 여기에 select check 한 결과 넣기.
		}
		return result;
	}
	
	@PostMapping("/member/validate")
	@ResponseBody
	public Map<String, Object> doCheckRegist(@Validated({MemberValidator.Regist.class}) @ModelAttribute MemberVO memberVO, Errors errors, HttpSession session) {
		Map<String, Object> result = new HashMap<>();

		if( errors.hasErrors() ) {
			StringBuilder errorMessage = new StringBuilder();
			for ( FieldError fieldError : errors.getFieldErrors() ) {
				errorMessage.append(fieldError.getDefaultMessage());
				errorMessage.append("\n");
				result.put("message", errorMessage);
			}
			result.put("status", false);
			session.setAttribute("_VAILDCHECK_", memberVO);
			return result;
		}
		result.put("status", true);
		this.memberService.registNewMember(memberVO);
		return result;
	}
	

	@GetMapping("/member/login")
	public String viewLoginPage() {
		return "member/login";
	}
	
	@GetMapping("/member/loginSuccess")
	@ResponseBody
	public Map<String, Object> doMemberLoginAction(@Validated({MemberValidator.Login.class}) @ModelAttribute MemberVO memberVO, 
													Errors errors, HttpSession session) {
		//ModelAndView view = new ModelAndView("/member/login");
		
		//boolean isBlockUser = memberService.isBlockUser(memberVO.getEmail());
		Map<String, Object> result = new HashMap<>();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		MemberVO loginMember = memberService.readOneMember(memberVO);
		loginMember.setEmail(user.getEmail());
		loginMember.setPassword(user.getPassword());
		result.put("loginStatus", false);
		if ( loginMember != null ) {
			session.setAttribute("_TOKEN_", loginMember);
			result.put("loginStatus", true);
			result.put("message", "Login 되었습니다.");
		}
		
		/*if ( isBlockUser ) {
			result.put("message","블락유저");
			result.put("status", isBlockUser);  // 여기에 select check 한 결과 넣기.
		}
		else {
			MemberVO loginMember = memberService.readOneMember(memberVO);
			if ( loginMember != null ) {
				session.setAttribute("_USER_", loginMember);
				result.put("loginStatus", true);
				result.put("message", "Login 되었습니다.");
			}
			else {
				result.put("loginStatus", false);
				result.put("message", "Login을 실패하였습니다");
			}
		}*/
		return result;
		
		/*if ( errors.hasErrors() ) {
			view.setViewName("redirect:/member/login?error=1");
			view.addObject(memberVO);
			return view;
		}		
		MemberVO param = this.memberService.readOneMember(memberVO);
		
		if ( param != null ) {
			view.setViewName("redirect:/main/home");
			session.setAttribute("_USER_", param);
			return view;
		}
		else {
			view.addObject("memberVO", memberVO);
			view.setViewName("redirect:/member/login?error=1");
			return view;
		}*/
		
		
	}
	
	@GetMapping("/member/download/{fileName}")
	   public void download(
	                  @PathVariable String fileName
	                  ,HttpServletRequest request
	                  , HttpServletResponse response) {
	      try {
	         new DownloadUtil(this.profileImgPath + File.separator + fileName)
	                     .download(request, response, fileName);
	      } catch (UnsupportedEncodingException e) {
	         throw new RuntimeException(e.getMessage(), e);
	      }
	   }
	
}
