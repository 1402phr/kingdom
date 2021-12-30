package org.cnu.kingdom.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import org.cnu.kingdom.util.*;
import org.cnu.kingdom.vo.*;

public class BoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 총게시글 수 조회 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("bSQL.totalCnt");
	}
	
	// 게시글 조회 전담 처리함수
	public List boardList(PageUtil page) {
		return sqlSession.selectList("bSQL.boardList", page);
	}
	
	// 게시글 상세 정보 조회 전담 처리함수
	public BoardVO boardDetail(int bno) {
		return sqlSession.selectOne("bSQL.boardDetail", bno);
	}
	
	// 첨부파일 저장이름 조회 전담 처리함수
	public List subFileList(int bno) {
		return sqlSession.selectList("bSQL.subFile", bno);
	}
	
	// 게시글 등록  전담 처리함수
	public int addBoard(BoardVO bVO) {
		return sqlSession.insert("bSQL.addBoard", bVO);
	}
	
	// 파일 정보 등록 전담 처리함수
	public int addFile(FileVO fVO) {
		return sqlSession.insert("bSQL.addFile", fVO);
	}
	
	// 첨부파일 삭제 전담 처리함수
	public int delSub(int fno) {
		return sqlSession.update("bSQL.delSub", fno);
	}
	
	// 게시글 삭제 전담 처리함수
	public int boardDel(int bno) {
		return sqlSession.update("bSQL.boardDel", bno);
	}
	
	// 게시글 수정 전담 처리함수
	public int boardEdit(BoardVO bVO) {
		return sqlSession.update("bSQL.editBoard", bVO);
	}
}
