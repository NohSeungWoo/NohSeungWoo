package com.spring.finalProject.model;

import java.util.List;
import java.util.Map;

import com.spring.board.model.BoardCommentVO_OHJ;
import com.spring.board.model.BoardVO_OHJ;

public interface InterOHJDAO {

	// 글쓰기(파일첨부가 없는 글쓰기)
	int boardWrite(BoardVO_OHJ boardvo);

	// 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	List<BoardVO_OHJ> boardListNoSearch();
	
	BoardVO_OHJ getView(Map<String, String> paraMap); // 글1개를 조회 해주는 것
	void addReadCount(String boardSeq); // 글조회수 1증가 하기

	// 1개글 수정하기
	int boardEdit(BoardVO_OHJ boardvo);

	// 1개글 삭제하기
	int boardDel(Map<String, String> paraMap);

	int boardCommentWrite(BoardCommentVO_OHJ commentvo); // 댓글쓰기(tbl_boardComment 테이블에  insert)
	int updateCommentCount(String fk_boardSeq); // tbl_board 테이블에 commentCount 컬럼이 1증가(update)

	// 원게시물에 딸린 댓글들을 조회해오는 것
	List<BoardCommentVO_OHJ> getCommentList(String fk_boardSeq);
	
	
	
	
	
	
	
	

}
