package com.ktds.ysproject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ktds.ysproject.member.biz.MemberBiz;
import com.ktds.ysproject.member.vo.MemberVO;

@Component("userService")
public class UserService implements AuthenticationProvider{

	@Autowired
	public MemberBiz memberBiz;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getPrincipal().toString();
		System.out.println("!!!!씨;"+authentication.getCredentials().toString());
		String userPassword = authentication.getCredentials().toString();
		System.out.println(email);
		System.out.println(userPassword);
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail(email);
		memberVO.setPassword(userPassword);
		
		boolean isBlockAccount = memberBiz.isBlockUser(memberVO.getEmail());
		boolean isLoginSuccess = false;
		
		if ( !isBlockAccount ){
			isLoginSuccess = memberBiz.readOneEmail(memberVO.getEmail());
			
			if ( !isLoginSuccess ){
				memberBiz.increaseLoginFailCount(memberVO.getEmail());
			}
			else {
				memberBiz.unblockUser(memberVO.getEmail());
			}
		}
		
		// 로그인 횟수 제한 방어코드 작성
		
		UsernamePasswordAuthenticationToken result = null;
				
		if ( isLoginSuccess ) {
			String token = UUID.randomUUID().toString();
			String grade;
			if ( memberVO.getMemberAuthority() == 0 ) {
				grade = "ROLE_ADMIN";
			}
			else if ( memberVO.getMemberAuthority() == 1 ) {
				grade = "REVIEW_USER";
			}
			else {
				grade = "ROLE_USER";
			}
			List<GrantedAuthority> roles = new ArrayList<>();
			if (grade.equals("ROLE_ADMIN")) {
				roles.add(new SimpleGrantedAuthority("ROLE_USER"));
				roles.add(new SimpleGrantedAuthority("REVIEW_USER"));
			}
			else if (grade.equals("REVIEW_USER")) {
				roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			}
			
			result = new UsernamePasswordAuthenticationToken(email, userPassword);
			User user = new User(email, userPassword, grade, isBlockAccount, token);
			result.setDetails(user);
		}
		else {
			throw new BadCredentialsException("잘못된 인증");
		}
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
	
}
