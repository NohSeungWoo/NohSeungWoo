package com.spring.finalProject.service;

import com.spring.finalProject.model.BoardVO;

public interface InterOHJService {

	// 글쓰기(파일첨부가 없는 글쓰기)
	int boardWrite(BoardVO boardvo);

}
