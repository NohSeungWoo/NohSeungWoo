package com.spring.finalProject.model;

import java.util.List;

import com.spring.approval.model.ApcategoryVO;

public interface InterWHCDAO {

	// 기안양식 카테고리 얻어오기
	List<ApcategoryVO> getApcategoryList();

}
