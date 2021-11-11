package com.spring.finalProject.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//==== #32. DAO 선언 ====
@Repository // 원래 bean에 올려주기 위해서 @Component를 써야하는데 @Repository를 쓰기 때문에 자동적으로 bean에 올라간다.
public class OHJDAO implements InterOHJDAO {
	
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

	
	/////////////////////////////////////////////////////////////////////////////////
	// 기본셋팅 끝이다. 여기서부터 개발 시작이다! //
	/////////////////////////////////////////////////////////////////////////////////

	
	// === &56. 글쓰기(파일첨부가 없는 글쓰기) === //
	@Override
	public int boardWrite(BoardVO_OHJ boardvo) {
		int n = sqlsession.insert("ohhj.boardWrite", boardvo);
		return n;
	}


	// === &60. 페이징 처리를 안한 검색어가 없는 전체 글목록 보여주기 === //
	@Override
	public List<BoardVO_OHJ> boardListNoSearch() {
		List<BoardVO_OHJ> boardList = sqlsession.selectList("ohhj.boardListNoSearch");
		return boardList;
	}


	// === &64. 글1개 조회하기 === //
	@Override
	public BoardVO_OHJ getView(Map<String, String> paraMap) {
		BoardVO_OHJ boardvo = sqlsession.selectOne("ohhj.getView", paraMap);
		return boardvo;
	}
	
	
	
	
	
}
