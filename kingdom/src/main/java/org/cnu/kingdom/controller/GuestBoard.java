package org.cnu.kingdom.controller;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 이 클래스는 방명록 관련 요청을 처리할 컨트롤러 클래스
 * @author	전은석
 * @since	2021.12.27
 * @version v.1.0
 * 
 * 			작업이력 ]
 * 				2021.12.27 - 담당자 	: 전은석
 * 							 작업내용 	: 클래스 제작, 리스트보기 요청 처리
 */
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.cnu.kingdom.dao.GuestDao;
import org.cnu.kingdom.dao.MemberDao;
import org.cnu.kingdom.util.*;
import org.cnu.kingdom.vo.*;

@Controller
@RequestMapping("/guestBoard")
public class GuestBoard {
	
	@Autowired
	GuestDao gDao;
	@Autowired
	MemberDao mDao;
	
	@RequestMapping("/gBoardList.cnu")
	public ModelAndView gBoardList(ModelAndView mv, PageUtil page, HttpSession session) {
		// 총 게시글 수 조회
		int total = gDao.getTotalCount();
		page.setPage(page.getNowPage(), total);
		
		String sid = (String)session.getAttribute("SID");
		int cnt = 0 ;
		if(sid != null) {
			cnt = gDao.getIdCnt(sid);
		}
		// 게시글 리스트 조회
		List<BoardVO> list = gDao.getList(page);
		for(BoardVO vo : list) {
			vo.setBody(vo.getBody().replace("\r\n", "<br>"));
		}
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.addObject("CNT", cnt);
		
		mv.setViewName("guestBoard/gBoardList");
		return mv;
	}
	
	/**
	 * 방명록 작성 폼보기 요청 처리함수
	 */
	@RequestMapping("/gBoardWrite.cnu")
	public ModelAndView gBoardWrite(ModelAndView mv, HttpSession session, RedirectView rv) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/kingdom/member/login.cnu");
			mv.setView(rv);
		} else {
			int cnt = gDao.getIdCnt(sid);
			if(cnt == 1) {
				// 이미 작성한 경우
				rv.setUrl("/kingdom/guestBoard/gBoardList.cnu");
				mv.setView(rv);
			} else {
				MemberVO mVO = mDao.getAvt(sid);
				mv.addObject("DATA", mVO);
				mv.setViewName("guestBoard/gBoardWrite");
			}
		}
		
		return mv;
	}
	
	@RequestMapping("/writeProc.cnu")
	public ModelAndView writeProc(ModelAndView mv, BoardVO bVO, HttpSession session, RedirectView rv) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			// 로그인 안한경우
			rv.setUrl("/kingdom/member/login.cnu");
			mv.setView(rv);
		} else {
			// 로그인 한 경우
			
			// 작성한 방명록 조회
			int cnt = gDao.getIdCnt(sid);
			
			if(cnt == 1) {
				rv.setUrl("/kingdom/guestBoard/gBoardList.cnu");
				mv.setView(rv);
			} else {
				
				if(gDao.addContent(bVO) == 1) {
					// 글 등록 성공
					rv.setUrl("/kingdom/guestBoard/gBoardList.cnu");
				} else {
					// 글 등록 실패
					rv.setUrl("/kingdom/guestBoard/gBoardWrite.cnu");
					
				}
				mv.setView(rv);
			}
			
		}
		
		return mv;
	}
}
