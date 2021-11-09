package com.spring.finalProject.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.finalProject.model.InterKGHDAO;

@Service
public class KGHService implements InterKGHService {

	// === #34. 의존객체 주입하기(DI: Dependency Injection) ===
		@Autowired
		private InterKGHDAO dao;
		// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.model.BoardDAO 의 bean 을  dao 에 주입시켜준다. 
	    // 그러므로 dao 는 null 이 아니다.
	
	@Override
	public List<Map<String, String>> getEmpList() {
		
		// 직원 목록 가져오기(select) 메서드
		List<Map<String, String>> empList = dao.getEmpList();
		return empList;
	}

}
