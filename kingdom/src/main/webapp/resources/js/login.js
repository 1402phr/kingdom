$(document).ready(function(){
	
	// 홈버튼 클릭이벤트처리
	$('#btn1').click(function(){
		$(location).attr('href', '/kingdom/');
	});
	
	// 로그인버튼 클릭이벤트처리
	$('#btn2').click(function(){
		// 할일
		// 1. 입력된 아이디와 비밀번호를 읽어온다.
		var sid = $('#id').val();
		var spw = $('#pw').val();
		// 2. 유효성검사를 해서 처리한다.
		if(!(sid && spw)){
			// 둘중 하나라도 입력되지 않은 경우
			return;
		} else {
			// 둘 모두 입력한 경우
			$('#frm').submit();
		}
	});
});