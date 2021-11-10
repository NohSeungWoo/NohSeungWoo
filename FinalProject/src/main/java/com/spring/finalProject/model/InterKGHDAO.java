package com.spring.finalProject.model;

import java.util.List;
import java.util.Map;

public interface InterKGHDAO {
	
	// 직원 목록 가져오기(select) 메서드
	List<Map<String, String>> getEmpList();

	// 총 게시물 건수(totalCount) 가져오기(select)
	int getTotalCount(Map<String, String> paraMap);

	// 페이징 처리한 직원 목록 가져오기(검색이 있든지, 검색이 없든지 다 포함된 것) //
	List<Map<String, String>> getEmpListWithPaging(Map<String, String> paraMap);

	// 부서목록 가져오기(select)
	List<String> getDepartmentName();

	// 직급 목록 가져오기(select)
	List<String> getPosition();

	// 검색어 결과 조회하기(select)
	List<String> employeeSearch(Map<String, String> paraMap);

}
