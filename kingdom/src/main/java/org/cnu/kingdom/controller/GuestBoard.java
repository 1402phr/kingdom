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

import java.util.List;

import javax.servlet.http.HttpSession;

import org.cnu.kingdom.dao.GuestDao;
import org.cnu.kingdom.util.*;
import org.cnu.kingdom.vo.*;

@Controller
@RequestMapping("/guestBoard")
public class GuestBoard {
	
	@Autowired
	GuestDao gDao;
	
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
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.addObject("CNT", cnt);
		
		mv.setViewName("guestBoard/gBoardList");
		return mv;
	}
}
