<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
	${sessionScope._USER_.nickname} 님 환영합니다.
	<a href="../board/write"> 글쓰기</a>
	<a href="../board/list"> 자유게시판</a>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />