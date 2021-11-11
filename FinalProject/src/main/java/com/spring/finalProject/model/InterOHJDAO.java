package com.spring.finalProject.model;

import java.util.List;
import java.util.Map;

public interface InterOHJDAO {

	// 글쓰기(파일첨부가 없는 글쓰기)
	int boardWrite(BoardVO boardvo);

	// 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기
	List<BoardVO> boardListNoSearch();

	// 글1개를 조회 해주는 것
	BoardVO getView(Map<String, String> paraMap);
	
	
	
	
	
	
	
	

}
