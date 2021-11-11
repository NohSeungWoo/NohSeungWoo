package com.spring.finalProject.model;

import java.util.List;
import java.util.Map;

import com.spring.approval.model.ApcategoryVO;

public interface InterWHCDAO {

	// 기안양식 카테고리 얻어오기
	List<ApcategoryVO> getApcategoryList();

	// 부서명, 부서번호 받아오기
	List<DepartmentVO_KGH> getDepartment();

	// 결제선에 이용할 사원 리스트
	List<Map<String, String>> getEmployeeList(Map<String, String> paraMap);

}
