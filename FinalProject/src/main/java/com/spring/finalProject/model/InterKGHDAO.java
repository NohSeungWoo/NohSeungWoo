package com.spring.finalProject.model;

import java.util.List;

public interface InterKGHDAO {
	
	// 직원 목록 가져오기(select) 메서드
	List<EmployeeVO_KGH> getEmpList();

}
