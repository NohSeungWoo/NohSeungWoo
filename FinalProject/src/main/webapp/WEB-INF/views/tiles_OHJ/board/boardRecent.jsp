<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<div class="container">

	<h3>최근 게시물</h3>
	<hr style="border: solid 1px gray;" />
	
	
	
	
	
	
	
		<!-- 게시물이 존재하는지 존재안하는지에 따라 달라짐. 시작-->
		<%-- 
		<c:if test="${empty requestScope.recentList}"> <!-- 없든지 텅빈거다. -->
			<h4 style="border-top: solid 1px #d9d9d9; border-bottom: solid 1px #d9d9d9; padding-top: 50px; padding-bottom: 50px;" align="center">최근 게시물이 없습니다.</h4>
		</c:if>
		
		<c:if test="${not empty requestScope.recentList}">
		--%>
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
						<%-- 
						<c:forEach var="boardvo" items="${requestScope.recentList}" >
						--%>
							<tr>
								<td class="verticalM" align="center">2</td>
								<td class="verticalM">메일 서비스 접속 장애 복구되었습니다.</td>
								<td class="verticalM" align="center">엄정화</td>
								<td class="verticalM" align="center">2021-11-08 12:04:13</td>
								<td class="verticalM" align="center">123</td>
							</tr>
							
							<tr>
								<td class="verticalM" align="center">1</td>
								<td class="verticalM">전자결재 서비스 점검 및 업데이트(12/03 토 01:00~05:00)</td>
								<td class="verticalM" align="center">이순신</td>
								<td class="verticalM" align="center">2021-11-07 14:51:04</td>
								<td class="verticalM" align="center">241</td>
							</tr>
						<%-- 	
						</c:forEach>
						--%>
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
			
		<%-- 	
		</c:if>
		--%>
		<!-- wishList가 존재하는지 존재안하는지에 따라 달라짐. 끝-->
	
	
	
	
	
	
	
	
	
	
	
	
</div>
