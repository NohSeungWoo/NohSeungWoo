<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<% String ctxPath = request.getContextPath(); %>

<style>
	
	.subjectStyle {font-weight: bold;
				   color: navy;
				   cursor: pointer;} /* 글제목에 마우스 효과주기 */
	
</style>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("span.subject").bind("mouseover",function(event){
			var $target = $(event.target); // 여러가지 글제목 중에서, 실제 마우스가 올라간 곳
			$target.addClass("subjectStyle");
		});
		
		$("span.subject").bind("mouseout",function(event){
			var $target = $(event.target); 
			$target.removeClass("subjectStyle");
		});
		
	});// end of $(document).ready(function(){})---------------------------
	
	// Function Declaration
	function goView(boardSeq){ // 해당 글 상세보기
		
		location.href = "<%= ctxPath%>/boardView.gw?boardSeq="+boardSeq;
		
	}
	
</script>

<div class="container">

	<h3>최근 게시물</h3>
	<hr style="border: solid 1px gray;" />
	
	
	
		<!-- 게시물이 존재하는지 존재안하는지에 따라 달라짐. 시작-->
		<c:if test="${empty requestScope.boardList}"> <!-- 없든지 텅빈거다. -->
			<h4 style="border-top: solid 1px #d9d9d9; border-bottom: solid 1px #d9d9d9; padding-top: 50px; padding-bottom: 50px;" align="center">최근 게시물이 없습니다.</h4>
		</c:if>
		
		<c:if test="${not empty requestScope.boardList}">
		
			<!-- 최근게시물 테이블 시작 -->
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr style="text-align: center; background-color: #F7F7F7;"> <!-- 글자 가운데정렬 -->
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach var="boardvo" items="${requestScope.boardList}" >
						
							<tr>
								<td class="verticalM" align="center">${boardvo.boardSeq}</td>
								<td class="verticalM">
									
									<span class="subject" onclick="goView('${boardvo.boardSeq}');">${boardvo.subject}</span>
								
								</td>
								<td class="verticalM" align="center">${boardvo.name}</td>
								<td class="verticalM" align="center">${boardvo.regDate}</td>
								<td class="verticalM" align="center">${boardvo.readCount}</td>
							</tr>
							
						</c:forEach>
						
					</tbody>
				</table>
			</div>
			<!-- 최근게시물 테이블 끝 -->
	
	
	
			<!-- 페이지바 시작 -->
			<nav style="clear: both;"> <!-- 페이지바는 페이지네비게이션(pagination) 이용 -->
				<ul class="pagination justify-content-center" style="margin-top: 50px;"><%-- ${requestScope.pageBar} --%>
					<li class='page-item'><a class='page-link' href='#'><span class='text-dark'>1</span></a></li>
				</ul>
			</nav>
			<!-- 페이지바 끝 -->
			
			
		</c:if>
		<!-- wishList가 존재하는지 존재안하는지에 따라 달라짐. 끝-->
	
	
	
	
	
	
	
	
</div>
