package com.spring.finalProject.model;

import java.util.List;
import java.util.Map;

public interface InterKGHDAO {
	
	// 직원 목록 가져오기(select) 메서드
	List<Map<String, String>> getEmpList();

}
