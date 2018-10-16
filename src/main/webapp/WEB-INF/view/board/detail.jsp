<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>GameReview</title>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
<script type="text/javascript">
	$().ready(function(){
		$(".replyDiv").on("click", ".replyDelBtn", function(){
			var replyId = $(this).closest(".replyDiv").find(".replyId").val();
			
			location.href="/GameReview/reply/delete/" + replyId;
			/* $(this).attr({
				action: "/GameReview/reply/delete/" + replyId,
              	method: "post"
			}).submit(); */
		})
		
		$(".replyDiv").on("click", ".rereplyBtn", function(){
			$(this).closest(".replyDiv").find(".replyData").attr( {
	           		action: "/GameReview/reply/write",
	              	method: "post",
	              	enctype: "multipart/form-data"
	         } ).submit();
		})
		
		$(".parentReplyBtn").click(function(){
			var parentId = $(this).closest(".replyDiv").find(".parentReplyId").val();
		    var cmForm = $('<form:form class="replyData" modelAttribute="replyVO"><div class="rereplyDiv"><textarea name="content"></textarea><input type="hidden" name="boardId" value="${board.boardId}" /> <input type="hidden" id="replyId" class="replyId" name="replyId" value="${reply.replyId}" /><input type="hidden" id="parentReplyId" class="parentReplyId" name="parentReplyId" value="' + parentId + '" /><input style= "display:inline-block; margin-left: ${(reply.level-1) * 30}px;" type="button" class="rereplyBtn" value="답글" /><input type="button" class ="replyDelBtn" value="삭제"/></div></form:form>');
			
		    if ( $(this).val()=="답글") {
		    	$(".rereplyDiv").remove();//열려있는 댓댓글 창을 리셋
		    	$(".parentReplyBtn").val("답글");
		    	$(this).val("답글 취소");
		    	$(this).closest(".replyDiv").find(".cmBtn").append(cmForm);
		    }
		    else {
		    	$(".rereplyDiv").remove();//열려있는 댓댓글 창을 리셋
		    	$(".parentReplyBtn").val("답글");
		    } 
		    
		    
		    
		  /*   $("#parentReplyForm").attr({
				"method" : "post",
				"action" : "/GameReview/reply/write"
			}).submit();
		     */
		})
	})

</script>
	<div id="alldiv">
		<div class="detailPage">
			<c:if test="${not empty board.videoPath}">
				<div class="video" style="display: inline-block;">
					<video style="width: 100%; height: 70%;"	preload="metadata"
							autoplay controls="controls" 
							poster="<c:url value='/board/poster/download/${board.posterPath}' />">
						<source src="<c:url value='/board/video/download/${board.videoPath}' />" />
					</video>
				</div>
			</c:if>
			<div class="detailPage">
				<span> <h1>${board.title}</h1> </span> 
			</div>
			<div class="detailPage" >
			<span style="margin-right:15px; margin-left:15px;"> ${board.content} </span> 
			</div>
			<div class="detailPage" style="margin-top:30px; padding-bottom:30px; text-align:right; padding-top:30px;  margin-right:15px;">
			${board.memberVO.nickname} 
			<span style="padding-left: 10px; margin-right:15px;">조회수 : ${board.viewCount} </span>
			</div>
	  	</div>
	 	<div style="padding-top:20px;">
			<c:forEach items="${board.replyList}" var="reply">
				<div class="replyDiv">
					<div style="margin-left: ${(reply.level-1) * 30}px;">
						<input type="hidden" id="replyId" class="replyId" name="replyId" value="${reply.replyId}" />
						<input type="hidden" id="parentReplyId" class="parentReplyId" name="parentReplyId" value="${reply.replyId}"  />
						<input type="hidden" name="boardId" value="${board.boardId}" />
						<div>${reply.memberVO.nickname} </div>
						<div>${reply.crtDt}</div>		
						<div>${reply.content}</div>		
					</div>
					<div class="cmBtn">
						<input style= "display:inline-block; margin-left: ${(reply.level-1) * 30}px;" type="button" class="parentReplyBtn" value="답글" />
						<input type="button" class ="replyDelBtn" value="삭제"/>
					</div>
				</div>
			</c:forEach>
		</div>
		
		
		<hr />
		<form id="parentReplyForm" action="/GameReview/reply/write" method="POST">
			<input type="hidden" name="boardId" value="${board.boardId}" />
			<input type="hidden" name="parentReplyId" value="0" />
			<textarea name="content"></textarea>
			<input style= "display:inline-block;" type="submit" value="등록"/>
		</form>
		<div class="href">
			<a href="<c:url value='/board/modify/${board.boardId}'/>">수정</a>
			<a href="/GameReview/board/list/${board.boardDivision}">목록</a>
		</div>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />