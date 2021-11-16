<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	$(document).ready(function() {
		$(".error").hide();
		
		// 로그인 버튼 클릭 이벤트
		$("button#loginBtn").click(function() {
			
			goLogin();
			
		});// end of $("button#loginBtn").click(function() {}
		
	});// end of $(document).ready(function() {}

	
	// === Function Declaration === //
	function goLogin() {
		
		// 로그인 유효성 검사
		var email = $("input#email").val();
		var password = $("input#password").val();
		
		if(email.trim() == "") {
			alert("이메일을 입력하세요!!");
			$("input#email").val("");
			$("input#email").focus();
			return;	// 종료
		}
		
		if(password.trim() == "") {
			alert("비밀번호를 입력하세요!!");
			$("input#password").val("");
			$("input#password").focus();
			return;	// 종료
		}
		
		var frm = document.loginFrm;
		
		frm.action = "<%= request.getContextPath()%>/loginEnd.gw";
		frm.method = "POST";
		frm.submit();
		
	}
	
</script>

<div class="container-fluid container-lg" style="margin: 50px auto; width: 80%;">
	<div class="row">
		<div class="col-2 col-lg-3"></div>
		<div class="col-8 col-lg-6">
			<span class="h2" style="font-weight: bold;">로그인</span>
		</div>
	</div>
	<br>
	<form name="loginFrm">
	    <div class="form-row">
	    	<div class="col-2 col-lg-3"></div>
		    <div class="col-8 col-lg-6">
		       <label>이메일</label>
		       <input type="text" class="form-control mb-1" id="email" placeholder="Enter email" name="email">
		       <span class="error" style="color: red;">이메일을 입력하세요</span>
		    </div>
		    <div class="w-100 my-3"></div>
		    <div class="col-2 col-lg-3"></div>
	        <div class="col-8 col-lg-6">
	      	   <label>비밀번호</label>
	           <input type="password" class="form-control mb-1" id="password" placeholder="Enter password" name="password">
	           <span class="error" style="color: red;">비밀번호를 입력하세요</span>
	        </div>
	        <div class="w-100 my-3"></div>
	        <div class="col-2 col-lg-3"></div>
	    </div>
	    <div class="row justify-content-center">
		    <div class="col-8 col-lg-6">
		       <button id="loginBtn" class="btn btn-primary btn-lg" style="display:flex; margin: auto; width: 295px; justify-content: center;">로그인</button>
		    </div>
	    </div>
    </form>
</div>
