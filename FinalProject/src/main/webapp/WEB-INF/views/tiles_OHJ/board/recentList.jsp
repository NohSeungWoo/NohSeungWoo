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
		
		setSearchDate(); // 글등록일 검색 부분에 디폴트 값 넣어주기
		
		// 글목록 중에 글제목에 마우스올리면 효과주기
		$("span.subject").bind("mouseover",function(event){
			var $target = $(event.target); // 여러가지 글제목 중에서, 실제 마우스가 올라간 곳
			$target.addClass("subjectStyle");
		});
		
		$("span.subject").bind("mouseout",function(event){
			var $target = $(event.target); 
			$target.removeClass("subjectStyle");
		});
		
<%--		
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
--%>
		//검색시 검색조건 및 값 유지시키기
		if(${not empty requestScope.fromDate}){
			$("input#fromDate").val("${requestScope.fromDate}");
			$("input#toDate").val("${requestScope.toDate}");
		}
		if(${not empty requestScope.bCategory}){
			$("select#bCategory").val("${requestScope.bCategory}");
		}
		if(${not empty requestScope.searchType}){
			$("select#searchType").val("${requestScope.searchType}");
			$("input#searchWord").val("${requestScope.searchWord}");
		}
		
	});// end of $(document).ready(function(){})---------------------------
	
	
	// === Function Declaration === //
	
	// 해당 글 상세보기
	function goView(boardSeq){
		
		location.href = "<%= ctxPath%>/boardView.gw?boardSeq="+boardSeq;
		
	}// end of function goView(boardSeq){}-------------------------------
	
	// 검색조건에 맞는 글 검색하기
	function goSearch(){
		
		// 유효성 검사
		// var dateRegExp = /[0-9]{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])/; 	// 날짜형식을 2021-1-4     도 입력 가능하도록 함.
		var dateRegExp = /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/; 		// 날짜형식을 2021-01-04 만 가능하게 함.
		// 날짜 정규표현식 객체 생성
		
		var fromDate = $("input#fromDate").val();
		var toDate = $("input#toDate").val();
		
		var bool1 = dateRegExp.test(fromDate);
		var bool2 = dateRegExp.test(toDate);
		
		if(bool1 && bool2){ // 둘 다 형식에 맞는 경우에만 검색을 진행함.
			var frm = document.searchFrm;
			frm.method = "GET";
			frm.action = "<%= ctxPath%>/recentList.gw";
			frm.submit();
		}
		else{
			alert("존재하지 않는 날짜이거나 형식이 잘못되었습니다.\n예를 들어, 2021-01-04 로 맞춰주세요.");
			
			// 잘못 입력한 날짜를 디폴트로 바꾸기
			setSearchDate();
		}
		
	}// end of function goSearch(){}-------------------------------
	
	// 글등록일 검색 부분에 디폴트 값 넣어주기
	function setSearchDate(){
		
		var now = new Date(); 			// 현재시각 -> 아래의 코드들이 잘 적용이 되는지 확인하려면 new Date(2021,2,1,12,31,2) 로 바꿔서 하면 된다. -> 2021년3월1일 이라는 뜻임.
	//	console.log("확인용 now : " + now);
		
		var year  = now.getFullYear(); 	// 현재년도
		var month = now.getMonth()+1; 	// 현재월
		var date  = now.getDate(); 		// 현재일
           
		if(month < 10){
			month = "0"+month;
		}
		if(date < 10){
			date = "0"+date;
		}
		
		var nowDate = year+"-"+month+"-"+date;
	//	console.log("확인용 nowDate : " + nowDate); // 2021-11-24
		
		//////////////////////////////////////////////////////////
		
		var threeMonthsAgo = new Date(now.setMonth(now.getMonth()-3)); // 3달전 시각 (만약 지금이 21년 1월이라면, 20년 10월로 한다.) => ★날짜다시설정
	//	console.log("확인용 threeMonthsAgo : " + threeMonthsAgo);
		
		var yearAgo  = threeMonthsAgo.getFullYear(); // 3달전의 년도
		var monthAgo = threeMonthsAgo.getMonth()+1;  // 3달전의 월
		var dateAgo  = threeMonthsAgo.getDate(); 	 // 3달전의 일
		
		if(monthAgo < 10){
			monthAgo = "0"+monthAgo;
		}
		if(dateAgo < 10){
			dateAgo = "0"+dateAgo;
		}
		
		var threeMonthsAgoDate = yearAgo+"-"+monthAgo+"-"+dateAgo;
	//	console.log("확인용 threeMonthsAgoDate : " + threeMonthsAgoDate);
	
		//////////////////////////////////////////////////////////
		
		$("input#fromDate").val(threeMonthsAgoDate);
		$("input#toDate").val(nowDate);
		
	}// end of function setSearchDate(){}----------------------------------
	
</script>

<div class="container">

	<h3>최근 게시물</h3>
	<hr style="border: solid 1px gray;" />
		
	
	<%-- &101. 글검색 폼 추가하기 --%>
	<!-- 여기에 검색어랑 글쓴날짜 조회 디자인 추가 -->
	<div class="mt-3 p-3" style="border-top: solid 1px #dee2e6; border-bottom: solid 1px #dee2e6;">
		
		<form name="searchFrm">
			<div>
				<span style="display: inline-block; width: 100px;">글등록일</span> <!-- span태그는 인라인방식이라서 height,width 주지 못하므로 inline-block처리함. -->
				
				<input type="date" name="fromDate" id="fromDate" style="width: 150px;" /> <!-- ★ input태그 안에 datepicker가 있는 듯한 느낌으로 하기 위해서, type을 text가 아닌 "date"로 함. -->
				~
				<input type="date" name="toDate" id="toDate" style="width: 150px;" />
			</div>
			
			<div>
				<div class="mt-3" style="display: inline-block;">
					<div style="display: inline-block; width: 100px;">게시판 종류</div>
					<select name="bCategory" id="bCategory" style="width: 350px; height: 28.76px;">
						<option value="0">전체</option>
						<option value="1">공지사항</option>
						<option value="2">자유게시판</option>
						<option value="3">건의사항</option>
					</select>
				</div>
				
				&nbsp;&nbsp;&nbsp;&nbsp; <!-- float을 주면 화면사이즈가 작아질때 정렬 이상해짐. margin-r를 주면 화면사이즈 작아질때 공백 생김 -->
				
				<div class="mt-3" style="display: inline-block;">
					<span style="display: inline-block; width: 100px;">검색</span>
					<select name="searchType" id="searchType" style="width: 80px; height: 28.76px;">
						<option value="subject">글제목</option>
						<option value="name">글쓴이</option>
					</select>
					<input type="text" name="searchWord" id="searchWord" placeholder="게시물 제목 입력" style="width: 265px;"/>
				</div>
			</div>
		</form>
	</div>
	<div class="mt-2 d-flex">
		<button type="button" class="btn btn-primary mx-auto" onclick="goSearch();">조회</button>
	</div>
	
	
	<!-- 게시물이 존재하는지 존재안하는지에 따라 달라짐. 시작-->
	<c:if test='${empty requestScope.boardList and requestScope.bCategory ne ""}'> <!-- 없든지 텅비었으며, 유저가 카테고리번호를 장난친게 아닌 경우 -->
		<h4 class="mt-3" style="border-top: solid 1px #d9d9d9; border-bottom: solid 1px #d9d9d9; padding-top: 50px; padding-bottom: 50px;" align="center">최근 게시물이 없습니다.</h4>
	</c:if>
	
	<c:if test='${requestScope.bCategory eq ""}'> <!-- 유저가 카테고리번호를 장난친 경우 -->
		<h4 class="mt-3" style="border-top: solid 1px #d9d9d9; border-bottom: solid 1px #d9d9d9; padding-top: 50px; padding-bottom: 50px; color: red;" align="center">주소창에 장난치지 마세요!<br>해당하는 게시판 종류는 없습니다!!</h4>
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
							
								<%-- === 댓글쓰기가 없는 게시판 === 	
									<span class="subject" onclick="goView('${boardvo.boardSeq}');">${boardvo.subject}</span>
								--%>
					
								<%-- === 댓글쓰기가 있는 게시판 시작 === --%>
								<c:if test="${boardvo.commentCount > 0}">
									<span class="subject" onclick="goView('${boardvo.boardSeq}');">${boardvo.subject} [<span style="color: red;">${boardvo.commentCount}</span>]</span>
								</c:if>
								<c:if test="${boardvo.commentCount == 0}">
									<span class="subject" onclick="goView('${boardvo.boardSeq}');">${boardvo.subject}</span>
								</c:if>
								<%-- === 댓글쓰기가 있는 게시판 끝 === --%>
							
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


		<!-- === &122. 페이지바 시작 -->
		<nav style="clear: both;"> <!-- 페이지바는 페이지네비게이션(pagination) 이용 -->
			${requestScope.pageBar}
		</nav>
		<!-- 페이지바 끝 -->
		
		
	</c:if>
	<!-- 게시물이 존재하는지 존재안하는지에 따라 달라짐. 끝-->
	
	
	
	
	
	
	
	
</div>
