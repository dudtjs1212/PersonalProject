<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
	<div id="alldiv">
	<%-- ${sessionScope._USER_.nickname} 님 환영합니다. --%>
		<div class="href" style="margin-top:100px;">
			<!-- <a href="../board/list/0"> 공지사항</a>
			<a href="../board/list/1"> Mobile Review</a>
			<a href="../board/list/2"> PlayStation Review</a>
			<a href="../board/list/3"> PC Game Review</a>
			<a href="../board/list/4"> 자유 게시판</a> -->
		</div>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />