package org.cnu.kingdom.dao;

import org.cnu.kingdom.vo.*;
import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

public class MemberDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
		자동의존주입(@Autowired) 는 반드시 빈처리되어있는 객체에 한해서만 
		사용가능하다.
	 */
	
	public int getLogin(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.login", mVO);
	}
}
