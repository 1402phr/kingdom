$(document).ready(function(){
	$('#rbtn').click(function(){
		// 리셋버튼이 클릭된 경우
		// $('#frm').reset();
		document.frm.reset();
	});
	
	// 홈버튼 클릭이벤트
	$('#hbtn').click(function(){
		$(location).attr('href', '/kingdom/main.cnu');
	});
	
	$('#idck').click(function(){
		// 할일
		// 1. 아이디 읽고
		var sid = $('#id').val();
		// 2. 아이디 유효성검사하고
		if(!sid) {
			alert('아이디를 입력하세요!');
			return;
		}
		
		// 3. 서버에 아이디보내고 검사하고 ==> 비동기 통신 처리
		$.ajax({
			url: '/kingdom/member/idCheck.cnu',
			type: 'post',
			dataType: 'json',
			data: {
				id: sid
			},
			success: function(obj){
				if(obj.msg == 'OK'){
					// 4-1. 사용가능한 아이디인 경우 처리
					$('#idmsg').removeClass('w3-text-red').addClass('w3-text-green').html('* 사용가능한 아이디입니다.');
				} else {
					// 4-2. 사용 불가능한 아이디인 겨우 처리
					$('#idmsg').removeClass('w3-text-green').addClass('w3-text-red').html('# 사용 불가능한 아이디입니다. #');
				}
				$('#idmsg').css('display', 'block');
			},
			error: function(){
				alert('# 통신 실패 #');
			}
		});
	});
});