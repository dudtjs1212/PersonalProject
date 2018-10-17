<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Member Login</title>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
 <script type="text/javascript">
	$().ready(function(){
		var emailAfter = $(`<div class="error"> E-Mail를 입력해 주세요.</div>`);
		var passwordAfter = $(`<div class="error"> Password를 입력해 주세요.</div>`);
		
		var emailRegexAfter = $(`<div class="error"> E-Mail 형식을 확인해 주세요.</div>`);
		var emailRegex = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		
		var emailEmpty = false;
		var passwordEmpty = false;
		
		
		var error = '${error}';
		if (error == '1') {
			$("#email").before(blockUser);
		}
		
		function EmptyCheck(){
			emailEmpty = false;
			passwordEmpty = false;
			
			if ($("#email").val() == "") {
				emailEmpty = true;
			}
			if ($("#password").val() == "") {
				passwordEmpty = true;
			}
		}
		
		function errorDivAdd() {
			EmptyCheck();
			var result;
			if (emailEmpty || passwordEmpty) {
				$(".error").remove();
				if (emailEmpty) {
					$("#email").after(emailAfter);
				}
				else {
					if ( !emailRegex.test($("#email").val()) ) {
						$("#email").after(emailRegexAfter);
		            }
				}
				if (passwordEmpty) {
					$("#password").after(passwordAfter);
				}
				result = true;
			}
			else {
				$(".error").remove();
				if ( !emailRegex.test($("#email").val()) ) {
					$("#email").after(emailRegexAfter);
					result = true;
	            }
				else {
					result = false;
				}
			}
			return result;
				
		}
		
		
		$("#loginBtn").click(function(){
			if (errorDivAdd()) {
				return;
			}
			
			var blockUser = $(`<div class="error"> Password 3회이상 실패하여 1시간 동안 계정이 잠겼습니다. <br/> 1시간 이후에 다시 이용 바랍니다.</div>`);
			var loginFail = $(`<div class="error"> ID 또는 Password가 맞지 않습니다.</div>`);        
			$.post("/GameReview/memberlogin", 
					$(`#memberLoginForm`).serialize()
					, function(response) {
	            if (response == "block") {
	            	$("#email").before(blockUser);
	            }
	            else if (response.loginStatus){
            		alert(response.message);
            		location.href="/GameReview/main/home"
	            } else {
		            $("#email").before(loginFail);
	            } 
	            //alert(response.message);
	         });     
			
			
			/* $("#memberLoginForm").attr({
				"method" : "post",
				"action" : "/GameReview/member/login"
			}).submit(); */
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
	<div id="alldiv">
		<h2 class="boardTitle"> 회원 로그인 </h2>
			<c:if test="${error eq '1'}"> 
				<div class="error"> Password 3회이상 실패하여 1시간 동안 계정이 잠겼습니다. <br/> 1시간 이후에 다시 이용 바랍니다.</div>
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
		<div class="href">
			<a href="/GameReview/member/regist">회원가입</a>
		</div>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />