<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${sessionScope._USER_.nickname} 님 환영합니다.
	<a href="../board/write"> 글쓰기</a>
	<a href="../board/list"> 자유게시판</a>
</body>
</html>