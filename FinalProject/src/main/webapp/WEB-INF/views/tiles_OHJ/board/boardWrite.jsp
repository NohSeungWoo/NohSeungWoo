<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
	
	th{border-right: solid 1px #dee2e6;} /* class가 table이니까 내가 원하는 table-bordered 처럼 보이도록 함. */
	
	.star { /* 필수 입력 사항 표시 */
		color: red;
        font-weight: bold;
        font-size: 15pt;
	}
	
</style>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		// input태그에 글제목 한글자씩 입력할때마다, 총글자수 나타내기
		$("input#subject").keyup(function(){
			
		//	console.log("확인용 $(this).val() : " + $(this).val());
			
			var subjectLen = $(this).val().length;
			// 입력된 글제목의 길이를 알아온다.
		//	console.log("확인용 subjectLen : " + subjectLen);
			
		/*
			// 한글이든 영어이든 상관없다.
			console.log("확인용 '안녕'.length : " + "안녕".length); // 2
			console.log("확인용 'ab'.length : " + "ab".length); // 2
			console.log("확인용 '12'.length : " + "12".length); // 2
		*/
			
			$("span#subjectLen").text(subjectLen);
			
		});
		
	});// end of $(document).ready(function(){})--------------------------
	
</script>

<div class="container">

	<h3>글쓰기</h3>
	<hr style="border: solid 1px gray;" />
	
	
	<div style="float: right;">
		<span class="star">*</span><span style="font-size: 15px;">&nbsp;필수입력사항</span>
	</div>
	
	<div class="table-responsive">
		<table class="table">
			<tr>
				<th>게시판종류&nbsp;<span class="star">*</span></th>
				<td>
					<select id="boardType">
						<option>-[필수]옵션을 선택해주세요-</option>
						<optgroup label="전사 게시판">
							<option>공지사항</option>
							<option>자유게시판</option>
							<option>건의사항</option>
						</optgroup>
						<optgroup label="그룹 게시판">
							<option>인사팀</option>
							<option>회계팀</option>
						</optgroup>
					</select>
				</td>
			</tr>
			<tr>	
				<th>글제목&nbsp;<span class="star">*</span></th>
				<td>
					<input type="text" id="subject" size="85" maxlength="50" placeholder="글제목 입력"/>
				<!-- 
					size="85"		: 웹페이지상에 보여주는 길이
					maxlength="50"  : 입력될 수 있는 글자(한글 또는 영문)의 최대글자수 -> 오라클DB의 컬럼길이와 똑같이 맵핑
				-->
					<span id="subjectLen" style="font-weight: bold;">0</span>/50
				</td>
			</tr>
			<tr>
				<th>파일첨부</th>
				<td><button type="button" id="fileAttach" class="btn btn-outline-secondary btn-sm">+</button></td>
			</tr>
			<tr style="border-bottom: solid 1px #dee2e6;">
				<th>참조글</th>
				<td><button type="button" id="refBoard" class="btn btn-outline-secondary btn-sm">+</button></td>
			</tr>
		</table>
	</div>
	
	<hr style="border: solid 1px gray;" />
	
</div>