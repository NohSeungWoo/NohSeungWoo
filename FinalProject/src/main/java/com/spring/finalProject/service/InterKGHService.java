package com.spring.finalProject.service;

import java.util.List;
import java.util.Map;

public interface InterKGHService {

	// 직원 목록 가져오기(select) 메서드
	List<Map<String, String>> getEmpList();

}
