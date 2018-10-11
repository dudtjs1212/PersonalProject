package com.ktds.ysproject.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.ktds.ysproject.member.validator.MemberValidator;

public class MemberVO {
  
	@NotEmpty(message="이메일은 필수 입력 값입니다.", groups= {MemberValidator.Login.class,MemberValidator.Regist.class})
	@Email(message="이메일 형식으로 작성해주세요.", groups= {MemberValidator.Login.class,MemberValidator.Regist.class})
	private String email;
	@NotEmpty(message="이름은 필수 입력 값입니다.", groups= {MemberValidator.Regist.class})
	private String name;
	@NotEmpty(message="별명은 필수 입력 값입니다.", groups= {MemberValidator.Regist.class})
	private String nickname;
	@NotEmpty(message="비밀번호는 필수 입력 값입니다.", groups= {MemberValidator.Regist.class, MemberValidator.Login.class})
	@Length(min=8, max=20, groups= {MemberValidator.Regist.class})
	@Pattern(regexp="((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).{8,})",message="비밀번호는 8글자 이상 대소문자, 숫자, 특수문자 조합으로 설정해야 합니다.",  groups = {MemberValidator.Regist.class})
	private String password;
	private String salt;
	private String profileImg;
	private int loginFailCount;
	private int memberAuthority;
	private String loginFailTime;

	private MultipartFile file;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public int getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public int getMemberAuthority() {
		return memberAuthority;
	}

	public void setMemberAuthority(int memberAuthority) {
		this.memberAuthority = memberAuthority;
	}

	public String getLoginFailTime() {
		return loginFailTime;
	}

	public void setLoginFailTime(String loginFailTime) {
		this.loginFailTime = loginFailTime;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		String format = "MemberVO [Email : %s, Name : %s, nickname : %s, Password: %s, salt : %s, profileImg : %s , loginFailCount : %d, memberAuthority : %d, loginFailTime : %s]";
		return String.format(format, this.email, this.name, this.nickname, 
				this.password, this.salt, this.profileImg, this.loginFailCount, this.memberAuthority, this.loginFailTime);
	}

}
