package org.cnu.kingdom.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.kingdom.dao.MemberDao;
import org.cnu.kingdom.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/member")
public class Member {
	
	@Autowired
	MemberDao mDao;
	
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
	public ModelAndView loginProc(ModelAndView mv, MemberVO mVO, 
									HttpSession session, RedirectView rv 
									/* String id, String pw */) {
		int cnt = mDao.getLogin(mVO);
		
		if(cnt == 1) {
			// 로그인 성공처리
			session.setAttribute("SID", mVO.getId());
			// main 페이지로 redirect
			rv.setUrl("/kingdom/");
		} else {
			// 로그인 실패
			rv.setUrl("/kingdom/member/login.cnu");
		}
		// 뷰 심고
		mv.setView(rv);
		
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
	
	// 회원가입 폼보기요청
	@RequestMapping("/join.cnu")
	public ModelAndView join(ModelAndView mv, HttpSession session, RedirectView rv) {
		// 할일
		// 1. 로그인 체크하고
		String sid = (String) session.getAttribute("SID");
		/*
			세션에는 데이터를 기억시킬때 Object 타입으로 기억된다.
			따라서 꺼내서 사용할 때는 반드시 강제 형변환해서 사용한다.
		 */
		if(sid != null) {
			// 2. 로그인 되어있으면 메인으로 보내고
			rv.setUrl("/kingdom/main.cnu");
			mv.setView(rv);
		} else {
			List<MemberVO> list = mDao.avtList();
			mv.addObject("LIST", list);
			// 3. 로그인 안되어있으면 뷰 부르고
			mv.setViewName("member/join");
		}
		
		// 4. 반환값 반환하고
		return mv;
	}
	
	@RequestMapping("/idCheck.cnu")
	@ResponseBody
	public MemberVO idCheck(MemberVO mVO) {
		// 디비작업 결과받고
		int cnt = mDao.getIdCount(mVO.getId());
		
		if(cnt == 0) {
			// 사용가능한 아이디인경우
			mVO.setMsg("OK");
		} else {
			// 사용불가능한 아이디인 경우
			mVO.setMsg("NO");
		}
		return mVO;
	}
	
	@RequestMapping("/joinProc.cnu")
	public ModelAndView joinProc(ModelAndView mv, MemberVO mVO, 
								HttpSession session, RedirectView rv) {
		int cnt = mDao.addMember(mVO);
		
		System.out.println("cnt : " + cnt);
		
		if(cnt == 1) {
			// 가입 성공한 경우
			session.setAttribute("SID", mVO.getId()); // 로그인 처리
			rv.setUrl("/kingdom/");
		} else {
			// 실패한 경우
			rv.setUrl("/kingdom/member/join.cnu");
		}
		
		mv.setView(rv);
		return mv; 
	}
	
	@RequestMapping("/memberList.cnu")
	public ModelAndView getMemberList(ModelAndView mv, MemberVO mVO) {
		List list = mDao.getList();
		
		mv.addObject("LIST", list);
		mv.addObject("COLOR", getColorList());
		
		mv.setViewName("member/memberList");
		return mv;
	}
	
	@RequestMapping("/memberInfo.cnu")
	@ResponseBody
	public MemberVO getInfo(ModelAndView mv, MemberVO mVO) {
		mVO = mDao.getInfo(mVO);
		/*
		System.out.println(mVO);
		mv.addObject("DATA", mVO);
		mv.setViewName("member/memberInfo");
		*/
		mVO.setSdate();
		return mVO;
	}
	
	public ArrayList getColorList() {
		ArrayList list = new ArrayList();
		list.add("w3-red");
		list.add("w3-pink");
		list.add("w3-purple");
		list.add("w3-deep-purple");
		list.add("w3-blue");
		list.add("w3-cyan");
		list.add("w3-aqua");
		list.add("w3-green");
		list.add("w3-light-green");
		list.add("w3-lime");
		list.add("w3-yellow");
		list.add("w3-amber");
		list.add("w3-orange");
		list.add("w3-deep-orange");
		list.add("w3-black");
		list.add("w3-dark-grey");
		list.add("w3-grey");
		list.add("w3-light-grey");
		list.add("w3-blue-grey");
		list.add("w3-brown");
		list.add("w3-pale-red");
		list.add("w3-sand");
		list.add("w3-pale-yellow");
		list.add("w3-khaki");
		list.add("w3-pale-blue");
		list.add("w3-light-blue");		
		return list;
	}
}
