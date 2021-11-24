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
	List<DepartmentVO_KGH> getDepartmentName();

	// 직급 목록 가져오기(select)
	List<PositionVO_KGH> getPosition();

	// 검색어 결과 조회하기(select)
	List<String> employeeSearch(Map<String, String> paraMap);

	// 로그인 처리 메서드(select)
	EmployeeVO_KGH getLogin(Map<String, String> paraMap);

	// 이메일 중복여부 검사하기(select)
	boolean emailDuplicateCheck(String email);

	// 새로 생성될 사원번호 조회하기(select)
	String selectEmpId(String departmentno);

	// 직원 정보 등록하기(insert)
	int empRegister(EmployeeVO_KGH emp);

	// 첨부파일과 함께 직원 정보 등록하기(insert)
	int empRegisterWithProfile(EmployeeVO_KGH emp);

	// 특정 회원에 대한 정보 가져오기(select)
	Map<String, String> empListEdit(String employeeID);

	// 직원 정보 수정하기(update)
	int empEdit(EmployeeVO_KGH emp);

	// 부서별 인원 가져오기(select)
	List<String> getDepartempCnt();

	// 엑셀에 입력할 직원 정보 가져오기
	List<Map<String, String>> excelEmpList(Map<String, String> paraMap);

	// 직원수 가져오기 메서드
	int getEmpCnt();

	// 부서 추가 시 이미 존재하는 부서인지 중복확인 하는 메서드
	boolean departDuplicate(String newDepart);

	// 해당하는 사원의 번호 존재여부 확인하는 메서드
	boolean isExistsEmpID(String employeeid);

	// 부서 새로 추가하기 메서드
	int newDepartAddEnd(Map<String, String> paraMap);

	// 해당하는 부서의 부서번호 select 하기
	String getdepartno(Map<String, String> paraMap);

	// 해당하는 사번의 직책 update하기
	int updateManager(Map<String, String> paraMap);

	// 부서 삭제 및 삭제 부서에 대한 사원정보 변경(update)
	int delDepartEmpUpdate(String departno);

	// 해당하는 부서에 대한 사원 정보 변경이 성공한 경우 해당 부서 삭제
	int delDepart(String departno);

	// 부서명 수정하기 메서드
	int departEditEnd(Map<String, String> paraMap);

	// 체크박스에 체크된 사원에 대한 부서변경(update)
	int changeDepartment(Map<String, Object> paraMap);

	

}
