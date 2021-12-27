<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<link rel="stylesheet" type="text/css" href="/kingdom/css/w3.css">
<link rel="stylesheet" type="text/css" href="/kingdom/css/kingdom.css">
<script type="text/javascript" src="/kingdom/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/kingdom/js/info.js"></script>
<script type="text/javascript">
	$(function(){
		var sid = '${SID}';
		
		$('.mw600 > div').css('height', '50px').removeClass('w3-margin-top').addClass('w3-input');
		
		// 홈버튼 이벤트처리
		$('#hbtn').click(function(){
			$(location).attr('href', '/kingdom/');
		});
		
		
		$('.member').click(function(){
			/*
			// 동기 처리방식
			var sno = $(this).attr('id');
			$('#mno1').val(sno);
			$('#frm').submit();
			*/
			var sno = $(this).attr('id');
			$('#infoContainer').stop().slideUp(200, function(){
				$('.indata').text('');
				
				$.ajax({
					url: '/kingdom/member/memberInfo.cnu',
					type: 'POST',
					dataType: 'json',
					data: {
						mno: sno
					},
					success: function(obj){
						$('#sname').text(obj.name);
						$('#name').text(obj.name);
						$('#tno').text(obj.mno);
						$('#mno2').val(obj.mno);
						$('#id').text(obj.id);
						$('#mail').text(obj.mail);
						var gender = obj.gen == 'M'? '남 자' : '여 자';
						$('#gen').text(gender);
						$('#jdate').text(obj.sdate);
						$('#avt').attr('src', '/kingdom/img/avatar/' + obj.avatar);
						
						if(sid != obj.id){
							$('#iebtn').parent().css('display', 'none');
						} else {
							$('#iebtn').parent().css('display', 'block');
						}
						
						$('#infoContainer').stop().slideDown(500);
					},
					error: function(){
						alert('### 서버 연결 실패 ###');
					}
				});
			});
			
		});
	});
</script>
</head>
<body>
	<form method="POST" action="/kingdom/member/memberInfo.cls" id="frm">
		<input type="hidden" name="mno" id="mno1">
	</form>
	
	<div class="w3-content w3-center mw800">
		<div class="w3-col">
			<h1 class="w3-center w3-padding w3-deep-purple">회원 리스트</h1>
			<div class="w3-col w3-border-bottom pdb10">
				<span class="w3-cell m2 w3-button w3-small w3-green w3-hover-lime w3-left mt0 btnBox" id="hbtn">Home</span>
			</div>
			<div class="w3-col">
<c:forEach var="data" items="${LIST}" varStatus="st">
				<div class="w3-col m2 w3-button ${COLOR[st.index]} w3-margin-bottom w3-margin-top member" 
						id="${data.mno}">${data.id}</div>
</c:forEach>	
			</div>
		</div>
		<div class="w3-col w3-margin-top" id="infoContainer" style="display: none;">
			<h1 class="w3-teal w3-card-4">[ <span class="indata" id="sname"></span> ] 님 회원 정보</h1>
			<div class="w3-col w3-border-bottom pdb10">
				<span class="w3-cell m2 w3-button w3-small w3-orange w3-hover-deep-orange w3-left mt0 btnBox" id="iebtn">정보수정</span>
				<span class="w3-cell m2 w3-button w3-small w3-red w3-hover-pale-red w3-right mt0 btnBox" id="dbtn">탈 퇴</span>
			</div>
			
			<!-- 회원탈퇴 모달 -->
			<div id="leave" class="w3-modal" style="display: none;">
	  		<div class="w3-modal-content">
				    <header class="w3-container w3-red">
				      <span onclick="document.getElementById('leave').style.display='none'" 
				      class="w3-button w3-display-topright">&times;</span>
				      <h2>Kingdom 회원탈퇴</h2>
				    </header>
				    <div class="w3-container w3-margin-top w3-margin-bottom">
						<form method="POST" action="/kingdom/member/memberDel.cnu" 
								id="dfrm" name="dfrm"
								class="w3-col w3-padding w3-margin-bottom">
							<input type="hidden" name="mno" id="mno2">
							<h2 class="w3-container w3-center w3-padding">Kingdom을 탈퇴하시겠습니까?</h2>
							<label for="pw" class="w3-col m3 w3-text-grey ft18px">비밀번호 : </label>
							<input type="password" id="pw" name="pw" 
									class="w3-col m7 w3-input w3-border">
							<div class="w3-col m2 pdh10">
								<div class="w3-col w3-button w3-medium w3-red w3-hover-orange w3-left mt0" id="del">탈퇴처리</div>
							</div>
						</form>
				    </div>
				  </div>
			</div>
			
			<div class="w3-col w3-card-4 w3-margin-top w3-padding">
				<div class="w3-col">
				<div class="w3-col w250 pd10">
					<div class="w3-border infoAvtBox">
						<img class="infoAvtBox" id="avt">
					</div>
				</div>
					<div class="w3-rest pdr10">
						<div class="w3-col w3-display-container contBox">
							<div class="w3-col w3-display-middle">
								<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">회원번호 : </span><span class="w3-twothird w3-center indata" id="tno"></span></div>
								<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">회원이름 : </span><span class="w3-twothird w3-center indata" id="name"></span></div>
								<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">아 이 디 : </span><span class="w3-twothird w3-center indata" id="id"></span></div>
								<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">회원메일 : </span><span class="w3-twothird w3-center indata" id="mail"></span></div>
								<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">회원성별 : </span><span class="w3-twothird w3-center indata" id="gen"></span></div>
								<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">가 입 일 : </span><span class="w3-twothird w3-center indata" id="jdate"></span></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
			
	<!-- 메세지 확인 모달 -->
<c:if test="${not empty MSG}">
		<div id="id01" class="w3-modal" style="display: block;">
		  <div class="w3-modal-content">
		<c:if test="${MSG ne '수정에 실패했습니다.'}">
		    <header class="w3-container w3-teal">
		      <span onclick="document.getElementById('id01').style.display='none'" 
		      class="w3-button w3-display-topright">&times;</span>
		      <h2>Modal Header</h2>
		    </header>
		</c:if>
		<c:if test="${MSG eq '수정에 실패했습니다.'}">
		    <header class="w3-container w3-red">
		      <span onclick="document.getElementById('id01').style.display='none'" 
		      class="w3-button w3-display-topright">&times;</span>
		      <h2>Modal Header</h2>
		    </header>
		</c:if>
		    <div class="w3-container w3-margin-top w3-margin-bottom">
		      <h4 class="w3-center w3-text-grey w3-margin-top w3-margin-bottom">${MSG}</h4>
		    </div>
		  </div>
		</div>
</c:if>
</body>
</html>