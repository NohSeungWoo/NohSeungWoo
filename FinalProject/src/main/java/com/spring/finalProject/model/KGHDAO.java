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
	public List<DepartmentVO_KGH> getDepartmentName() {
		List<DepartmentVO_KGH> departList = sqlsession.selectList("KangGH.getDepartmentName");
		return departList;
	}

	// === 직급 목록 가져오기(select) === //
	@Override
	public List<PositionVO_KGH> getPosition() {
		List<PositionVO_KGH> positionList = sqlsession.selectList("KangGH.getPosition");
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

	// === 이메일 중복여부 검사하기(select) === //
	@Override
	public boolean emailDuplicateCheck(String email) {
		boolean isExists = false;
		
		String useremail = sqlsession.selectOne("KangGH.emailDuplicateCheck", email);
		
		if(useremail == null) {
			isExists = false;
		}
		else {
			isExists = true;
		}
		
		return isExists;
	}

	// === 새로 생성될 사원번호 조회하기(select) === //
	@Override
	public String selectEmpId(String departmentno) {
		String empId = sqlsession.selectOne("KangGH.selectEmpId", departmentno);
		return empId;
	}

	
	// === 직원 정보 등록하기(insert) === //
	@Override
	public int empRegister(EmployeeVO_KGH emp) {
		int n = sqlsession.insert("KangGH.empRegister", emp);
		return n;
	}

	// === 첨부파일과 함께 직원 정보 등록하기(insert) === //
	@Override
	public int empRegisterWithProfile(EmployeeVO_KGH emp) {
		int n = sqlsession.insert("KangGH.empRegisterWithProfile", emp);
		return n;
	}

	// === 특정 회원에 대한 정보 가져오기(select) === //
	@Override
	public Map<String, String> empListEdit(String employeeID) {
		Map<String, String> map = sqlsession.selectOne("KangGH.empListEdit", employeeID);
		return map;
	}

	// === 직원 정보 수정하기(update) === //
	@Override
	public int empEdit(EmployeeVO_KGH emp) {
		int n = sqlsession.update("KangGH.empEdit", emp);
		return n;
	}

	// === 부서별 인원 가져오기(select) === //
	@Override
	public List<String> getDepartempCnt() {
		List<String> departEmpCnt = sqlsession.selectList("KangGH.getDepartempCnt");
		return departEmpCnt;
	}

	// === 엑셀에 입력할 직원 정보 가져오기 === //
	@Override
	public List<Map<String, String>> excelEmpList(Map<String, String> paraMap) {
		List<Map<String, String>> excelEmpList = sqlsession.selectList("KangGH.excelEmpList", paraMap);
		return excelEmpList;
	}

	
	// === 직원수 가져오기 메서드 === //
	@Override
	public int getEmpCnt() {
		int empCnt = sqlsession.selectOne("KangGH.getEmpCnt");
		return empCnt;
	}

	// === 부서 추가 시 이미 존재하는 부서인지 중복확인 하는 메서드 === //
	@Override
	public boolean departDuplicate(String newDepart) {
		boolean isExists = false;
		int n = sqlsession.selectOne("KangGH.departDuplicate", newDepart);
		
		if(n >= 1) {
			isExists = true;
		}
		else {
			isExists = false;
		}
		
		return isExists;
	}

	// === 해당하는 사원의 번호 존재여부 확인하는 메서드 === //
	@Override
	public boolean isExistsEmpID(String employeeid) {
		boolean isExists = false;
		
		int n = sqlsession.selectOne("KangGH.isExistsEmpID", employeeid);
		
		if(n >= 1) {
			isExists = true;
		}
		else {
			isExists = false;
		}
		
		return isExists;
	}

	// === 부서 새로 추가하기 메서드 === //
	@Override
	public int newDepartAddEnd(Map<String, String> paraMap) {
		int n = sqlsession.insert("KangGH.newDepartAddEnd", paraMap);
		return n;
	}

	// === 해당하는 부서의 부서번호 select 하기 === //
	@Override
	public String getdepartno(Map<String, String> paraMap) {
		String departno = sqlsession.selectOne("KangGH.getdepartmentno", paraMap);
		return departno;
	}

	// 해당하는 사번의 직책 update하기
	@Override
	public int updateManager(Map<String, String> paraMap) {
		int result = sqlsession.update("KangGH.updateManager", paraMap);
		return result;
	}

	// 부서 삭제 및 삭제 부서에 대한 사원정보 변경(update)
	@Override
	public int delDepartEmpUpdate(String departno) {
		int n = sqlsession.update("KangGH.delDepartEmpUpdate", departno);
		return n;
	}

	// === 해당하는 부서에 대한 사원 정보 변경이 성공한 경우 해당 부서 삭제(delete) === //
	@Override
	public int delDepart(String departno) {
		int result = sqlsession.delete("KangGH.delDepart", departno);
		return result;
	}

	
	// === 부서명 수정하기 메서드(update) === //
	@Override
	public int departEditEnd(Map<String, String> paraMap) {
		int n = sqlsession.update("KangGH.departEditEnd", paraMap);
		return n;
	}

	// === 체크박스에 체크된 사원에 대한 부서변경(update) === //
	@Override
	public int changeDepartment(Map<String, Object> paraMap) {
		int n = sqlsession.update("KangGH.changeDepartment", paraMap);
		return n;
	}



}
