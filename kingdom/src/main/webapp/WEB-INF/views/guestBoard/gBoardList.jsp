<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/kingdom/css/w3.css">
<link rel="stylesheet" type="text/css" href="/kingdom/css/user.css">
<script type="text/javascript" src="/kingdom/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/kingdom/js/w3color.js"></script>
<style type="text/css"></style>
<script type="text/javascript">
	$(document).ready(function(){
		/*
		// 각각의 태그에 이벤트를 적용시키는 방법(<== 해당 태그를 선택해서 처리하는 방법)
		$('#hbtn').click(function(){
			$(location).attr('href', '/cafe/main.cafe');
		});
		*/
		

		if('${PAGE.startPage}' == 1){
			$('#startbtn').toggleClass('pbutton');
			$('#startbtn').toggleClass('w3-light-grey');
		}
		if('${PAGE.endPage}' == '${PAGE.totalPage}'){
			$('#endbtn').toggleClass('pbutton');
			$('#endbtn').toggleClass('w3-light-grey');
		}
		
		/* 페이지 이동 이벤트 처리 */
		$('.pbutton').click(function(){
			var sPage = $(this).html();
			var ltxt = $('#startbtn').html();
			var rtxt = $('#endbtn').html();
			if(sPage == '${PGE.nowPage}') return;
			
			if(sPage == ltxt){
				sPage = '${PAGE.startPage - 1}';
				$('#nowPage').val(sPage);
			} else if(sPage == rtxt){
				sPage = '${PAGE.endPage + 1}';
				$('#nowPage').val(sPage);
			} else {
				$('#nowPage').val(sPage);
			}
			$('#frm').submit();
		});
		
		
		// 여러태그를 동시에 이벤트를 처리하는 방법
		$('.w3-button').click(function(){
			// 이렇게 선택하면 클래스에 w3-button 이 추가되어있는 태그는 모두 선택하고
			// 선택된 태그들에 클릭이벤트를 등록하게 된다.
			
			// 할일
			// 어떤태그가 클릭되었는지 먼저 알아낸다.
			var tid = $(this).attr('id');
			var url = '';
			switch(tid){
			case 'hbtn':
				url = '/kingdom/main.cnu';
				break;
			case 'lbtn':
				url = '/kingdom/member/login.cnu';
				break;
			case 'jbtn':
				url = '/kingdom/member/join.cnu';
				break;
			case 'outbtn':
				url = '/kingdom/member/logout.cnu';
				break;
			case 'wbtn':
				url = '/kingdom/guestBoard/gBoardWrite.cnu';
				break;
			}
			$(location).attr('href', url);
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw650 w3-margin-top">
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-green w3-center w3-padding mg0">방명록 작성</h1>
			<nav class="w3-bar w3-pale-yellow">
				<div class="w3-col w150 w3-left w3-button w3-small w3-amber" id="hbtn">home</div>
<c:if test="${not empty SID}">
	<c:if test="${CNT == 0}">
				<div class="w3-col w150 w3-left w3-button w3-small w3-lime w3-right" id="wbtn">글작성</div>
	</c:if>
				<div class="w3-col w150 w3-left w3-button w3-small w3-light-green w3-right" id="outbtn">logout</div>
</c:if>
<c:if test="${empty SID}">
				<div class="w3-col w150 w3-left w3-button w3-small w3-deep-orange w3-right" id="lbtn">login</div>
				<div class="w3-col w150 w3-left w3-button w3-small w3-yellow w3-right" id="jbtn">join</div>
</c:if>
		</nav>
		</header>
		
<c:forEach var="data" items="${LIST}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
			<div class="w3-col box120 pdAll10 w3-border-right">
				<img src="/kingdom/img/avatar/${data.avatar}" class="inblock avtBox100 w3-border w3-border-grey">
			</div>
			<div class="w3-rest w3-padding">
				<div class="w3-col w3-border-bottom">
					<span class="w3-text-left mgb10 ft10"><b>${data.id}</b></span>
					<span class="w3-right mgb10 ft10">${data.sdate}</span>
				</div>
				<div class="w3-col w3-margin-top">
					<span class="w3-col w3-padding ft12">${data.body}</span>
				</div>
			</div>
		</div>
</c:forEach>

	<!-- Page 처리 태그 -->
		<div class="w3-center">
			<div class="w3-bar w3-border w3-margin-top w3-margin-bottom">
			  <div class="w3-bar-item w3-hover-blue pbutton" id="startbtn">&laquo;</div>
		<c:forEach var="page" begin="${PAGE.startPage}" end="${PAGE.endPage}">
			  <c:if test="${PAGE.nowPage eq page }">
			  	<div class="w3-bar-item w3-lime w3-hover-blue pbutton">${page}</div>
			  </c:if>
			  <c:if test="${PAGE.nowPage ne page }">
			  	<div class="w3-bar-item w3-hover-blue pbutton">${page}</div>
			  </c:if>
		</c:forEach>
			  <div class="w3-bar-item w3-hover-blue pbutton" id="endbtn">&raquo;</div>
			</div>
		</div>
		
		<form method="POST" action="/kingdom/guestBoard/gBoardList.cnu" id="frm" name="frm">
			<input type="hidden" name="nowPage" id="nowPage" value="${PAGE.nowPage}">
		</form>
		
	</div>
</body>
</html>