package com.spring.finalProject.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.finalProject.model.DepartmentVO_KGH;
import com.spring.finalProject.model.EmployeeVO_KGH;
import com.spring.finalProject.model.InterKGHDAO;
import com.spring.finalProject.model.PositionVO_KGH;

@Service
public class KGHService implements InterKGHService {

	// === #34. 의존객체 주입하기(DI: Dependency Injection) ===
	@Autowired
	private InterKGHDAO dao;
	// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.model.BoardDAO 의 bean 을  dao 에 주입시켜준다. 
    // 그러므로 dao 는 null 이 아니다.
	
	@Override
	public List<Map<String, String>> getEmpList() {
		
		// === 직원 목록 가져오기(select) 메서드 === //
		List<Map<String, String>> empList = dao.getEmpList();
		return empList;
	}

	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		
		// === 총 게시물 건수(totalCount) 가져오기(select) === //
		int totalCount = dao.getTotalCount(paraMap);
		return totalCount;
	}

	@Override
	public List<Map<String, String>> getEmpListWithPaging(Map<String, String> paraMap) {
		// === 페이징 처리한 직원 목록 가져오기(검색이 있든지, 검색이 없든지 다 포함된 것) === //
		List<Map<String, String>> empList = dao.getEmpListWithPaging(paraMap);
		return empList;
	}

	@Override
	public List<DepartmentVO_KGH> getDepartmentName() {
		// === 부서목록 가져오기(select) === //
		List<DepartmentVO_KGH> departList = dao.getDepartmentName();
		return departList;
	}

	@Override
	public List<String> getPosition() {
		// === 직급 목록 가져오기(select) === //
		List<String> positionList = dao.getPosition();
		return positionList;
	}

	@Override
	public List<String> employeeSearch(Map<String, String> paraMap) {
		// === 검색어 결과 조회하기(select) === //
		List<String> searchList = dao.employeeSearch(paraMap);
		return searchList;
	}

	@Override
	public EmployeeVO_KGH getLogin(Map<String, String> paraMap) {
		// === 로그인 처리 메서드(select) === //
		EmployeeVO_KGH empvo = dao.getLogin(paraMap);
		return empvo;
	}

}
