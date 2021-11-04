<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.net.InetAddress"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ======= #27. tile1 중 header 페이지 만들기 (#26.번은 없다 샘이 장난침.) ======= --%>
<%
   String ctxPath = request.getContextPath();

   // === #172. (웹채팅관련3) === 
   // === 서버 IP 주소 알아오기(사용중인 IP주소가 유동IP 이라면 IP주소를 알아와야 한다.) ===
   InetAddress inet = InetAddress.getLocalHost(); 
   String serverIP = inet.getHostAddress();
   
 // System.out.println("serverIP : " + serverIP);
 // serverIP : 211.238.142.72
   
   // String serverIP = "211.238.142.72"; 만약에 사용중인 IP주소가 고정IP 이라면 IP주소를 직접입력해주면 된다.
   
   // === 서버 포트번호 알아오기   ===
   int portnumber = request.getServerPort();
 // System.out.println("portnumber : " + portnumber);
 // portnumber : 9090
   
   String serverName = "http://"+serverIP+":"+portnumber; 
 // System.out.println("serverName : " + serverName);
 // serverName : http://211.238.142.72:9090 
%>
	
<style type="text/css">
	
</style>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		
		
	});

</script>

</head>
<body>
	<div class="container-fluid"> <!-- 팀장님꺼에서 style="max-width:1600px" 삭제함 -->
		<nav class="navbar navbar-expand-lg navbar-light pt-2" style="background-color: black;">
		
			<!-- Brand/logo --> <!-- Font Awesome 5 Icons -->
			<a class="navbar-brand" href="<%= ctxPath %>/index.gw" style="color: white;"><i class="fas fa-home fa-lg"></i></a>
			
			<span style="font-size:20pt; font-weight: bold; color: white;">게시판</span>
			
			<div>
				<a class="nav-link fas fa-search fa-lg" style="color:white" href="<%= ctxPath%>"></a>
				<a class="nav-link far fa-bell fa-lg" style="color:white" href="<%= ctxPath%>"></a>
				<a class="nav-link far fa-user fa-lg" style="color:white" href="<%= ctxPath%>"></a>
			</div>
			
		</nav>
	</div>
	<hr style="margin:0">