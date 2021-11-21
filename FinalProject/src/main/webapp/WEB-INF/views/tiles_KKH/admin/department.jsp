<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
	tr.department:hover {
		background-color: #cccccc;
	}
	
</style>

<script type="text/javascript">

	$(document).ready(function() {
		
		// 부서목록 가져오기
		getDepartmentName();
		
		// 부서 클릭했을 때 해당하는 부서의 이벤트 처리
		$(document).on("click", "tr.department", function() {
			// 부서번호 가져오기
			var department = $(this).text();
			
			location.href = "<%= ctxPath%>/admin/department.gw?department=" + department;
		});
		
		// 검색창 버튼 클릭했을 때 내용물 검색 이벤트 처리
		$("i#searchIcon").click(function() {
			searchEmpDepart();
		});
		
		// 검색창 검색어를 입력하고 엔터키를 눌렀을 때 검색 이벤트 처리
		$("input#searchEmp").keyup(function(event) {
			if(event.keyCode == 13) {
				searchEmpDepart();
			}
		});
		
	});// end of $(document).ready(function() {})

	
	// Function Declaration
	// === 부서목록 가져오기(Ajax) === //
	function getDepartmentName() {
		$.ajax({
			url:"<%= ctxPath%>/getDepartmentName.gw",
			type:"GET",
			dataType:"JSON",
			success:function(json) {
				if(json.length > 0) {
							
					var html = "";
					
					$.each(json, function(index, item) {
						var departmentname = item.depart;
						var departno = item.departno
							
						html += "<tr class='department' id=" + departno + ">"+
									"<td style='border-top: none;'>" + departmentname + "</td>"+
					      		"</tr>";
						
					});
					
					$("tbody#departList").html(html);
				}
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
		});
	}// end of function getDepartmentName() {}
	
	// === 사원정보 가져오기(Ajax) === //
	function getEmpList() {
		$.ajax({
			url:"<%= ctxPath%>/getEmpDepartList.gw",
			type:"GET",
			dataType:"JSON",
			success:function(json) {
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
		});
	}
	
	// === 검색창에 직원명 검색하기 === //
	function searchEmpDepart() {
		var searchemp = $("input#searchEmp").val();
		location.href = "<%= ctxPath%>/admin/department.gw?searchEmp=" + searchemp;
	}
	
</script>

<div class="container-fluid  container-xl" id="groupList">
  <div class="mb-3">
  	<span class="h4 font-weight-bold">부서 관리</span>
  </div>
  <div class="mt-2 mb-3" style="border: solid 1px gray; width: 25%; float: left; border-radius: 5px;">
  	<table class="table">
  		<thead class="thead-light">
	      <tr>
	        <th>부서명</th>
	      </tr>
	    </thead>
	    <tbody id="departList" style="font-size: 11pt; cursor: pointer;">
	    </tbody>
	      <tr>
	        <td>
	        	<div style="margin-top: 10px;">
		        	<a href="#" class="mr-2"><i class="fas fa-plus fa-lg"></i></a>
		        	<a href="#" class="mr-2"><i class="fas fa-trash fa-lg"></i></a>
		        	<a href="#" class="mr-2"><i class="far fa-edit fa-lg"></i></a>
	        	</div>
	        </td>
	      </tr>
  	</table>
  </div>
  <div class="mt-2 mb-3" style="border: solid 1px gray; width: 73%; float: right; border-radius: 5px; ">
  	<div class="row">
  		<div class="col-11 col-lg-4">
  		</div>
  	</div>
  	
  	<div class="table-responsive">
	  	<table class="table">
	  		<thead class="thead-light">
		      <tr>
		        <th colspan="5">
		        	<div class="float-left">
		        		1조파이널프로젝트&nbsp;<span style="font-weight: normal; font-size: 10pt;">&nbsp;총 <span style="font-weight: bold;">${requestScope.empCnt}</span>명</span>
		        	</div>
		        	<div class="float-right">
		        		<i id="searchIcon" class="fas fa-search pt-1 ml-2" style="cursor: pointer;"></i>
		        	</div>
		        	<div class="float-right">
		        		<input id="searchEmp" name="searchEmp" type="text" class="form-control rounded-pill" placeholder="직원 검색" style="height: 25px;">
		        	</div>
		        </th>
		      </tr>
		    </thead>
	  		<tbody style="font-size: 11pt;">
	  		  <c:if test="${not empty requestScope.empDepartList}">
	  		  	<c:forEach var="map" items="${requestScope.empDepartList}">
			      <tr class="employeeList">
			      	<td style="width: 5%;"><input type="checkbox" style="margin-left: 15px;"/></td>
			      	<td style="width: 15%">${map.employeeid}</td>
			        <td style="width: 12%;">${map.name}</td>
			        <td style="width: 15%;">${map.departmentname}</td>
			        <td style="width: 15%;">${map.positionname}</td>
			      </tr>
	  		  	</c:forEach>
	  		  </c:if>
		      <tr>
		        <td colspan="5">
					<button class="btn btn-sm mt-2" style="background-color: #e6f9ff; color: #3377ff; font-weight: bold; font-size: 10pt;">다른 조직으로 이동</button>
				</td>
		      </tr>
		    </tbody>
	  	</table>
		<%-- === #122. 페이지바 보여주기 --%>
		  <div align="center" style="width: 70%; border: solid 0px gray; margin: 20px auto;">
		  	  ${requestScope.pageBar}
		  </div>
  	</div>
  </div>
</div>