<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameReview</title>
<link rel="stylesheet" type="text/css" href="/GameReview/css/layout.css"/>
<script src="/GameReview/js/jquery-3.3.1.min.js" type="text/javascript"></script>
</head>
<body>
	
	<div id="wrapper">
		<header>
			<!-- <div style="width:100%; height:120px; position:absolute;">
				<a href="/GameReview/main/home">
					<img style="width:100%; height:30%" src="/GameReview/img/MainTopImg.gif"/>
				</a>
			</div> -->
			<div id="headerlogin">
				<c:choose>
					<c:when test="${not empty sessionScope._USER_}">
						${sessionScope._USER_.nickname} 님 환영합니다.
						<a id="logoutBtn" href="/GameReview/member/logout">로그아웃</a>
					</c:when>
					<c:otherwise>
						로그인이 필요합니다. 
					</c:otherwise>
				</c:choose>
			</div>
		</header>
		<section class="inline">
			<aside>
				Aside
			</aside>
		<section>