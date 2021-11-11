package com.spring.finalProject.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.spring.approval.model.ApcategoryVO;

//==== #32. DAO 선언 ====
@Repository // 원래 bean에 올려주기 위해서 @Component를 써야하는데 @Repository를 쓰기 때문에 자동적으로 bean에 올라간다.
public class WHCDAO implements InterWHCDAO {
	
	// === #33. 의존객체 주입하기(DI: Dependency Injection) ===
    // >>> 의존 객체 자동 주입(Automatic Dependency Injection)은
    //     스프링 컨테이너가 자동적으로 의존 대상 객체를 찾아서 해당 객체에 필요한 의존객체를 주입하는 것을 말한다. 
    //     단, 의존객체는 스프링 컨테이너속에 bean 으로 등록되어 있어야 한다. 

    //     의존 객체 자동 주입(Automatic Dependency Injection)방법 3가지 
    //     1. @Autowired ==> Spring Framework에서 지원하는 어노테이션이다. 
    //                       스프링 컨테이너에 담겨진 의존객체를 주입할때 타입을 찾아서 연결(의존객체주입)한다.
   
    //     2. @Resource  ==> Java 에서 지원하는 어노테이션이다.
    //                       스프링 컨테이너에 담겨진 의존객체를 주입할때 필드명(이름)을 찾아서 연결(의존객체주입)한다.
   
    //     3. @Inject    ==> Java 에서 지원하는 어노테이션이다.
    //                       스프링 컨테이너에 담겨진 의존객체를 주입할때 타입을 찾아서 연결(의존객체주입)한다.   
	
	@Resource
	private SqlSessionTemplate sqlsession; // 원격DB remote_finalorauser1 에 연결
	// Type 에 따라 Spring 컨테이너가 알아서 root-context.xml 에 생성된 org.mybatis.spring.SqlSessionTemplate 의 sqlsession bean 을  sqlsession 에 주입시켜준다. 
    // 그러므로 sqlsession 는 null 이 아니다.

	// === 기안양식 카테고리 얻어오기 === //
	@Override
	public List<ApcategoryVO> getApcategoryList() {
		List<ApcategoryVO> apcList = sqlsession.selectList("woohc.getApcategoryList");
		return apcList;
	}

	// === 부서명, 부서번호 받아오기 === //
	@Override
	public List<DepartmentVO_KGH> getDepartment() {
		List<DepartmentVO_KGH> departList = sqlsession.selectList("woohc.getDepartment");
		return departList;
	}

	// 결제선에 이용할 사원 리스트
	@Override
	public List<Map<String, String>> getEmployeeList(Map<String, String> paraMap) {
		List<Map<String, String>> empList = sqlsession.selectList("woohc.getEmployeeList", paraMap);
		return empList;
	}
	
	
	
}
