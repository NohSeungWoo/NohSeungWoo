<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<% String ctxPath = request.getContextPath(); %>

<style>
	
	.subjectStyle {font-weight: bold;
				   color: navy;
				   cursor: pointer;} /* 글제목에 마우스 효과주기 */
	
</style>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 글목록 중에 글제목에 마우스올리면 효과주기
		$("span.subject").bind("mouseover",function(event){
			var $target = $(event.target); // 여러가지 글제목 중에서, 실제 마우스가 올라간 곳
			$target.addClass("subjectStyle");
		});
		
		$("span.subject").bind("mouseout",function(event){
			var $target = $(event.target); 
			$target.removeClass("subjectStyle");
		});
		
		
		// === jQuery UI 의 datepicker(전체 datepicker 옵션 일괄 설정하기) === //
		//	     한번의 설정으로 $("input#fromDate"), $('input#toDate')의 옵션을 모두 설정할 수 있다.
        $(function() {
            //모든 datepicker에 대한 공통 옵션 설정
            $.datepicker.setDefaults({
                dateFormat: 'yy-mm-dd' //Input Display Format 변경
                ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
                ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
                ,changeYear: true //콤보박스에서 년 선택 가능
                ,changeMonth: true //콤보박스에서 월 선택 가능                
              ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
              ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
              ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
              ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
                ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
                ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
                ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
             // ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
             // ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                    
            });
 
            //input을 datepicker로 선언
            $("input#fromDate").datepicker();                    
            $("input#toDate").datepicker();
            
            //From의 초기값을 오늘 날짜로 설정
            $('input#fromDate').datepicker('setDate', '-3M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
            
            //To의 초기값을 3일후로 설정
            $('input#toDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
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
		
	
		
	<!-- 여기에 검색어랑 글쓴날짜 조회 디자인 추가 -->
	<div class="mt-3 p-3" style="border-top: solid 1px #dee2e6; border-bottom: solid 1px #dee2e6;">
		
		<div>
			<span style="display: inline-block; width: 100px;">글등록일</span> <!-- span태그는 인라인방식이라서 height,width 주지 못하므로 inline-block처리함. -->
			
			<input type="text" id="fromDate" style="width: 150px;" />
			~
			<input type="text" id="toDate" style="width: 150px;" />
		</div>
		
		<div>
			<div class="mt-3" style="display: inline-block;">
				<div style="display: inline-block; width: 100px;">게시판 종류</div>
				<select style="width: 350px; height: 28.76px;">
					<option>전체</option>
					<option>공지사항</option>
					<option>자유게시판</option>
					<option>건의사항</option>
				</select>
			</div>
			
			&nbsp;&nbsp;&nbsp;&nbsp; <!-- float을 주면 화면사이즈가 작아질때 정렬 이상해짐. margin-r를 주면 화면사이즈 작아질때 공백 생김 -->
			
			<div class="mt-3" style="display: inline-block;">
				<span style="display: inline-block; width: 100px;">검색</span>
				<select id="searchType" style="width: 80px; height: 28.76px;">
					<option>글제목</option>
					<option>글쓴이</option>
				</select>
				<input type="text" id="searchWord" placeholder="게시물 제목 입력" style="width: 265px;"/>
			</div>
		</div>
		
	</div>
	<div class="mt-2 d-flex">
		<button type="button" class="btn btn-primary mx-auto">조회</button>
	</div>
	
	
	<!-- 게시물이 존재하는지 존재안하는지에 따라 달라짐. 시작-->
	<c:if test="${empty requestScope.boardList}"> <!-- 없든지 텅빈거다. -->
		<h4 class="mt-3" style="border-top: solid 1px #d9d9d9; border-bottom: solid 1px #d9d9d9; padding-top: 50px; padding-bottom: 50px;" align="center">최근 게시물이 없습니다.</h4>
	</c:if>
	
	<c:if test="${not empty requestScope.boardList}">
		
		<!-- 게시물 건수 시작 -->
		<div class="d-flex mt-3">
			<span style="color: #999; margin-right: auto;">총 <strong style="color: #000;">${fn:length(requestScope.boardList)}</strong>건</span>
			
			<select id="sizePerPage">
				<option>3</option>
				<option>5</option>
				<option>10</option>
			</select>
		</div>
		<!-- 게시물 건수 종료 -->
		
		<!-- 최근게시물 테이블 시작 -->
		<div class="table-responsive mt-1">
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
	<!-- 게시물이 존재하는지 존재안하는지에 따라 달라짐. 끝-->
	
	
	
	
	
	
	
	
</div>
