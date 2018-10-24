<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
 	<div id="alldiv">
	 	<h1> BOARD MODIFY</h1>
		<form:form id="boardModifyForm" modelAttribute="boardVO" method="post" action="/GameReview/board/modify" enctype="multipart/form-data" >
			<input type="hidden" name="token" value="${sessionScope._TOKEN_}" />
			<div>
				<input type="hidden" id="boardId" name="boardId" value="${board.boardId}"/>
			</div>
			<div>
				<input type="hidden" id="videoPathaa" name="videoPath" value="${board.videoPath}"/>
			</div>
			<div>
				<input type="hidden" id="posterPathaa" name="posterPath" value="${board.posterPath}"/>
			</div>
			<div>
				<input type="text" id="title" name="title" value="${board.title}"/>
			</div>
			<div>
				<input type="text" id="content" name ="content" value="${board.content}" />
			</div>
			<div>
				<h2> 동영상 및 포스터 이미지 변경을 원하시면 파일을 다시 등록해 주시기 바랍니다. </h2>
			</div>
			<div>
				<input type="file" id="videoPath" name="video" accept=".mp4, .avi" value="${board.video}"/>
			</div>
			<div>
				<input type="file" id="posterPath" name="poster" accept=".jpg, .jpeg, .png, .gif" value="${board.poster}"/>
			</div>
			<div>
				<ul>
					<form:errors path="title"/> 
					<form:errors path="content"/>
				</ul>
			</div>
			<div>
				<input type="submit" id="modifyBtn" value="수정" />
			</div>
			<div class="href">
				<a style="margin-right:20px;" href="/GameReview/board/list/${board.boardDivision}">목록</a>
				<a href="/GameReview/main/home">메인 화면</a> 
			</div>
		</form:form>
	</div>
 
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />