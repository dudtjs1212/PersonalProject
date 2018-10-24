<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
	<div id="alldiv">
	<%-- ${sessionScope._USER_.nickname} 님 환영합니다. --%>
		<div id="zeroBoard">
			<div class="mainHref">
				<a href="/GameReview/board/list/0"> 공지사항</a>
			</div>
			<c:forEach items="${boardZero}" var="BoardZero" varStatus="i">
				<div class="mainBoardList">
					<a href="/GameReview/board/detail/${BoardZero.boardId}">
						<div class="spanId"> ${i.count} </div>
						<div class="spantitle"> ${BoardZero.title} </div> 
						<div class="spandt"> ${BoardZero.crtDt} </div>
					</a>
				</div>
			</c:forEach>
		</div>
		<div id="oneBoard">
			<div class="mainHref">
				<a href="/GameReview/board/list/1"> Mobile Review</a>
			</div>
			<c:forEach items="${boardOne}" var="boardOne" varStatus="i">
				<div class="mainBoardList">
					<a href="/GameReview/board/detail/${boardOne.boardId}">
						<div class="spanId"> ${i.count} </div>
						<div class="spantitle"> ${boardOne.title} </div> 
						<div class="spandt"> ${boardOne.crtDt} </div>
					</a>
				</div>
			</c:forEach>
		</div>
		<hr/>
		<div id="twoBoard">
			<div class="mainHref">
				<a href="/GameReview/board/list/2"> PlayStation Review</a>
			</div>
			<c:forEach items="${boardTwo}" var="boardTwo" varStatus="i">
				<div class="mainBoardList">
					<a href="/GameReview/board/detail/${boardTwo.boardId}">
						<div class="spanId"> ${i.count} </div>
						<div class="spantitle"> ${boardTwo.title} </div> 
						<div class="spandt"> ${boardTwo.crtDt} </div>
					</a>
				</div>
			</c:forEach>
		</div>
		<div id="threeBoard">
			<div class="mainHref">
				<a href="/GameReview/board/list/3"> PC Game Review</a>
			</div>
			<c:forEach items="${boardThree}" var="boardThree" varStatus="i">
				<div class="mainBoardList">
					<a href="/GameReview/board/detail/${boardThree.boardId}">
						<div class="spanId"> ${i.count} </div>
						<div class="spantitle"> ${boardThree.title} </div> 
						<div class="spandt"> ${boardThree.crtDt} </div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />