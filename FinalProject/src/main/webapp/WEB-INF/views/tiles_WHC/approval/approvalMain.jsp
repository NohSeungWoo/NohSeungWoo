<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
	.tabmenu:hover {
		cursor: pointer;
		font-weight: bold;
	}
	div.tabcontent {
		display:none;
	}
	.active-tab {
      background-color: lightblue;
      color: white;
      font-weight: bold;
   }
</style>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("li.tabmenu").click(function(event){
			var $target = $(event.target);
			var i = $("li.tabmenu").index($target);
			$("div.tabcontent").css("display","none");
			$("div.tabcontent:eq("+i+")").css("display","block");
			
			$("li.tabmenu").removeClass("active-tab");
			$target.addClass("active-tab");
		});
		
		$("li.tabmenu").eq(0).trigger('click');
	});
	
</script>
<div class="container-fluid">
	<h4><b>나의 현황</b></h4>
	
	<div class="card-group">
		<div class="card py-lg-3">
	  		<div class="card-body text-center">
	    		<h5 class="card-title mb-3">상신한</h5>
			    <a href="#" class="stretched-link" style="font-size:18pt; font-weight: bold;">0</a>
		  	</div>
		</div>
		<div class="card py-lg-3">
		  	<div class="card-body text-center">
		    	<h5 class="card-title mb-3">반려된</h5>
			    <a href="#" class="stretched-link" style="font-size:18pt; font-weight: bold;">0</a>
	  		</div>
		</div>
		<div class="card py-lg-3">
		  	<div class="card-body text-center">
		    	<h5 class="card-title mb-3">결재전</h5>
			    <a href="#" class="stretched-link" style="font-size:18pt; font-weight: bold;">0</a>
		  	</div>
		</div>
		<div class="card py-lg-3">
		  	<div class="card-body text-center">
		    	<h5 class="card-title mb-3">수신</h5>
			    <a href="#" class="stretched-link" style="font-size:18pt; font-weight: bold;">0</a>
		  	</div>
		</div>
	</div>
	
	<ul class="list-group list-group-horizontal mt-5">
	  	<li class="list-group-item tabmenu">내가 상신한문서</li>
	  	<li class="list-group-item tabmenu">내가 결제할 문서</li>
	  	<li class="list-group-item tabmenu">최근 결재 의견</li>
	</ul>
	
	<div class="mt-2 tabcontent">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th style="width: 100px;  text-align: center">기안양식</th>
					<th style="text-align: center">기안제목</th>
					<th style="width: 200px;  text-align: center">결제대기자</th>
					<th style="width: 200px; text-align: center">상신일시</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div class="mt-2 tabcontent">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th style="width: 100px;  text-align: center">결재구분</th>
					<th style="width: 100px;  text-align: center">기안양식</th>
					<th style="text-align: center">기안제목</th>
					<th style="width: 200px;  text-align: center">기안자</th>
					<th style="width: 200px; text-align: center">상신일시</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div class="mt-2 tabcontent">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th style="width: 100px;  text-align: center">결재구분</th>
					<th style="width: 100px;  text-align: center">결재처리</th>
					<th style="text-align: center">결재의견</th>
					<th style="width: 200px;  text-align: center">결재자</th>
					<th style="width: 200px; text-align: center">상신일시</th>
				</tr>
			</thead>
		</table>
	</div>
	
</div>