package org.cnu.kingdom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.kingdom.vo.MemberVO;
/**
 * 이 클래스는 회원에 관련된 요청을 처리하는 클래스
 * @author 전은석
 * @since	2021/12/23
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2021/12/23	- 	클래스제작
 * 								로그인 폼보기 함수제작
 * 														-	담당자 : 전은석
 */
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/member")
public class Member {
	
	@RequestMapping("/login.cnu")
	public ModelAndView loginForm(ModelAndView mv, RedirectView rv,
									HttpSession session/* , HttpServletResponse resp */) throws ServletException, IOException {
		String view = "member/login";
//		session.setAttribute("SID", "jenny"); // 처리 테스트용 코드
		
		Object sid = session.getAttribute("SID");
		if(sid != null) {
			// 이 경우는 이미 로그인 한 회원이므로 메인페이지로 강제로 돌려보낸다.
//			resp.sendRedirect("/kingdom/");
			rv.setUrl("/kingdom/");
			mv.setView(rv);
		} else {
			mv.setViewName(view); // 뷰를 포워딩 시켜주는 함수
			/*
				참고 ]
					ModelAndView 는 뷰와 데이터를 한꺼번에 관리할 목적으로 만들어진 클래스
					따라서 반환값을 ModelAndView로 사용해도 처리된다.
			 */
		}
		
		return mv;
	}
	
	@RequestMapping("/loginProc.cnu")
	public ModelAndView loginProc(ModelAndView mv, MemberVO mVO /* String id, String pw */) {
		System.out.println("id : " + mVO.getId());
		System.out.println("pw : " + mVO.getPw());
		
		return mv;
	}
	
	@RequestMapping("/logout.cnu")
	public ModelAndView logout(ModelAndView mv, HttpSession session, RedirectView rv) {
		// 세션에 SID로 기억된 데이터를 지운다.
		session.removeAttribute("SID");
		
		// 메인페이지로 다시 보낸다.(redirect)
		rv.setUrl("/kingdom/");
		mv.setView(rv);
		
		return mv;
	}
}
