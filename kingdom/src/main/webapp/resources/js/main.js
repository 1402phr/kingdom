$(document).ready(function(){
	$('.w3-button').click(function(){
		var tmp = $(this).attr('id');
/*
		alert(tmp);
*/
		var spath = '';
		switch(tmp){
			case 'lbtn':
				spath = '/kingdom/member/login.cnu';
				break;
			case 'obtn':
				spath = '/kingdom/member/logout.cnu';
				break;
			case 'jbtn':
				spath = '/kingdom/member/join.cnu';
				break;
			case 'mlbtn':
				spath = '/kingdom/member/memberList.cnu';
				break;
			case 'ibtn':
				$('#frm').submit();
				return;
				break;
			case 'gbtn':
				spath = '/kingdom/guestBoard/guestBoard.cnu';
				break;
			case 'irbtn':
				spath = '/kingdom/reBoard/initRBD.cnu';
				break;
			case 'rbtn':
				spath = '/kingdom/reBoard/reBoardList.cnu';
				break;
			case 'sbtn':
				spath = '/kingdom/survey/surveyInfo.cnu';
				break;
		}
		$(location).attr('href', spath);
	});
	
});
