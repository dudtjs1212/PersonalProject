<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Regist</title>
</head>
<body>
	<h1>Member Regist</h1>
	<form:form id="memberRegistForm" method="post" action="regist" enctype="multipart/form-data" modelAttribute="memberVO">
		<div>
			<input type="email" id="email" name="email" placeholder="E-Mail"/> <!-- 동영상 파일만 선택되도록 설정 -->
		</div>
		<div>
			<form:errors path="email"/>
		</div>
		<div>
			<input type="text" id="name" name="name" placeholder="NAME"/>
		</div>
		<div>
			<form:errors path="name"/>
		</div>
		<div>
			<input type="text" id="nickname" name="nickname" placeholder="NICKNAME"/>
		</div>
		<div>
			<form:errors path="nickname"/>
		</div>
		<div>
			<input type="password" id="password" name="password" placeholder="PASSWORD"/>
		</div>
		<div>
			<form:errors path="password"/>
		</div>
		<div>
			<input type="file" id="file" name="file" placeholder="PICTURE" accept=".gif, .jpg, .jpeg, .png"/>
		</div>
		<div>
			<input type="submit" id="regist" value="Regist"/>
		</div>
	</form:form>
	<a href="/GameReview/member/login">로그인</a>
</body>
</html>