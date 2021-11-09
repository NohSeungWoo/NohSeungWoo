<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
	ul.departments {
		list-style: none;
		padding-left: 0;
	}
	
	.selectEmp {
		padding-left: 50px;
	}
	.selectEmp:hover {
		background-color: lightblue;
		cursor: pointer;
	}
	
	.selectedEmp {
		background-color: lightblue;
	}
</style>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 결재선 설정 모달창에서 직원을 클릭 했을때
		$(".selectEmp").click(function(){
			$(".selectEmp").removeClass("selectedEmp");
			$(this).addClass("selectedEmp");
		});
		
		// 결재선 설정 모달창에서 결재 협조 수신 버튼을 클릭했을때 
		$(".approvalTypeBtn").click(function(){
			
			var selectedEmpSeq = $("li.selectedEmp > input[name=seq]").val();
			var selectedEmpDP = $("li.selectedEmp > input[name=department]").val();
			var selectedEmpName = $("li.selectedEmp > span.name").text();
			var selectedEmpPO = $("li.selectedEmp > input[name=position]").val();
			
			if( selectedEmpSeq == null ) {
				alert("직원을 선택하세요!");
			}
			else {
				
				var flag = false;
				
				$("tbody.approvalPerson > tr.showedEmp > td.empSeq").each(function(index,item){
					if ($(item).text() == selectedEmpSeq ) {
						flag = true;
						alert("결제선 중복!");
					}
				});
				
				if(!flag){
				
					var $target = $(event.target);
					var i = $("button.approvalTypeBtn").index($target);
					
					var html = "<tr class='emps notsaved showedEmp'>" +
									"<td class='empDP'>"+selectedEmpDP+"</td>" +
									"<td class='empName'>"+selectedEmpName+"</td>" +
									"<td class='empPO'>"+selectedEmpPO+"</td>" +
									"<td class='deleteEmp'><button class='deleteEmpBtn btn btn-light btn-sm'>x</button></td>" +
									"<td class='empSeq' style='display:none'>"+selectedEmpSeq+"</td>" +
							   "</tr>";
					
					$("tbody.approvalPerson:eq("+i+")").append(html);
				}
			}
		});// end of $(".approvalTypeBtn").click(function(){}----------------
				
		// 결재선 설정 모달창에서 선택한 직원 취소하기
		$(document).on("click", "button.deleteEmpBtn", function(){
			$(this).parent().parent().hide();	// 삭제를 선택한 버튼의 tr 태그를 숨김
			$(this).parent().parent().addClass("deleteNotSaved"); // 숨긴태그가 아직 저장되지않았음을 표시 하는 클래스 추가
			$(this).parent().parent().removeClass("showedEmp"); // 보여주고 있는 직원 표시인 showedEmp 태그 삭제
		});	
				
		// 결재선 설정 모달창에서 닫기, 취소 버튼을 클릭했을때 
		$("button.cancle").click(function(){
			$(".selectEmp").removeClass("selectedEmp"); // 모달창에서 좌측 선택된 직원에 css 효과 주는 클래스 삭제
			$("tr.notsaved").remove();	// 저장되지 않고 보여주기만 한 직원들 다시 삭제
			$("tr.deleteNotSaved").show(); // 삭제 버튼을 눌렀던 직원들 다시 보여주기
			$("tr.emps").addClass("showedEmp"); // 보여주고 있는 직원 표시인 showedEmp 태그 추가
			$("tr.emps").removeClass("deleteNotSaved"); // 삭제버튼을 눌러서 숨겨두었다는 표시를 다시 제거 
		});	
		
		// 결재선 설정 모달창에서 적용 버튼을 클릭했을때 
		$(document).on("click", "button.applyBtn", function(){
			
			// 결재에 인원이 있는지 확인
			var isExist = false;
			$("tbody.approvalEmp tr.showedEmp").each(function(index,item){
				isExist = true; // 삭제버튼을 누르지 않은 보여지고 있는 직원 (showedEmp) 이 있다면 true;
				return false;
			});
			
			if(!isExist){ // 결재자가 아무도 없다면
				alert("최소 1명의 결재자를 설정해주세요.");
			}
			else { // 결재자를 선택했다면
				
				// 모달창에서 좌측 선택된 직원에 css 효과 주는 클래스 삭제
				$(".selectEmp").removeClass("selectedEmp");
			
				// 삭제버튼을 누른 직원들 제거
				$("tr.deleteNotSaved").remove();
			
				// 결재선을 새로 설정한 경우 기존에 입력한 내용물 제거
				$(".addedApprovalEmps").remove();
				
				// 선택된 결재자, 협조자, 수신자에 맞게 넣어주기
				$(".approvalPerson").each(function(index,item){
					
					var html = "";
					var employeeID = "";	// DB에 넣을 직원 아이디
					
					// 삭제버튼을 누르지 않고 보여지고 있는 직원들만
					$(item).children(".showedEmp").each(function(i,t){
						
						// 적용전 이라는 표시인 notsaved 클래스 제거해서 저장되었음
						$(t).removeClass("notsaved");
						
						html += "<div class='col-md-2 mb-2 addedApprovalEmps'>" +
									"<div class='card'>";
									if(index == 0){ // 결재인 경우
								    	html += "<div class='card-header text-center py-1'>결재</div>"; 
									}
							  	html += "<div class='card-body text-center py-1'>" +
								    		"<p class='card-text'>"+$(t).children(".empName").text()+"</p>" +
								    	"</div>" +
								    	"<div class='text-center'><small>"+$(t).children(".empPO").text()+"<br>"+$(t).children(".empDP").text()+"</small></div>" +
					    			"</div>" +
					    		"</div>";
					    
					    if(i==0){
						  	employeeID += $(t).children(".empSeq").text();
					    }
					    else {
					    	employeeID += ","+$(t).children(".empSeq").text();
					    }
					  	
					});
					
					if(index==0) { // 결재인 경우
						$("div.selemps:eq("+index+")").append(html);
						$("input[name=apprEmp]").val(employeeID);
					}
					else if(index==1) { // 협조인 경우
						$("div.selemps:eq("+index+")").html(html);
						$("input[name=coopEmp]").val(employeeID);
					}
					else if(index==2) { // 수신인 경우
						$("div.selemps:eq("+index+")").html(html);
						$("input[name=reciEmp]").val(employeeID);
					}
					
				});
				
	    		// 모달창을 닫아준다.
				$("button.hiddenBtn").trigger("click");
			}
		});	
		
		
		
	});
	
</script>
<div class="container-fluid">

	<%-- 선택한 기안종류에 맞게 --%>
	<h4><b>회의록</b></h4>
	<div class="mb-1" style="display: flex; border-bottom: 1px solid black;">
		<div>결재선</div>
		<button class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#myModal" style="margin-left:auto;">결재선 설정</button>
	</div>
	
	<%-- 결재선 선택 모달 시작 --%>
	<!-- The Modal -->
    <div class="modal fade" id="myModal" data-backdrop="static">
      <div class="modal-dialog modal-xl">
        <div class="modal-content">
        
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">결제선 설정</h4>
            <button type="button" class="close cancle" data-dismiss="modal">&times;</button>
          </div>
          
          <!-- Modal body -->
          <div class="modal-body">
            <div class="row">
            	<div class="col-lg-5">
            		<div class="card shadow mb-4">
						<div class="card-header py-3">
							<input type="text" placeholder="사원명 ,부서명">
						</div>
						<div class="card-body">
							<div class="list-group-flush">
								<a href="#department1" data-toggle="collapse" aria-expanded="false" class="list-group-item list-group-item-action dropdown-toggle">마케팅부</a>
							  		<ul class="collapse departments" id="department1" >
							            <li class="selectEmp">
							                	<span class="name">우희철</span>
							                	<input type="hidden" name="seq" value="211059005" />
							                	<input type="hidden" name="department" value="부서명" />
							                	<input type="hidden" name="position" value="직책" />
							            </li>
							            <li class="selectEmp">
							                	<span class="name">이순신</span>
							                	<input type="hidden" name="seq" value="211059004" />
							                	<input type="hidden" name="department" value="부서명" />
							                	<input type="hidden" name="position" value="직책" />
							            </li>
							            <li class="selectEmp">
							                	<span class="name">엄정화</span>
							                	<input type="hidden" name="seq" value="211059006" />
							                	<input type="hidden" name="department" value="부서명" />
							                	<input type="hidden" name="position" value="직책" />
							            </li>
							        </ul>
							  	<a href="#department2" data-toggle="collapse" aria-expanded="false" class="list-group-item list-group-item-action dropdown-toggle">관리부</a>
							  		<ul class="collapse departments" id="department2">
							            <li class="selectEmp">
							                	사원1
							            </li>
							            <li class="selectEmp">
							                	사원2
							            </li>
							            <li class="selectEmp">
							                	사원3
							            </li>
							        </ul>
							</div>
	                  	</div>
	                </div>  	
            	</div>
			            	
            	<div class="col-lg-2" style="display:flex; align-items: center; justify-content: center;">
            		<div class="btn-group-vertical approvalTypeBtns">
					  <button type="button" class="approvalTypeBtn btn btn-light mb-2">결재</button>
					  <button type="button" class="approvalTypeBtn btn btn-light mb-2">협조</button>
					  <button type="button" class="approvalTypeBtn btn btn-light">수신</button>
					</div>
            	</div>
            	
            	<div class="col-lg-5">
	            	<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold">결재자 선택</h6>
						</div>
						<div class="card-body">
							<table class="table">
								<thead>
									<tr>
										<th colspan="4">결재</th>
									</tr>
								</thead>
								<tbody class="approvalPerson approvalEmp">
								</tbody>
							</table>
							<table class="table">
								<thead>
									<tr>
										<th colspan="4">협조</th>
									</tr>
								</thead>
								<tbody class="approvalPerson">
									
								</tbody>
							</table>
							<table class="table">
								<thead>
									<tr>
										<th colspan="4">수신</th>
									</tr>
								</thead>
								<tbody class="approvalPerson">
									
								</tbody>
							</table>
						</div>
					</div>
            	</div>
            </div>
          </div>
          
          <!-- Modal footer -->
          <div class="modal-footer">
            <button type="button" class="cancle btn btn-light" data-dismiss="modal">취소</button>
            <button type="button" class="applyBtn btn btn-primary">적용</button>
            <button type="button" class="hiddenBtn btn btn-light" data-dismiss="modal" style="display:none;"></button>
          </div>
          
        </div>
      </div>
    </div>
    <%-- 결재선 선택 모달 끝 --%>
    
	<div class="row selemps appr">
		<div class="col-md-2 mb-2">
	  		<div class="card">
		    	<div class="card-header text-center py-1">기안</div>
		    	<div class="card-body text-center py-1">
		    		<p class="card-text">우희철</p>
		    	</div>
		    	<div class="text-center"><small>직책<br>부서명</small></div>
	    	</div>
	  	</div>
	</div>
	<div>
		<div class="mb-1" style="display: flex; border-bottom: 1px solid black;">
			<div>협조</div>
		</div>
		<div class="row selemps cone">
			
		</div>
	</div>
	<div class="my-3">
		<div class="mb-1" style="display: flex; border-bottom: 1px solid black;">
			<div>수신</div>
		</div>
		<div class="row selemps receiver">
			
	  	</div>
	</div>
	<div class="my-3">
		<div class="mb-1" style="display: flex; border-bottom: 1px solid black;">
			<div>기안내용</div>
		</div>
		<form>
		
			<input type="text" name="apprEmp" value="" />
			<input type="text" name="coopEmp" value="" />
			<input type="text" name="reciEmp" value="" />
			
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td>기안제목</td>
						<td>
							<input type="text" />
						</td>
					</tr>
					<tr>
						<td>파일첨부</td>
						<td>
							<input type="file" />
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="" style="text-align: center;">
				<button type="button" class="btn btn-secondary">취소</button>
		        <button type="button" class="btn btn-primary">상신하기</button>
	        </div>
		</form>
	</div>	
</div>	