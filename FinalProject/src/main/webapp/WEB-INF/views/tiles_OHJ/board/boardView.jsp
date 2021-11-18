<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>

<style>
	
	.linkStyle{ /* 링크의 디자인처리 */
		color: #37f; 
		font-weight: bold;
	} 
	
	#aStyle{ /* 삭제링크에 마우스올리면 커서 모양 변경 */
		cursor: pointer;
	}
	
	.brStyle{border-right: solid 1px #dee2e6;} /* 댓글 테이블에서 부트스트랩으로 table-bordered하면 border가 0이 안됨. 따라서 내가 원하는 디자인처리하려고 class="table"에다가 border-right 디자인처리해줌. */
	
</style>

<script type="text/javascript">
	
	$(document).ready(function(){
		
	});// end of $(document).ready(function(){})----------------------------
	
	// Functional Declaration
	
	// 정말로 삭제할것인지 아닌지 물어봄. (=== &77. 나는 강사님과 달리 암호확인페이지 없으니 &77 진행안함. ===)
	function delConfirm(){

		var bool = confirm("정말로 글을 삭제하시겠습니까?");
	//	console.log("확인용 bool => " + bool);
		
		if(bool){
			location.href = "<%= ctxPath%>/boardDel.gw?boardSeq=${requestScope.boardvo.boardSeq}";
		}
		// 확인을 누르면 삭제하고, 취소를 누르면 그냥 냅둠.
	}
	
</script>

<div class="container">


	<!-- 글1개에 대한 정보 보여주기 시작 -->
	
	<!-- 원글이 존재하지 않을 경우 -->
	<c:if test="${empty requestScope.boardvo}"> <!-- get방식이라서 유저가 존재하지 않는 글번호, 숫자타입이 아닌 글번호로 장난칠 수 있다. -->
		<div style="padding: 50px 0; font-size: 16pt; color: red; text-align: center;">장난치지마세요! 해당하는 글은 존재하지 않습니다.</div>
	</c:if>
	
	<!-- 원글이 존재하는 경우 -->
	<c:if test="${not empty requestScope.boardvo}"> 
		
		
		<div class="mb-3 d-flex" style="border: solid 0px red;">
			<!-- 링크 (답글, 수정, 삭제, 게시글종류 이동) -->
			<span class="mr-auto d-flex"> <!-- 바깥에 d-flex를 주고, 내부에 오른쪽margin을 auto로 주면 [*     **] 이런식으로 배치된다. -->
				<a href="#" class="linkStyle mr-2 my-auto">답글</a> <!-- 세로중 가운데에 정렬하기 위해서 바깥에 d-flex를 주고 내부에 y축으로 auto를 줌. -->
				<a href="<%= ctxPath%>/boardEdit.gw?boardSeq=${requestScope.boardvo.boardSeq}" class="linkStyle mr-2 my-auto">수정</a>
				<a id="aStyle" onclick="delConfirm()" class="linkStyle mr-2 my-auto">삭제</a>
				<a href="#" class="linkStyle my-auto">이동</a>
			</span>
			
			<span>
				<button type="button" class="btn" style="border: solid 1px #dee2e6;">전체목록</button>
				<button type="button" class="btn btn-secondary">검색된목록</button>
			</span>
		</div>
		
		<!-- 원글정보 테이블 -->
		<table class="table">
			<tr>
				<th rowspan="2" width="10%" style="padding: 0; text-align: center;">
					<img alt="기본프로필_kh.jpg" src="<%= ctxPath%>/resources/images/기본프로필_kh.jpg" width="90" height="100">
				</th>
				<td colspan="4"><strong style="font-size: 18px;">${requestScope.boardvo.subject}</strong></td>
			</tr>
			<tr style="border-bottom: solid 1px #dee2e6;">
				<td>작성자 : ${requestScope.boardvo.positionName} ${requestScope.boardvo.name}</td>
				<td>글종류 : ${requestScope.boardvo.bCategoryName}</td>
				<td>조회수 : <span>${requestScope.boardvo.readCount}</span></td>
				<td>작성일자 : ${requestScope.boardvo.regDate}</td>
			</tr>
		</table>
		<!-- 글내용 -->
		<div class="p-3" style="border: solid 1px #dee2e6; word-break: break-all; min-height: 300px;">${requestScope.boardvo.content}</div>
		<%-- 
		      style="word-break: break-all; 은 공백없는 긴영문일 경우 width 크기를 뚫고 나오는 것을 막는 것임. 
		             그런데 style="word-break: break-all; 나 style="word-wrap: break-word; 은
		             테이블태그의 <td>태그에는 안되고 <p> 나 <div> 태그안에서 적용되어지므로 <td>태그에서 적용하려면
		      <table>태그속에 style="word-wrap: break-word; table-layout: fixed;" 을 주면 된다.
		--%>
		
	</c:if>
	<!-- 글1개에 대한 정보 보여주기 종료 -->
	
	
	
	
	
	
	
	<!-- 이전글, 다음글 보기 -->
	<div class="mt-3 d-flex">
		<i class="fas fa-arrow-circle-left mr-1 fa-2x"></i>
		<span class="mr-auto">이전글의 제목은 무엇입니다1</span>
		
		<span class="mr-1">다음글의 제목은 무엇입니다2</span>
		<i class="fas fa-arrow-alt-circle-right fa-2x"></i>
	</div>
	
	
	
	
	
<%-- 	
	
	<!-- 댓글쓰기 시작 -->
	<div class="mt-5" style="border-bottom: solid 1px #dee2e6; display: flex;"> <!-- span태그를 위아래로 꽉 채우기위한 flex -->
		<strong style="border-bottom: solid 2px #37f;">댓글쓰기</strong>
	</div>
	
	<div class="mt-3">
		<input type="text" id="commentContent" style="width: 100%" placeholder="훈훈해지는 댓글 부탁드립니다."/>
		
		<button type="button" class="btn btn-sm mt-1" style="border: solid 1px #dee2e6; color: #37f; float: right;">등록</button>
	</div>
	<!-- 댓글쓰기 끝 -->
	
	
	
	
	
	
	<!-- 댓글내용 보여주기 시작 -->
	<div class="mt-5" style="border-bottom: solid 1px #dee2e6; display: flex;"> <!-- span태그를 위아래로 꽉 채우기위한 flex -->
		<strong style="border-bottom: solid 2px #37f;">댓글내용</strong>
	</div>
	
	<!-- 댓글이 존재하지 않을 경우 -->
	<div class="table-responsive mt-3">
		<table class="table table-hover">
			<tr style="text-align: center; background-color: #F7F7F7;"> <!-- 글자 가운데정렬 -->
				<th class="brStyle" width="6%">번호</th>
				<th class="brStyle">댓글내용</th>
				<th class="brStyle" width="8%">작성자</th>
				<th width="17%">작성일자</th>
			</tr>
			<tr style="border-bottom: solid 1px #dee2e6;">
				<td colspan="4">
					<div class="my-5" align="center">
						<i class="fas fa-info-circle" style="color: #37f;"></i><br>
						조회 결과, 댓글이 존재하지 않습니다.
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 댓글이 존재하는 경우 -->
	<div class="table-responsive mt-3">
		<table class="table table-hover">
			<tr style="text-align: center; background-color: #F7F7F7;"> <!-- 글자 가운데정렬 -->
				<th class="brStyle" width="6%">번호</th>
				<th class="brStyle">댓글내용</th>
				<th class="brStyle" width="8%">작성자</th>
				<th width="17%">작성일자</th>
			</tr>
			<tr style="border-bottom: solid 1px #dee2e6;">
				<td class="brStyle" align="center">1</td>
				<td class="brStyle">네~~~알겠습니다~~~!</td>
				<td class="brStyle" align="center">서강준</td>
				<td align="center">2021-11-10 14:00:01</td>
			</tr>
		</table>
	</div>
	<!-- 댓글내용 보여주기 종료 -->

--%>


	
	
	
	
	
	
<%--  
<!-- 가비아 그룹웨어나 네이버 같은 댓글을 하고싶을때 디자인처리 -->
	<!-- 댓글쓰기 및 댓글보여주기 시작 -->
	<div class="mt-5" style="border-bottom: solid 1px #dee2e6; display: flex;"> <!-- span태그를 위아래로 꽉 채우기위한 flex -->
		<strong style="border-bottom: solid 2px #37f;">댓글</strong>
	</div>
	
	<div style="background-color: #f4f5f7">
	
		<span>2개의 댓글</span>
		
		<div class="container">
			<div style="border: solid 1px red;">
				<img alt="기본프로필.jpg" src="<%= request.getContextPath()%>/resources/images/기본프로필.JPG" width="45" height="50">
				<span>서강준</span>
				<span>2021-11-10 14:00:01</span>
				<span>네~~~알겠습니다~~~!</span>
			</div>
		</div>
		
	</div> 
	<!-- 댓글쓰기 및 댓글보여주기 끝 -->
--%>	
	
	
	
	
	
	
	
</div>