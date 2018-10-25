package com.ktds.ysproject.board.vo;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.ktds.ysproject.member.vo.MemberVO;
import com.ktds.ysproject.reply.vo.ReplyVO;

public class BoardVO {
 
	private String boardId;
	@NotEmpty(message="제목은 필수 입력 값입니다.")
	private String title;
	@NotEmpty(message="내용은 필수 입력 값입니다.")
	private String content;
	private String crtDt;
	private int viewCount;
	private String videoPath;
	private String posterPath;
	private int boardDivision;
	private String urlAddress;
	private int recommendCount;
	private String email;
	private String token;
	private String delWhether;

	public String getDelWhether() {
		return delWhether;
	}

	public void setDelWhether(String delWhether) {
		this.delWhether = delWhether;
	}

	private MemberVO memberVO;
	
	private List<ReplyVO> replyList;

	private MultipartFile video;
	private MultipartFile poster;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
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

	public List<ReplyVO> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<ReplyVO> replyList) {
		this.replyList = replyList;
	}

	@Override
	public String toString() {
		return "BoardVO [boardId=" + boardId + ", title=" + title + ", content=" + content + ", crtDt=" + crtDt
				+ ", viewCount=" + viewCount + ", videoPath=" + videoPath + ", posterPath=" + posterPath
				+ ", boardDivision=" + boardDivision + ", urlAddress=" + urlAddress + ", recommendCount="
				+ recommendCount + ", email=" + email + ", token=" + token + ", delWhether=" + delWhether
				+ ", memberVO=" + memberVO + ", replyList=" + replyList + ", video=" + video + ", poster=" + poster
				+ "]";
	}

	
	
}
