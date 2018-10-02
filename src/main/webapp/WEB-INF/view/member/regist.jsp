<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" /> 
<script type="text/javascript">
	$().ready(function(){
		$("#registBtn").click(function(){
			var emailAfter = $(`<div class="error"> ID를 입력해 주세요.</div>`);
			var nameAfter = $(`<div class="error"> 이름을 입력해 주세요.</div>`);
			var passwordAfter = $(`<div class="error"> Password를 입력해 주세요.</div>`);
			var nicknameAfter = $(`<div class="error"> 닉네임을 입력해 주세요.</div>`);
			
			if ($("#email").val() == "") {
				$(".error").remove();
				$("#email").after(emailAfter);
				$("#email").focus(); 
				return;
			}
			else if ($("#name").val() == "") {
				$(".error").remove();
				$("#name").after(nameAfter);
				$("#name").focus(); 
				return;
			}
			else if ($("#nickname").val() == "") {
				$(".error").remove();
				$("#nickname").after(nicknameAfter);
				$("#nickname").focus(); 
				return;
			}
			else if ($("#password").val() == "") {
				$(".error").remove();
				$("#password").after(passwordAfter);
				$("#password").focus(); 
				return;
			}
			/* var emailRegex = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
            if ( !emailRegex.test($("#email").val()) ) {
               alert("이메일 형식을 확인해주세요");
               setTimeout( function() { 
                  $("#email").focus(); 
                  }, 10);
               return;
            }  */
			
			$("#memberRegistForm").attr({
				"method" : "post",
				"action" : "/GameReview/member/regist",
				"enctype": "multipart/form-data"
			}).submit();
			
		});
	});
</script> 
<title>Member Regist</title>
	<h1>Member Regist</h1>
		<c:if test= "${param.error eq '1' }">
			<article>
				<div class="error"> 
					Eamil 또는 Password를 형식에 맞게 작성하였는지 확인하십시오.
				</div>
			</article>
		</c:if>
	<form:form id="memberRegistForm" modelAttribute="memberVO">
		<div>
			<input type="email" id="email" name="email" placeholder="E-Mail" /> <!-- 동영상 파일만 선택되도록 설정 -->
		</div>
		<div>
			<form:errors path="email"/>
		</div>
		<div>
			<input type="text" id="name" name="name" placeholder="NAME" />
		</div>
		<div>
			<form:errors path="name"/>
		</div>
		<div>
			<input type="text" id="nickname" name="nickname" placeholder="닉네임" />
		</div>
		<div>
			<form:errors path="nickname"/>
		</div>
		<div>
			<input type="password" id="password" name="password" placeholder="PASSWORD" />
		</div>
		<div>
			<form:errors path="password"/>
		</div>
		<div>
			<input type="file" id="file" name="file" placeholder="PICTURE" accept=".gif, .jpg, .jpeg, .png"/>
		</div>
		<div>
			<input type="button" id="registBtn" value="등록"/>
		</div>
	</form:form>
	<a href="/GameReview/member/login">로그인</a>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />