<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- ======= #28. tile2 중 sideinfo 페이지 만들기  ======= --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	
	button#writeBtn{
		width: 100%;
	}
	
</style>

<script type="text/javascript">

	$(document).ready(function() {
	
	}); // end of ready(); ---------------------------------

</script>

<div class="container">

	<button type="button" id="writeBtn" class="btn btn-dark btn-lg" onclick="" >글쓰기</button>
	
	<a href="<%= request.getContextPath()%>">최근 게시물</a>
	<a href="<%= request.getContextPath()%>">전사 게시판</a>
		- 사내공지
		- 자유게시판
		- 설문조사
	<a href="<%= request.getContextPath()%>">그룹 게시판</a>
	<a href="<%= request.getContextPath()%>">환경설정</a>
	<a href="<%= request.getContextPath()%>">게시판 만들기</a>
	<a href="<%= request.getContextPath()%>">게시판 관리</a>
	
	
</div>
   