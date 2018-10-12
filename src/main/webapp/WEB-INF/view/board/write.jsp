<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
<script type="text/javascript">
	$().ready(function(){
	})
</script>
	<div id="alldiv">
		<h1>Board Write</h1>
		<form:form id="boardCreateForm" modelAttribute="boardVO" method="post" action="/GameReview/board/write" enctype="multipart/form-data" >
			<div>
				<input type="text" id="title" name="title" placeholder="제목을 입력해 주세요."/>
			</div>
			<div>
				<input type="text" id="content" name ="content" placeholder="내용을 입력해 주세요." />
			</div>
			<div>
				<input type="file" id="videoPath" name="video" accept=".mp4, .avi"/>
			</div>
			<div>
				<input type="file" id="posterPath" name="poster" accept=".jpg, .jpeg, .png, .gif"/>
			</div>
			<div>
				<ul>
					<li><form:errors path="title"/></li>
					<li><form:errors path="content"/></li>
				</ul>
			</div>
			<div>
				<input type="submit" id="writeBtn" value="글작성" />
			</div>
		</form:form>
		<div class="href">
			<a href="/GameReview/main/home">메인 화면</a>
		</div>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />