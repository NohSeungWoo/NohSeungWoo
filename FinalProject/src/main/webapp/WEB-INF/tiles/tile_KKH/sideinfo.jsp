<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- ======= #28. tile2 중 sideinfo 페이지 만들기  ======= --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
	a {
		color:#495057;
	}
	.sidesubmenu {
		font-size:11pt; 
		background-color:#fafafa; 
		list-style: none;
		margin-bottom: 0;	
		padding-bottom: 2px;
	}
	.sidesubmenu li {
		margin : 3px 0;
	}
</style>

<script type="text/javascript">

	$(document).ready(function() {
	
	}); // end of ready(); ---------------------------------

</script>

<nav id="sidebar">
	<div class="list-group-flush">
		<a href="" class="list-group-item list-group-item-action ">기안작성</a>
		<a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="list-group-item list-group-item-action dropdown-toggle">기안함</a>
	  		<ul class="collapse sidesubmenu" id="homeSubmenu" >
	            <li>
	                <a href="#">상신한</a>
	            </li>
	            <li>
	                <a href="#">반려된</a>
	            </li>
	            <li>
	                <a href="#">완료된</a>
	            </li>
	        </ul>
	  	<a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="list-group-item list-group-item-action dropdown-toggle">결재함</a>
	  		<ul class="collapse sidesubmenu" id="pageSubmenu">
	            <li>
	                <a href="#">결재전</a>
	            </li>
	            <li>
	                <a href="#">반려한</a>
	            </li>
	            <li>
	                <a href="#">완료한</a>
	            </li>
	        </ul>
	  <a href="#" class="list-group-item list-group-item-action">수신함</a>
	</div>
</nav>
   