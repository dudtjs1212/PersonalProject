<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Member Regist</title>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" /> 
<script type="text/javascript">
	$().ready(function(){
		var emailAfter = $(`<div class="error"> E-Mail을 입력해 주세요.</div>`);
		var nameAfter = $(`<div class="error"> 이름을 입력해 주세요.</div>`);
		var passwordAfter = $(`<div class="error"> Password를 입력해 주세요.</div>`);
		var nicknameAfter = $(`<div class="error"> 닉네임을 입력해 주세요.</div>`);
		var emailRegex = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		var emailRegexAfter = $(`<div class="error"> E-Mail 형식이 맞지 않습니다.</div>`);
		var passwordRegex = /^(?=.*[a-zA-z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}/;
		var passwordRegexAter = $(`<div class="error"> 비밀번호는 8글자 이상 20글자 이하 <br/>  대소문자, 숫자, 특수문자를 포함해야 합니다.</div>`);
		var emailRegexboolAfter = $(`<div class="error"> E-Mail 이미 존재합니다.</div>`);
		
		$("#email").after(emailAfter);
		$("#name").after(nameAfter);
		$("#nickname").after(nicknameAfter);
		$("#password").after(passwordAfter); 
		
		var emailEmpty = false;
		var nameEmpty = false;
		var nicknameEmpty = false;
		var passwordEmpty = false;
		var emailRegexbool = false;
		
		function EmptyCheck(){
			emailEmpty = false;
			nameEmpty = false;
			nicknameEmpty = false;
			passwordEmpty = false;
			
			if ($("#email").val() == "") {
				emailEmpty = true;
			}
			if ($("#name").val() == "") {
				nameEmpty = true;
			}
			if ($("#nickname").val() == "") {
				nicknameEmpty = true;
			}
			if ($("#password").val() == "") {
				passwordEmpty = true;
			}
		}
		
		function errorDivAdd() {
			EmptyCheck();
			duplicate();
			if (emailEmpty || nameEmpty || nicknameEmpty || passwordEmpty) {
				$(".error").remove();
				if (emailEmpty) {
					$("#email").after(emailAfter);
				}
				else {
					if ( !emailRegex.test($("#email").val()) ) {
						$("#email").after(emailRegexAfter);
		            }
					else { 
						if ( emailRegexbool ) {
							$("#email").after(emailRegexboolAfter);
						}
					}
				}
				if (nameEmpty) {
					$("#name").after(nameAfter);
				}
				if (nicknameEmpty) {
					$("#nickname").after(nicknameAfter);
				}
				if (passwordEmpty) {
					$("#password").after(passwordAfter);
				}
				else {
					if ( !passwordRegex.test($("#password").val()) ) {
						$("#password").after(passwordRegexAter);
			        }
				}
			}
			else {
				$(".error").remove();
				if ( !passwordRegex.test($("#password").val()) ) {
					$("#password").after(passwordRegexAter);
		        }
				if ( !emailRegex.test($("#email").val()) ) {
					$("#email").after(emailRegexAfter);
	            }
				else {
					if ( emailRegexbool ) {
						$("#email").after(emailRegexboolAfter);
					}
				}
			}
				
		}
		
		function duplicate() {
			$.post("/GameReview/member/duplicate", {
	            "email" : $("#email").val()
	         }, function(response) {
	            if (response.duplicated) {
	            	emailRegexbool  = true;
	            }
	            else {
	            	emailRegexbool  = false;
	            }
	         });       
		}
		
		$("#registBtn").click(function(){
			errorDivAdd();
			if ( emailRegexbool ) {
				return;
			}
			$("#memberRegistForm").attr({
				"method" : "post",
				"action" : "/GameReview/member/regist",
				"enctype": "multipart/form-data"
			}).submit();
		});
		$("#email").blur( function() {
			errorDivAdd();
			return;
	    });
		$("#name").blur(function(){
			errorDivAdd();
			return;
		});
		$("#nickname").blur(function(){
			errorDivAdd();
			return;
		});
		$("#password").blur(function(){
			errorDivAdd();
			return;
		});
	});
</script> 
<title>Member Regist</title>
	<div id="alldiv">
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
		<div class="href">
			<a href="/GameReview/member/login">로그인</a>
		</div>
	</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />