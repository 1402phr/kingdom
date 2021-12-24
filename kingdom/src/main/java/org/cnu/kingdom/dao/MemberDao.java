package org.cnu.kingdom.dao;

import java.util.*;

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
	
	// 아이디체크 데이터베이스 작업 전담 처리함수
	public int getIdCount(String id) {
		return sqlSession.selectOne("mSQL.idCheck", id);
	}
	
	// 아바타 리스트 조회 데이터베이스 작업 전담 처리함수
	public List<MemberVO> avtList(){
		return sqlSession.selectList("mSQL.avtList");
	}
	
	// 회원정보조회 전담 처리함수
	public MemberVO getInfo(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.getInfo", mVO);
	}
	
	// 회원추가 데이터베이스작업 전담 처리함수
	public int addMember(MemberVO mVO) {
		return sqlSession.insert("mSQL.addMember", mVO);
	}
	
	// 회원리스트조회 데이터베이스 작업 전담 처리함수
	public List<MemberVO> getList(){
		return sqlSession.selectList("mSQL.selList");
	}
}
