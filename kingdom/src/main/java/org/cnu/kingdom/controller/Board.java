package org.cnu.kingdom.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

import org.cnu.kingdom.dao.*;
import org.cnu.kingdom.vo.*;
import org.cnu.kingdom.util.*;

/**
 * 게시판 관련 요청 처리 컨트롤러
 * @author	전은석
 * @since 	2021.12.29
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2021.12.29 	-	담당자 : 전은석
 * 								작업내용	:
 * 												클래스제작, 리스트 폼보기, 글쓰기 폼보기
 *
 */

@Controller
@RequestMapping("/board")
public class Board {
	@Autowired
	BoardDao bDao;
	
	@RequestMapping("/boardList.cnu")
	public ModelAndView getList(ModelAndView mv, PageUtil page) {
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping("/boardWrite.cnu")
	public ModelAndView boardWrite(ModelAndView mv, HttpSession session, RedirectView rv) {
		
		mv.setViewName("board/boardWrite");
		return mv;
	}
}
