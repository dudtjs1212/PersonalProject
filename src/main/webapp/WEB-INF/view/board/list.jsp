<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
	<div id="alldiv">
		<h1> 자유 게시판 </h1>
		<c:forEach items="${boardList}" var="board">
			<div class="listpage">
				<a style="display: inline-block;" href="<c:url value='/board/detail/${board.boardId}'/>">
				<div class="poster" style="display: inline-block;">
					<img style="width: 150px; height: 130px;" src="<c:url value='/board/poster/download/${board.posterPath}'/>"/>
				</div></a>
				<div class="titlediv" style="display: inline-block; width:850px; height: 130px;">
					<a style="display: inline-block; margin-top:5px;" href="<c:url value='/board/detail/${board.boardId}'/>">
					<h2 style="display: inline-block; padding-left: 10px; margin-top:5px; ">${board.title} </h2></a>
					<br/>
					<h4 style="padding-left: 10px; padding-top: 3px; margin-top:8px; display: inline-block;">${board.memberVO.nickname}</h4>
					<br/>
					<span style="padding-left: 10px;">${board.crtDt} </span> 
					<span style="padding-left: 10px;"> 조회수 : ${board.viewCount} </span>
				</div>
				
				
			</div>
		</c:forEach>
		
		<div class="padded">
	     	<form id="searchForm" onsubmit="javascript:movePage(0);">
	     		${pagenation}
	     		<div>
	     			<input type="text" name="searchKeyword" value="${boardSearchVO.searchKeyword}">
	     			<a href="/GameReview/board/list/init">검색 초기화</a>
	     		</div>
	     	</form>
	     </div>
	     <div class="href">
	     	<a href="/GameReview/main/home">메인 화면</a>
	     </div>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />