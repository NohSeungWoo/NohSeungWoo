package com.spring.finalProject.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.finalProject.model.BoardVO_OHJ;
import com.spring.finalProject.service.InterOHJService;

//==== #30. 컨트롤러 선언 ====
@Component
@Controller
public class OHJController {
	
	@RequestMapping(value="/test1.gw")
	public String test1(HttpServletRequest request) {
		
		String name = "이순신"; //데베에서 얻어온거라고 가정함.
		request.setAttribute("name", name);
		
		return "test1"; //뷰단을 넘김.
	//  /WEB-INF/views/test1.jsp 페이지를 만들어야 한다.
	}
	
	@RequestMapping(value="/test4.gw")
	public String test4(HttpServletRequest request) {
		
		String name = "Lee SunSin";
		request.setAttribute("name", name);
		
		return "test4.tiles1";
	//  /WEB-INF/views/tiles1/test4.jsp 파일을 생성한다.
	}
	
	@RequestMapping(value="/test6.gw")
	public String test6(HttpServletRequest request) {
		
		String name = "Eom JungHwa";
		request.setAttribute("name", name);
		
		return "test6.tiles2";
	//  /WEB-INF/views/tiles2/test6.jsp 파일을 생성한다.
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// === #35. 의존객체 주입하기(DI: Dependency Injection) ===
	// ※ 의존객체주입(DI : Dependency Injection) 
	//  ==> 스프링 프레임워크는 객체를 관리해주는 컨테이너를 제공해주고 있다.
	//      스프링 컨테이너는 bean으로 등록되어진 BoardController 클래스 객체가 사용되어질때, 
	//      BoardController 클래스의 인스턴스 객체변수(의존객체)인 BoardService service 에 
	//      자동적으로 bean 으로 등록되어 생성되어진 BoardService service 객체를  
	//      BoardController 클래스의 인스턴스 변수 객체로 사용되어지게끔 넣어주는 것을 의존객체주입(DI : Dependency Injection)이라고 부른다. 
	//      이것이 바로 IoC(Inversion of Control == 제어의 역전) 인 것이다.
	//      즉, 개발자가 인스턴스 변수 객체를 필요에 의해 생성해주던 것에서 탈피하여 스프링은 컨테이너에 객체를 담아 두고, 
	//      필요할 때에 컨테이너로부터 객체를 가져와 사용할 수 있도록 하고 있다. 
	//      스프링은 객체의 생성 및 생명주기를 관리할 수 있는 기능을 제공하고 있으므로, 더이상 개발자에 의해 객체를 생성 및 소멸하도록 하지 않고
	//      객체 생성 및 관리를 스프링 프레임워크가 가지고 있는 객체 관리기능을 사용하므로 Inversion of Control == 제어의 역전 이라고 부른다.  
	//      그래서 스프링 컨테이너를 IoC 컨테이너라고도 부른다.
	
	//  ★IOC(Inversion of Control) 란 ?
	//  ==> 스프링 프레임워크는 사용하고자 하는 객체를 빈형태로 이미 만들어 두고서 컨테이너(Container)에 넣어둔후
	//      필요한 객체사용시 컨테이너(Container)에서 꺼내어 사용하도록 되어있다.
	//      이와 같이 객체 생성 및 소멸에 대한 제어권을 개발자가 하는것이 아니라 스프링 Container 가 하게됨으로써 
	//      객체에 대한 제어역할이 개발자에게서 스프링 Container로 넘어가게 됨을 뜻하는 의미가 제어의 역전 
	//      즉, IOC(Inversion of Control) 이라고 부른다.
	
	
	//  === 느슨한 결합 ===
	//      스프링 컨테이너가 BoardController 클래스 객체에서 BoardService 클래스 객체를 사용할 수 있도록 
	//      만들어주는 것을 "느슨한 결합" 이라고 부른다.
	//      느스한 결합은 BoardController 객체가 메모리에서 삭제되더라도 BoardService service 객체는 메모리에서 동시에 삭제되는 것이 아니라 남아 있다.
	
	// ===> 단단한 결합(개발자가 인스턴스 변수 객체를 필요에 의해서 생성해주던 것)
	// private InterBoardService service = new BoardService(); 
	// ===> BoardController 객체가 메모리에서 삭제 되어지면  BoardService service 객체는 멤버변수(필드)이므로 메모리에서 자동적으로 삭제되어진다.
	
	@Autowired
	private InterOHJService service;
	// Type에 따라 알아서 Bean 을 주입해준다.

	/////////////////////////////////////////////////////////////////////////////////
						// 기본셋팅 끝이다. 여기서부터 개발 시작이다! //
	/////////////////////////////////////////////////////////////////////////////////
	
	
	// === &51. 게시판 글쓰기 폼페이지 요청 === //
	@RequestMapping(value="/boardWrite.gw")
	public ModelAndView requiredLogin_boardWrite(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		mav.setViewName("board/boardWrite.tiles_OHJ");
		//  /WEB-INF/views/tiles_OHJ/board/boardWrite.jsp 파일을 생성한다.
		
		return mav;
	}
	
	
	// === &54. 게시판 글쓰기 완료 요청 === //
	@RequestMapping(value="/boardWriteEnd.gw", method= {RequestMethod.POST})
	public ModelAndView boardWriteEnd(ModelAndView mav, BoardVO_OHJ boardvo) {
		
		// form 태그의 name명과 BoardVO의 필드명이 같다라면, getParameter 사용안하더라도 자동적으로 set되어진다.
	/*	
		System.out.println("확인용 fk_bCategorySeq => " + boardvo.getFk_bCategorySeq());
		System.out.println("확인용 subject => " + boardvo.getSubject());
		System.out.println("확인용 content => " + boardvo.getContent());
	*/	
		
		// **** 크로스 사이트 스크립트 공격에 대응하는 안전한 코드(시큐어 코드) 작성하기 **** // 
		String content = boardvo.getContent();
		content = content.replaceAll("<", "&lt;");
		content = content.replaceAll(">", "&gt;");
		content = content.replaceAll("\r\n", "<br>"); // 입력한 엔터는 <br>처리하기
		boardvo.setContent(content);
		
		int n = service.boardWrite(boardvo); // <== 파일첨부가 없는 글쓰기
		
		mav.setViewName("redirect:/recentList.gw");
		//	/list.gw 페이지로 redirect(페이지이동)해라는 말이다.
		
		return mav;
	}
	
	
	// === &58. 글목록 보기 페이지 요청(최근 게시물) === //
	@RequestMapping(value="/recentList.gw")
	public ModelAndView recentList(ModelAndView mav, HttpServletRequest request) {
		
	//	getCurrentURL(request); // 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출
		
		List<BoardVO_OHJ> boardList = null;
		
		// == 페이징 처리를 안한, 검색어가 없는, 전체 글목록 보여주기 == //
		boardList = service.boardListNoSearch();
				
		mav.addObject("boardList", boardList);
		mav.setViewName("board/recentList.tiles_OHJ");
		//  /WEB-INF/views/tiles_OHJ/board/recentList.jsp 파일을 생성한다.
		
		return mav;
	}
	
	// === &62. 글1개를 보여주는 페이지 요청 === //
	@RequestMapping(value="/boardView.gw")
	public ModelAndView boardView(ModelAndView mav, HttpServletRequest request) {
		
	//	getCurrentURL(request); // 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출
		
		// 조회하고자 하는 글번호 받아오기
		String boardSeq = request.getParameter("boardSeq");
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("boardSeq", boardSeq);
		
		BoardVO_OHJ boardvo = null;
		
		try {
			
			Integer.parseInt(boardSeq); // "하하하호호호", "1", "1324654"
			
			HttpSession session = request.getSession();
			
			
			boardvo = service.getView(paraMap);
			
			
		} catch (NumberFormatException e) {
			// boardvo는 null인 상태 그대로 뷰단으로 넘어간다.
		}
		
		mav.addObject("boardvo", boardvo);
		
		mav.setViewName("board/boardView.tiles_OHJ");
		//  /WEB-INF/views/tiles_OHJ/board/boardView.jsp 파일을 생성한다.
		
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////
	//  === 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 생성 === 
/*	
	public void getCurrentURL(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("goBackURL", MyUtil.getCurrentURL(request));
	}
*/	
	////////////////////////////////////////////////////////////////////////////////
	

	
}
