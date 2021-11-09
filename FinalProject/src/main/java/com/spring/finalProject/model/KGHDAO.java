package com.spring.finalProject.model;

import java.util.List;

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
	public List<EmployeeVO_KGH> getEmpList() {
		List<EmployeeVO_KGH> empList = sqlsession.selectList("KangGH.getEmpList");
		
		return empList;
	}

}
