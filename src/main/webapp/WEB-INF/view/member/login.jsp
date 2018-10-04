<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
 <script type="text/javascript">
	$().ready(function(){
		$("#loginBtn").click(function(){
			var blockUser = $(`<div class="error"> Password 3회이상 실패하여 1시간 동안 계정이 잠겼습니다. 1시간 이후에 다시 이용 바랍니다.</div>`);
			var loginFail = $(`<div class="error"> ID 또는 Password가 맞지 않습니다.</div>`);
			$(".error").remove();
			$.post("/GameReview/member/login", 
					$(`#memberLoginForm`).serialize()
					, function(response) {
	            if (response.status) {
	            	$("#email").before(blockUser);
	            }
	            else {
	            	if (response.loginStatus){
		            	location.href="/GameReivew/main/home"
		            }
		            else {
		            	$("#email").before(loginFail);
		            }
	            }
	            alert(response.message);
	         });               
			var emailAfter = $(`<div class="error"> E-Mail를 입력해 주세요.</div>`);
			var passwordAfter = $(`<div class="error"> Password를 입력해 주세요.</div>`);
			
			var emailRegexAfter = $(`<div class="error"> E-Mail 형식을 확인해 주세요.</div>`);
			var emailRegex = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
			
			if ($("#email").val() == "" && $("#password").val() == "") {
				$(".error").remove();
				$("#email").after(emailAfter);
				$("#password").after(passwordAfter);
				$("#email").focus(); 
				return;
			}
			else if ($("#email").val() == "") {
				$(".error").remove();
				$("#email").after(emailAfter);
				$("#email").focus(); 
				return;
			}
			else if (!emailRegex.test($("#email").val()) && $("#password").val() == "") {
            	$(".error").remove();
				$("#email").after(emailRegexAfter);
				$("#password").after(passwordAfter);
               setTimeout( function() { 
                  $("#email").focus(); 
                  }, 10);
               return;
            }
            else if ( !emailRegex.test($("#email").val()) ) {
            	$(".error").remove();
				$("#email").after(emailRegexAfter);
               setTimeout( function() { 
                  $("#email").focus(); 
                  }, 10);
               return;
            }
			/* var emailRegex = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
            if ( !emailRegex.test($("#email").val()) ) {
               alert("이메일 형식을 확인해주세요");
               setTimeout( function() { 
                  $("#email").focus(); 
                  }, 10);
               return;
            } */

			else if ($("#password").val() == "") {
				$(".error").remove();
				$("#password").after(passwordAfter);
				$("#password").focus(); 
				return;
			}
            
			$("#memberLoginForm").attr({
				"method" : "post",
				"action" : "/GameReview/member/login"
			}).submit();
			/* $.post("/GameReview/member/login"
					, $("#memberLoginForm").serialize()
					, function(response) {
						alert("전송");				
			}); */
			/* $("#memberLoginForm").attr({
				"method" : "post",
				"action" : "/GameReview/member/login"
			}).submit(); */
		});
	});
	
</script>

<title>Member Login</title>
	<h1> 회원 로그인 </h1>
		<c:if test= "${loginMember.error eq '1' }">
			<article>
				<div class="error"> 
					ID 또는 Password가 맞지 않습니다.
				</div>
			</article>
		</c:if>
	<form:form id = "memberLoginForm" modelAttribute="memberVO">
		<div>
			<input type="email" id="email" name="email" placeholder="Email" value="${memberVO.email}"/>
		</div>
		<div>
			<form:errors path="email"/>
		</div>
		<div>
			<input type="password" id="password" name="password" placeholder="Password" value="${memberVO.password}" />
		</div>
		<div>
			<form:errors path="password"/>
		</div>
		<div>
			<input type="button" id="loginBtn" value="로그인" />
		</div>
	</form:form>
	<a href="/GameReview/member/regist">회원가입</a>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />