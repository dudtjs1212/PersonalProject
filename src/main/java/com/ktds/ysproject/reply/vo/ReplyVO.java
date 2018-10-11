package com.ktds.ysproject.reply.vo;

import com.ktds.ysproject.board.vo.BoardVO;
import com.ktds.ysproject.member.vo.MemberVO;

public class ReplyVO {

	private int level;
	private String replyId;
	private String content;
	private String parentReplyId;
	private String email;
	private String boardId;
	private String crtDt;

	private MemberVO memberVO;
	private BoardVO boardVO;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParentReplyId() {
		return parentReplyId;
	}

	public void setParentReplyId(String parentReplyId) {
		this.parentReplyId = parentReplyId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public BoardVO getBoardVO() {
		return boardVO;
	}

	public void setBoardVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}

	public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	@Override
	public String toString() {
		return "ReplyVO [level=" + level + ", replyId=" + replyId + ", content=" + content + ", parentReplyId="
				+ parentReplyId + ", email=" + email + ", boardId=" + boardId + ", crtDt=" + crtDt + ", memberVO="
				+ memberVO + ", boardVO=" + boardVO + "]";
	}
	
	

}
