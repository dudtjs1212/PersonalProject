<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
 <h1> BOARD MODIFY</h1>
	<form:form id="boardModifyForm" modelAttribute="boardVO" method="post" action="/GameReview/board/modify" enctype="multipart/form-data" >
		<div>
			<input type="hidden" id="boardId" name="boardId" value="${board.boardId}"/>
		</div>
		<div>
			<input type="text" id="title" name="title" value="${board.title}"/>
		</div>
		<div>
			<input type="text" id="content" name ="content" value="${board.content}" />
		</div>
		<div>
			<input type="file" id="videoPath" name="video" accept=".mp4, .avi"/>
		</div>
		<div>
			<input type="file" id="posterPath" name="poster" accept=".jpg, .jpeg, .png, .gif"/>
		</div>
		<div>
			<input type="submit" id="modifyBtn" value="수정" />
		</div>
	</form:form>
 
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />