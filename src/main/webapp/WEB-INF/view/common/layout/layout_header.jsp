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
			  <div style="width:400px; height:124px; position:absolute;">
				<a href="/GameReview/main/home">
					<img style="width:400px; height:124px" src="/GameReview/img/GameReviews2.jpg"/>
				</a>
			  </div> 
			
			<div id="menu">
				<ul>
					<li><a href="/GameReview/board/list/0"> 공지사항</a></li>
					<li><a href="/GameReview/board/list/1"> Mobile Review</a></li>
					<li><a href="/GameReview/board/list/2"> PlayStation Review</a></li>
					<li><a href="/GameReview/board/list/3"> PC Game Review</a></li>
					<li><a href="/GameReview/board/list/4"> 자유 게시판</a></li>
				</ul>	
			</div>
			<div id="headerlogin">
				<c:choose>
					<c:when test="${not empty sessionScope._USER_}">
						${sessionScope._USER_.nickname} 님 환영합니다.
						<a id="logoutBtn" href="/GameReview/member/logout2">로그아웃</a>
					</c:when>
					<c:otherwise>
						로그인이 필요합니다. 
					</c:otherwise>
				</c:choose>
			</div>
		</header>
		<section class="inline">
			<aside>
				<div style="width:200px; height:120px;"></div>
				<a href="http://pubg.game.daum.net/pubg/event/bp_attendance/index.daum">
					<img style="width:300px; height:140px;" src="/GameReview/img/potg.jpg"/>
				</a>
				<div style="width:200px; height:50px;"></div>
				<a href="https://www.ktds.com/main.jsp">
					<img style="width:250px; height:300px;" src="/GameReview/img/KTDS.jpg"/>
				</a>
				<div style="width:200px; height:50px;"></div>
				<a href="https://kr.leagueoflegends.com">
					<img style="width:300px; height:165px;" src="/GameReview/img/lol.jpg"/>
				</a>
			</aside>
		<section>