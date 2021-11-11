package com.spring.finalProject.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

// === DAO 선언 === //
@Repository
public class KGHDAO implements InterKGHDAO {

	@Resource
	private SqlSessionTemplate sqlsession;	// 원격 DB에 연결
	
	// 직원 목록 가져오기(select) 메서드
	@Override
	public List<Map<String, String>> getEmpList() {
		List<Map<String, String>> empList = sqlsession.selectList("KangGH.getEmpList");
		return empList;
	}

	// === 총 게시물 건수(totalCount) 가져오기(select) === //	
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("KangGH.getTotalCount", paraMap);
		return totalCount;
	}

	// === 페이징 처리한 직원 목록 가져오기(검색이 있든지, 검색이 없든지 다 포함된 것) === //
	@Override
	public List<Map<String, String>> getEmpListWithPaging(Map<String, String> paraMap) {
		List<Map<String, String>> empList = sqlsession.selectList("KangGH.getEmpListWithPaging", paraMap);
		return empList;
	}

	// === 부서목록 가져오기(select) === //
	@Override
	public List<String> getDepartmentName() {
		List<String> departList = sqlsession.selectList("KangGH.getDepartmentName");
		return departList;
	}

	// === 직급 목록 가져오기(select) === //
	@Override
	public List<String> getPosition() {
		List<String> positionList = sqlsession.selectList("KangGH.getPosition");
		return positionList;
	}

	// === 검색어 결과 조회하기(select) === //
	@Override
	public List<String> employeeSearch(Map<String, String> paraMap) {
		List<String> searchList = sqlsession.selectList("KangGH.employeeSearch", paraMap);
		return searchList;
	}

	// === 로그인 처리 메서드(select) === //
	@Override
	public EmployeeVO_KGH getLogin(Map<String, String> paraMap) {
		EmployeeVO_KGH empvo = sqlsession.selectOne("KangGH.getLogin", paraMap);
		return empvo;
	}

}
