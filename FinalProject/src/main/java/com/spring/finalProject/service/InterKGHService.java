package com.spring.finalProject.service;

import java.util.List;
import java.util.Map;

import com.spring.finalProject.model.DepartmentVO_KGH;
import com.spring.finalProject.model.EmployeeVO_KGH;
import com.spring.finalProject.model.PositionVO_KGH;

public interface InterKGHService {

	// 직원 목록 가져오기(select) 메서드
	List<Map<String, String>> getEmpList();

	// 총 게시물 건수(totalCount) 가져오기(select)
	int getTotalCount(Map<String, String> paraMap);

	// 페이징 처리한 직원 목록 가져오기(검색이 있든지, 검색이 없든지 다 포함된 것)
	List<Map<String, String>> getEmpListWithPaging(Map<String, String> paraMap);

	// 부서목록 가져오기(select)
	List<DepartmentVO_KGH> getDepartmentName();

	// 직급 목록 가져오기(select)
	List<PositionVO_KGH> getPosition();

	// 검색어 결과 조회하기(select)
	List<String> employeeSearch(Map<String, String> paraMap);

	// 로그인 처리 메서드(select)
	EmployeeVO_KGH getLogin(Map<String, String> paraMap);

	// 이메일 중복 여부 검사하기(select)
	boolean emailDuplicateCheck(String email);

	// 새로 생성될 사원번호 조회하기
	String selectEmpId(String departmentno);

	// 직원 정보 등록하기(insert)
	int empRegister(EmployeeVO_KGH emp);

	// 첨부파일과 함께 직원 정보 등록하기(insert)
	int empRegisterWithProfile(EmployeeVO_KGH emp);

	// 특정 회원에 대한 정보 가져오기(select)
	Map<String, String> empListEdit(String employeeID);

	// 직원 정보 수정하기(update)
	int empEdit(EmployeeVO_KGH emp);

}
