<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
<script type="text/javascript">
	$().ready(function(){
		/* $("#writeBtn").click(function(){
			$("#boardCreateForm").attr({
				"method" : "post",
				"action" : "/GameReview/board/write"
				"enctype" : "multipart/form-data"
			}).submit();
		}); */
	})
</script>
	<div id="alldiv">
		<h1>Board Write</h1>
		<div style="width:1024px;">
			<form:form id="boardCreateForm" modelAttribute="boardVO" method="post" action="/GameReview/board/write" enctype="multipart/form-data" >
				<input type="hidden" name="token" value="${sessionScope._TOKEN_}" />
				<input type="hidden" id="boardDivision" name="boardDivision" value="${boardDivision}"/>
				<div>
					<input type="text" id="title" name="title" placeholder="제목을 입력해 주세요."/>
				</div>
				<div>
					<input type="text" id="content" name ="content" placeholder="내용을 입력해 주세요." />
				</div>
				<div>
					동영상 파일 : <input type="file" id="videoPath" name="video" accept=".mp4, .avi"/>
				</div>
				<div>
					이미지 파일 : <input type="file" id="posterPath" name="poster" accept=".jpg, .jpeg, .png, .gif"/>
				</div>
				<div>
					<ul>
						 <form:errors path="title"/>
						 <form:errors path="content"/>
					</ul>
				</div>
				<div>
					<input type="submit" id="writeBtn" value="글작성" />
				</div>
			</form:form>
		</div>
		<div class="href">
			<a style="margin-right:20px;" href="/GameReview/board/list/${board.boardDivision}">목록</a>
			<a href="/GameReview/main/home">메인 화면</a>
		</div>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />