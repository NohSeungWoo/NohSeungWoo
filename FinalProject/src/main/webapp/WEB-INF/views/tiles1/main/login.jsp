<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	$(document).ready(function() {
		$(".error").hide();
	});

</script>

<div class="container-fluid container-lg" style="margin: 50px auto; width: 80%;">
	<div class="row">
		<div class="col-2 col-lg-3"></div>
		<div class="col-8 col-lg-6">
			<span class="h2" style="font-weight: bold;">로그인</span>
		</div>
	</div>
	<br>
	<form>
	    <div class="form-row">
	    	<div class="col-2 col-lg-3"></div>
		    <div class="col-8 col-lg-6">
		       <label>아이디</label>
		       <input type="text" class="form-control mb-1" id="email" placeholder="Enter userid" name="userid">
		       <span class="error" style="color: red;">아이디를 입력하세요</span>
		    </div>
		    <div class="w-100 my-3"></div>
		    <div class="col-2 col-lg-3"></div>
	        <div class="col-8 col-lg-6">
	      	   <label>비밀번호</label>
	           <input type="password" class="form-control mb-1" placeholder="Enter password" name="password">
	           <span class="error" style="color: red;">비밀번호를 입력하세요</span>
	        </div>
	        <div class="w-100 my-3"></div>
	        <div class="col-2 col-lg-3"></div>
	    </div>
	    <div class="row justify-content-center">
		    <div class="col-8 col-lg-6">
		       <button class="btn btn-primary btn-lg" style="display:flex; margin: auto; width: 295px; justify-content: center;">로그인</button>
		    </div>
	    </div>
	    
    </form>
</div>
