<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 

<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 

<!-- Font Awesome 5 Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- 직접 만든 CSS 1 -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/style1.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script> 
<script type="text/javascript" src="<%= ctxPath%>/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script> 
  

<style type="text/css">
	
	div#empList {
		margin: 50px auto;
		width: 90%;
	}
	
	div.input-group {
	    width:100%;
	    text-align:center;
	    float: left;
  		display: inline-block;
	}
	
	div.search-bar {
		float: left;
  		position: relative;
  		display: inline-block;
	}
	
	div#searchButton {
		float: left;
  		position: relative;
  		display: inline-block;
	}
	
	div#empButton {
		float: right;
  		position: relative;
  		display: inline-block;
	}
	
</style>
</head>
<body>
	<div class="container-fluid" id="empList">
	  <div class="row mb-2 ml-2">
	  	<span class="h3 font-weight-bold">직원 목록</span>
	  </div>
	  <div class="row mt-1 input-group mb-3">
	  	<div class="col-11 col-lg-4 search-bar mt-3 ml-2">
	      	<input id="searchEmp" type="text" class="form-control rounded-pill float-left" placeholder="직원 검색">
	  	</div>
	  	<div id="searchButton" class="mt-3">
		  	<a href="#"><i class="fas fa-search fa-lg mt-2 pt-1"></i></a>
	  	</div>
	  	<div id="empButton" class="col-11 col-lg-4 mt-3" >
		  	<button class="btn btn-outline-secondary">직원등록</button>
		  	<button class="btn btn-outline-secondary">관리자등록</button>
	  	</div>
	  </div>
	  <table class="table col-12" >
	    <thead class="thead-light">
	      <tr>
	        <th>이름</th>
	        <th>
				<span class="dropdown-toggle" data-toggle="dropdown" style="cursor: pointer;">
				      부서
				</span>
				<div class="dropdown-menu">
				  <a class="dropdown-item" href="#">Link 1</a>
				  <a class="dropdown-item" href="#">Link 2</a>
				  <a class="dropdown-item" href="#">Link 3</a>
				</div>
			</th>
	        <th>
	        	<span class="dropdown-toggle" data-toggle="dropdown" style="cursor: pointer;">
				      직급
				</span>
				<div class="dropdown-menu">
				  <a class="dropdown-item" href="#">Link 1</a>
				  <a class="dropdown-item" href="#">Link 2</a>
				  <a class="dropdown-item" href="#">Link 3</a>
				</div>
			</th>
	        <th>이메일</th>
	        <th>연락처</th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td>John</td>
	        <td>인사과</td>
	        <td>부장</td>
	        <td>john@example.com</td>
	        <td>010-1234-5678</td>
	      </tr>
	      <tr>
	        <td>Mary</td>
	        <td>인사과</td>
	        <td>대리</td>
	        <td>mary@example.com</td>
	        <td>010-1234-5678</td>
	      </tr>
	      <tr>
	        <td>July</td>
	        <td>인사과</td>
	        <td>사원</td>
	        <td>july@example.com</td>
	        <td>010-1234-5678</td>
	      </tr>
	    </tbody>
	  </table>
	</div>
	
</body>
</html>