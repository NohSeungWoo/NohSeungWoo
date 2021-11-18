<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<% String ctxPath = request.getContextPath(); %>

<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		
		var authority = "${requestScope.authority}";
		
		if(authority == "a1"}){
			
			$("button#agrBtn").click(function(){
				
				var frm = document.agrFrm;
				
			});
			
		}
		
	});
</script>

<div class="container-fluid">

	<%-- 선택한 기안종류에 맞게 --%>
	<h4 class="mb-3"><b>${requestScope.approval.subject}</b></h4>
	<div class="mb-2" style="display: flex; border-bottom: 1px solid black;">
		<div style="font-weight: bold;">결재선</div>
	</div>
    
	<div class="row selemps appr">
		<div class="col-md-2 mb-2">
	  		<div class="card">
		    	<div class="card-header text-center py-1">기안</div>
		    	<div class="card-body text-center py-1">
		    		<p class="card-text">${requestScope.sendEmp.name}</p>
		    	</div>
		    	<div class="text-center"><small>${requestScope.sendEmp.positionname}<br>${requestScope.sendEmp.departmentname}</small></div>
	    	</div>
	  	</div>
	  	<c:forEach var="arrEmps" items="${requestScope.apprList}">
			<div class="col-md-2 mb-2">
		  		<div class="card">
			    	<div class="card-header text-center py-1">결재</div>
			    	<div class="card-body text-center py-1">
			    		<p class="card-text">${arrEmps.name}</p>
			    	</div>
			    	<div class="text-center"><small>${arrEmps.positionname}<br>${arrEmps.departmentname}</small></div>
		    	</div>
		  	</div>
	  	</c:forEach>
	</div>
	<c:if test="${not empty requestScope.coopList}">
		<div class="my-3">
			<div class="mb-2 pb-1" style="display: flex; border-bottom: 1px solid black;">
				<div style="font-weight: bold;">협조</div>
			</div>
			<div class="row selemps cone">
				<c:forEach var="cooEmps" items="${requestScope.coopList}">
					<div class="col-md-2 mb-2">
				  		<div class="card">
					    	<div class="card-body text-center py-1">
					    		<p class="card-text">${cooEmps.name}</p>
					    	</div>
					    	<div class="text-center"><small>${cooEmps.positionname}<br>${cooEmps.departmentname}</small></div>
				    	</div>
				  	</div>
			  	</c:forEach>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty requestScope.reciList}">
		<div class="mt-3 mb-4">
			<div class="mb-2 pb-1" style="display: flex; border-bottom: 1px solid black;">
				<div style="font-weight: bold;">수신</div>
			</div>
			<div class="row selemps receiver">
				<c:forEach var="recEmps" items="${requestScope.reciList}">
					<div class="col-md-2 mb-2">
				  		<div class="card">
					    	<div class="card-body text-center py-1">
					    		<p class="card-text">${recEmps.name}</p>
					    	</div>
					    	<div class="text-center"><small>${recEmps.positionname}<br>${recEmps.departmentname}</small></div>
				    	</div>
				  	</div>
			  	</c:forEach>
		  	</div>
		</div>
	</c:if>
	<hr>
	<div class="my-3">
		<div class="mb-1 pb-1" style="display: flex; border-bottom: 1px solid black;">
			<div style="font-weight: bold;">기안내용</div>
		</div>
		<input type="hidden" name="apprEmp" value="" />
		<input type="hidden" name="coopEmp" value="" />
		<input type="hidden" name="reciEmp" value="" />
		<input type="hidden" name="fk_apcano" value="${requestScope.apcano}" />
		<input type="hidden" name="fk_employeeid" value="${sessionScope.loginuser.employeeid}" />
		
		<table class="table " style="width:100%; margin-bottom:5px;">
			<tbody class="border-bottom" style="background-color: #f7f7f7;" >
				<tr>
					<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">기안제목</th>
					<td>
						${requestScope.approval.subject}
					</td>
				</tr>
				<tr>
					<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">작성자</th>
					<td>
						${requestScope.sendEmp.departmentname}부&nbsp;${requestScope.sendEmp.positionname}&nbsp;${requestScope.sendEmp.name}
					</td>
				</tr>
				<tr>
					<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">파일첨부</th>
					<td>
						<a href="#">${requestScope.approval.orgFilename}</a>
					</td>
				</tr>
				<%-- 회의록 --%>
				<c:if test="${requestScope.approval.fk_apcano == 50}">
					<tr>
						<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">회의일시</th>
						<td>
							${requestScope.cateApdetail.midate}
						</td>
					</tr>
					<tr>
						<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">회의목적</th>
						<td>
							<p style="width: 100%; min-height:50px; word-break: break-all;">${requestScope.cateApdetail.purpose}</p>
						</td>
					</tr>
				</c:if>
				
				<%-- 업무보고 --%>
				<c:if test="${requestScope.approval.fk_apcano == 51}">
					<tr>
						<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">업무기간</th>
						<td>
							${requestScope.cateApdetail.taskdate}
						</td>
					</tr>
					<tr>
						<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">이슈</th>
						<td>
							<p style="width: 100%; min-height:50px; word-break: break-all;">${requestScope.cateApdetail.issue}</p>
						</td>
					</tr>
				</c:if>
				<%-- 지출결의서 --%>
				<c:if test="${requestScope.approval.fk_apcano == 53}">
					<tr>
						<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">지출예정일</th>
						<td>
							${requestScope.cateApdetail.spdate}
						</td>
					</tr>
					<tr>
						<th class="border-right" style="width: 15%; vertical-align: middle; text-align: center;">금액</th>
						<td>
							<fmt:formatNumber value="${requestScope.cateApdetail.amount}" pattern="#,###" />원
						</td>
					</tr>
				</c:if>
				
			</tbody>
		</table>
		<div class="mb-3" style="border: 1px solid #dee2e6; padding:10px;">
			<p style="width: 100%; min-height:150px; word-break: break-all;">${requestScope.approval.content}</p>
		</div>
		<div class="" style="text-align: center;">
			<input type="button" class="btn btn-secondary" value="목록으로" onclick="javascript:history.back();">
			<c:if test="${requestScope.authority == 'a1'}">
				<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">결재</button>
		        <div class="modal fade" id="myModal" data-backdrop="static">
			    	<div class="modal-dialog modal-dialog-centered">
				    	<div class="modal-content">
				        
				          	<!-- Modal Header -->
				          	<div class="modal-header">
				            	<h4 class="modal-title">결재하기</h4>
				            	<button type="button" class="close cancle" data-dismiss="modal">&times;</button>
				          	</div>
				          
				          	<!-- Modal body -->
				          	<div class="modal-body">
				          		<form name="agrFrm">
				          			<table class="table " style="width:100%; margin-bottom:5px;">
										<tbody class="border-bottom">
											<tr>
												<th class="border-right" style="width: 30%; vertical-align: middle; text-align: center;">결재상태</th>
												<td style="text-align: left; vertical-align: middle;">
													<div class="mt-2">
														<input type="radio" name="agr" id="yagr"><label class="ml-1 mr-3" for="yagr">승인</label>
														<input type="radio" name="agr" id="nagr"><label class="ml-1 "for="nagr">반려</label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="border-right" style="width: 30%; vertical-align: middle; text-align: center;">결재의견</th>
												<td>
													<textarea style="width: 100%; height: 150px; resize: none;" name="opinion" placeholder="내용을 입력해주세요"></textarea>
												</td>
											</tr>
										</tbody>
									</table>	
				          		</form>
				          	</div>
				          	<!-- Modal footer -->
					        <div class="modal-footer">
					            <button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
					            <button type="button" class="applyBtn btn btn-primary" id="agrBtn" >결재</button>
					            <button type="button" class="hiddenBtn btn btn-light" data-dismiss="modal" style="display:none;"></button>
					        </div>
			          	</div>
		          	</div>
	          	</div>
			</c:if>
        </div>
	</div>	
</div>