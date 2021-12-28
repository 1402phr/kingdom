package org.cnu.kingdom.dao;

import java.util.List;

import org.cnu.kingdom.util.PageUtil;
import org.cnu.kingdom.vo.BoardVO;
/**
 * 방명록 데이터베이스 작업 전담 처리 클래스
 * @author	전은석
 * @since	2021.12.27
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2021.12.27	-	담당자 		: 전은석
 * 								처리내용 	: 클래스제작, 총게시글수 조회
 */
import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

public class GuestDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	/**
	 * 게시글 수 조회 전담 처리함수
	 */
	public int getTotalCount() {
		return sqlSession.selectOne("gSQL.getTotal");
	}
	
	/**
	 * 로그인회원 작성 방명록 수 조회 전담 처리함수
	 */
	public int getIdCnt(String id) {
		return sqlSession.selectOne("gSQL.idCnt", id);
	}
	
	/**
	 * 방명록 리스트 조회 전담 처리함수
	 */
	public List<BoardVO> getList(PageUtil page) {
		return sqlSession.selectList("gSQL.getList", page);
	}
	
	
	/**
	 * 방명록 글 등록 데이터베이스 작업 전담 처리함수
	 */
	public int addContent(BoardVO bVO) {
		return sqlSession.insert("gSQL.addGBoard", bVO);
	}
}
