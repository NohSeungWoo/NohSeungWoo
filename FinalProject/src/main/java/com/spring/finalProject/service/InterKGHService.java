package com.spring.finalProject.service;

import java.util.List;

import com.spring.finalProject.model.EmployeeVO_KGH;

public interface InterKGHService {

	// 직원 목록 가져오기(select) 메서드
	List<EmployeeVO_KGH> getEmpList();

}
