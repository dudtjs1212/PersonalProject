<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${boardList}" var="board">
		<div>
			<h3>${board.title} <br/></h3>
			${board.content} <br/>
			${board.viewCount} <br/>
		</div>
	</c:forEach>
	
	<div class="padded">
     	<form id="searchForm" onsubmit="javascript:movePage(0);">
     		${pagenation}
     		<div>
     			<input type="text" name="searchKeyword" value="${boardSearchVO.searchKeyword}">
     			<a href="/HelloSpring/board/list/init">검색 초기화</a>
     		</div>
     	</form>
     </div>
	
</body>
</html>