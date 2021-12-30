package org.cnu.kingdom.service;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.cnu.kingdom.vo.MemberVO;
import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;

import org.cnu.kingdom.vo.*;
import org.cnu.kingdom.util.*;

//@Aspect
//@Component
public class LoggingExec {
	
	private static Logger recordLog = LoggerFactory.getLogger(LoggingExec.class);
	
	@Pointcut("execution(* org.cnu.kingdom.controller.Member.loginProc(..))")
	public void recordLogin() {
		System.out.println("### recordLogin() ###");
	}
	
	@Before("recordLogin()")
//	@Before("execution(* org.cnu.kingdom.controller.Member.loginProc(..))")
	public boolean recrd(JoinPoint join) {
		MemberVO mVO = (MemberVO)join.getArgs()[0];
		mVO.setCnt(10);
		return false;
	}
	
//	@After("execution(* org.cnu.kingdom.controller.Member.loginProc(..))")
	@After("recordLogin()")
	public boolean rec(JoinPoint join) {
		System.out.println(join.getTarget().getClass().getName().substring(join.getTarget().getClass().getName().lastIndexOf('.')));
		MemberVO mVO = (MemberVO)join.getArgs()[0];
		System.out.println("service mVO cnt : " + mVO.getCnt());
		if(mVO.getCnt() == 1) {
			recordLog.info(mVO.getId() + " ] ***** Login *****");
		}
		
		return true;
	}
	
	@Before("execution(* org.cnu.kingdom.controller.*.*(..))")
	public void getPage(JoinPoint join) {
		
	}
}
