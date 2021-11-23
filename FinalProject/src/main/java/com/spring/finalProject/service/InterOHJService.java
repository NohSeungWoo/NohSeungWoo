package com.spring.finalProject.service;

import java.util.List;
import java.util.Map;

import com.spring.board.model.BoardCommentVO_OHJ;
import com.spring.board.model.BoardVO_OHJ;

public interface InterOHJService {

	// 글쓰기(파일첨부가 없는 글쓰기)
	int boardWrite(BoardVO_OHJ boardvo);

	// 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	List<BoardVO_OHJ> boardListNoSearch();

	// 글조회수 증가와 함께 글1개를 조회
	BoardVO_OHJ getView(Map<String, String> paraMap, String login_employeeId);

	// 글조회수 증가는 없고 단순히 글1개 조회
	BoardVO_OHJ getViewWithNoAddCount(Map<String, String> paraMap);

	// 1개글 수정
	int boardEdit(BoardVO_OHJ boardvo);

	// 1개글 삭제
	int boardDel(Map<String, String> paraMap);

	// 댓글쓰기(transaction 처리)
	int boardCommentWrite(BoardCommentVO_OHJ commentvo) throws Throwable;

	// 원게시물에 딸린 댓글들을 조회해오는 것
	List<BoardCommentVO_OHJ> getCommentList(String fk_boardSeq);
	
	
	
	
	
	

}
