package com.ktds.ysproject.board.vo;

import org.springframework.web.multipart.MultipartFile;

import com.ktds.ysproject.member.vo.MemberVO;

public class BoardVO {

	private String boardId;
	private String title;
	private String content;
	private String crtDt;
	private int viewCount;
	private String videoPath;
	private String posterPath;
	private int boardDivision;
	private String urlAddress;
	private int recommendCount;
	private String email;
	private MemberVO memberVO;

	private MultipartFile video;
	private MultipartFile poster;
	
	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public int getBoardDivision() {
		return boardDivision;
	}

	public void setBoardDivision(int boardDivision) {
		this.boardDivision = boardDivision;
	}

	public String getUrlAddress() {
		return urlAddress;
	}

	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public MultipartFile getVideo() {
		return video;
	}

	public void setVideo(MultipartFile video) {
		this.video = video;
	}

	public MultipartFile getPoster() {
		return poster;
	}

	public void setPoster(MultipartFile poster) {
		this.poster = poster;
	}

	
	
}
