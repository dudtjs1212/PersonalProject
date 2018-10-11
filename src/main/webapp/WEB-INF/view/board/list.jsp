<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

	<c:forEach items="${boardList}" var="board">
		<div>
			<a href="<c:url value='/board/detail/${board.boardId}'/>">
			<h3>${board.title} <br/></h3>
			</a>
			${board.content} <br/>
			${board.viewCount} <br/>
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
     <a href="/GameReview/main/home">메인 화면</a>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />