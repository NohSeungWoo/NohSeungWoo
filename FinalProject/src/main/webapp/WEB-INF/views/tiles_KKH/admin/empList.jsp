<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

	$(document).ready(function() {
		
		$("td").click(function() {
			var target = event.target;
			
			var employeeid = target.parent().find(".employeeid").value;
			alert(employeeid);
		});
		
	});// end of $(document).ready(function() {})

</script>

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
	
	tr:hover {
		cursor: pointer;
		background-color: #e6e6e6;
	}
	
</style>

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
	        <th>사번</th>
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
	    <c:if test="${not empty requestScope.empList}">
	    	<c:forEach var="map" items="${requestScope.empList}">
	    		<tr>
			        <td class="name">${map.name}</td>
			        <td class="employeeid">${map.employeeid}</td>
			        <td class="departmentname">${map.departmentname}</td>
			        <td class="positionname">${map.positionname}</td>
			        <td class="email">${map.email}</td>
			        <td class="mobile">${map.mobile}</td>
			    </tr>
	    	</c:forEach>
	    </c:if>
	    </tbody>
	  </table>
	</div>
