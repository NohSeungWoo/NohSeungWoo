package com.spring.finalProject.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.model.BoardCommentVO_OHJ;
import com.spring.board.model.BoardVO_OHJ;
import com.spring.finalProject.common.MyUtil_KGH;
import com.spring.finalProject.model.EmployeeVO_KGH;
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
		
		getCurrentURL(request); // 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출
		
		mav.setViewName("board/boardWrite.tiles_OHJ");
		//  /WEB-INF/views/tiles_OHJ/board/boardWrite.jsp 파일을 생성한다.
		
		return mav;
	}
	
	
	// === &54. 게시판 글쓰기 완료 요청 === //
	@RequestMapping(value="/boardWriteEnd.gw", method= {RequestMethod.POST})
	public ModelAndView boardWriteEnd(ModelAndView mav, BoardVO_OHJ boardvo) {
		
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
	public ModelAndView recentList(ModelAndView mav, HttpServletRequest request) { /* 뿌잉) 그룹웨어이므로 requiredLogin_을 추가해야한다. */
		
		getCurrentURL(request); // 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출
		
		List<BoardVO_OHJ> boardList = null;
		
		// == 페이징 처리를 안한, 검색어가 없는, 전체 글목록 보여주기 == //
	//	boardList = service.boardListNoSearch();
/*		
		// === &102. 페이징 처리를 안한, 검색어가 있는, 전체 글목록 보여주기 시작 === //
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String bCategory = request.getParameter("bCategory");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		System.out.println("확인용 fromDate : " + fromDate);
		System.out.println("확인용 toDate : " + toDate);
		System.out.println("확인용 bCategory : " + bCategory);
		System.out.println("확인용 searchType : " + searchType);
		System.out.println("확인용 searchWord : " + searchWord);
		
		if(fromDate == null) { // 맨처음 목록보기를 통해 들어가는 경우
			fromDate = ""; // 2021-08-23 // '숫자개월수만큼 빼준 날짜인 add_months(sysdate,-3) 로 검색'하려했으나 유효성검사 했으므로 안해도된다.
		}
		if(toDate == null) { // 맨처음 목록보기를 통해 들어가는 경우
			toDate = "";   // 2021-11-23 // 'sysdate 로 검색'하려했으나 유효성검사 했으므로 안해도된다.
		}
		////////////////////////////////////////////////////
		if(bCategory == null) { // 맨처음 목록보기를 통해 들어가는 경우
			bCategory = "0";
		}
		if( !"0".equals(bCategory)&&!"1".equals(bCategory)&&!"2".equals(bCategory)&&!"3".equals(bCategory) ) { // 유저가 게시판종류를 장난친 경우
			bCategory = "";
		}
		////////////////////////////////////////////////////
		if(searchType == null || (!"subject".equals(searchType)&&!"name".equals(searchType))) { // 맨처음 목록보기를 통해 들어가는 경우, 유저가 장난친 경우
			searchType = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) { // 맨처음 목록보기를 통해 들어가는 경우, 검색어자체가 ⓐ없거나 ⓑ있는데 공백인 경우
			searchWord = "";
		}
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("bCategory", bCategory);
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		
		boardList = service.boardListSearch(paraMap);
		
		// 아래는 검색시 검색조건 및 값들을 유지시키기 위한 것임.
		if(!"".equals(fromDate) && !"".equals(toDate)) {
			mav.addObject("fromDate", fromDate);
			mav.addObject("toDate", toDate);
		}
//		if(!"0".equals(bCategory) && !"".equals(bCategory)) {
			mav.addObject("bCategory", bCategory); // 주소창에 게시판종류를 장난친경우를 recentList.jsp에서 처리하기위해, if의 조건없이 넘김.
//		}
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("searchType", searchType);
			mav.addObject("searchWord", searchWord);
		}
		// === &102. 페이징 처리를 안한, 검색어가 있는, 전체 글목록 보여주기 끝 === //
*/		
		/////////////////////////////////////////////////////////////////
		/* === &69. 글조회수증가는 반드시 목록보기에 와서 해당 글제목을 클릭했을 경우에만 증가되고,
			웹브라우저에서 새로고침(F5)을 했을 경우에는 증가가 되지 않도록 해야 한다.
			이것을 하기 위해서는 session 을 사용하여 처리하면 된다. === */
		HttpSession session = request.getSession();
		session.setAttribute("readCountPermission", "yes"); // 조회수 증가를 허락하겠다.
		/*	
			session에 readCountPermission의 value값은 "yes"이다.
			이 값은 웹브라우저에서 주소창에 "/recentList.gw" 이라고 입력해야만 얻어올 수 있다.
		*/
		/////////////////////////////////////////////////////////////////
		
		
		// === &114. 페이징 처리를 한, 검색어가 있는, 전체 글목록 보여주기 시작 === //
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String bCategory = request.getParameter("bCategory");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
	/*	
		System.out.println("확인용 fromDate : " + fromDate);
		System.out.println("확인용 toDate : " + toDate);
		System.out.println("확인용 bCategory : " + bCategory);
		System.out.println("확인용 searchType : " + searchType);
		System.out.println("확인용 searchWord : " + searchWord);
	*/	
		if(fromDate == null) { // 맨처음 목록보기를 통해 들어가는 경우
			// 2021-08-23 // '숫자개월수만큼 빼준 날짜인 add_months(sysdate,-3) 로 검색'하려했으나 유효성검사 해서 값이 넘어왔다.
			
			// 3달전 날짜 구하기
			fromDate = getDate("threeMonthsAgo");
		}
		if(toDate == null) { // 맨처음 목록보기를 통해 들어가는 경우
			// 2021-11-23 // 'sysdate 로 검색'하려했으나 유효성검사 해서 값이 넘어왔다.
			
			// 오늘 날짜 구하기
			toDate = getDate("today");
		}
		////////////////////////////////////////////////////
		if(bCategory == null) { // 맨처음 목록보기를 통해 들어가는 경우
			bCategory = "0";
		}
		if( !"0".equals(bCategory)&&!"1".equals(bCategory)&&!"2".equals(bCategory)&&!"3".equals(bCategory) ) { // 유저가 게시판종류를 장난친 경우
			bCategory = "";
		}
		////////////////////////////////////////////////////
		if(searchType == null || (!"subject".equals(searchType)&&!"name".equals(searchType))) { // 맨처음 목록보기를 통해 들어가는 경우, 유저가 장난친 경우
			searchType = "";
		}
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) { // 맨처음 목록보기를 통해 들어가는 경우, 검색어자체가 ⓐ없거나 ⓑ있는데 공백인 경우
			searchWord = "";
		}
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("bCategory", bCategory);
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		
		
		// 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
		// ★총 게시물 건수(totalCount)는 검색조건이 있을 때와 없을 때로 나뉘어진다.
		int totalCount = 0; 		// 총 게시물 건수
		int sizePerPage = 5; 		// 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0; 	// 현재 보여주는 페이지 번호로서, 초기치로는 1페이지로 설정함.
		int totalPage = 0; 			// 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바에 쓰임)
		
		int startRno = 0; 			// 시작 행번호
		int endRno = 0; 			// 끝 행번호
		
		// 총 게시물 건수(totalCount)
		totalCount = service.getTotalCount(paraMap);
	//	System.out.println("~~~~ 확인용 totalCount : " + totalCount);
		
		// 만약에 총 게시물 건수(totalCount)가 27개 이라면
		// 27/5 = 5.xx 이므로
		// 총 페이지수(totalPage)는 6개가 되어야 한다.
		totalPage = (int) Math.ceil((double)totalCount/sizePerPage);
		// (double)27/5 ==> 5.4 ==> Math.ceil(5.4) ==> 6.0 ==> (int)6.0 ==> 6
		
		if(str_currentShowPageNo == null) {
			// 게시판에 보여지는 초기화면
			currentShowPageNo = 1;
		}
		else {
			try {
				currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				if(currentShowPageNo < 1 || currentShowPageNo > totalPage) { // get방식이라 유저가 장난친 경우("-321", "2136351")
					currentShowPageNo = 1;
				}
			} catch(NumberFormatException e) { // get방식이라 유저가 장난친 경우("하하호호")
				currentShowPageNo = 1;
			}
		}
		
		// **** 가져올 게시글의 범위를 구한다.(공식임!!!) **** 
	    /*
	           currentShowPageNo      startRno     endRno
	          --------------------------------------------
	               1 page        ===>     1           5
	               2 page        ===>     6          10
	               3 page        ===>    11          15
	               4 page        ===>    16          20
	               ......                ...         ...
	    */
		
		startRno = ( (currentShowPageNo-1) * sizePerPage ) + 1;
		endRno = startRno + sizePerPage - 1 ;
		
		paraMap.put("startRno", String.valueOf(startRno)); // 사칙연산으로 계산했으니 int인 것을 Map<String,String>에 맞게 string타입으로 바꿔줌.
		paraMap.put("endRno", String.valueOf(endRno));
		
		boardList = service.boardListSearchWithPaging(paraMap);
		// 페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한것)
		
		
		// 아래는 검색시 검색조건 및 값들을 유지시키기 위한 것임.
		if(!"".equals(fromDate) && !"".equals(toDate)) {
			mav.addObject("fromDate", fromDate);
			mav.addObject("toDate", toDate);
		}
//		if(!"0".equals(bCategory) && !"".equals(bCategory)) {
			mav.addObject("bCategory", bCategory); // 주소창에 게시판종류를 장난친경우를 recentList.jsp에서 처리하기위해, if의 조건없이 넘김.
//		}
		if(!"".equals(searchType) && !"".equals(searchWord)) {
			mav.addObject("searchType", searchType);
			mav.addObject("searchWord", searchWord);
		}
		
		
		// === &121. 페이지바 만들기 === //
		int blockSize = 10;
		// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수 이다.
	    /*
	                       1  2  3  4  5  6  7  8  9 10 [다음][마지막]  -- 1개블럭
	         [맨처음][이전]  11 12 13 14 15 16 17 18 19 20 [다음][마지막]  -- 1개블럭
	         [맨처음][이전]  21 22 23
	    */
		
		int loop = 1;
		/*
        	loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.
		*/
		
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;
	    // *** !! 공식이다. !! *** //
		
		/*
	       1  2  3  4  5  6  7  8  9  10  -- 첫번째 블럭의 페이지번호 시작값(pageNo)은 1 이다.
	       11 12 13 14 15 16 17 18 19 20  -- 두번째 블럭의 페이지번호 시작값(pageNo)은 11 이다.
	       21 22 23 24 25 26 27 28 29 30  -- 세번째 블럭의 페이지번호 시작값(pageNo)은 21 이다.
	       
	       currentShowPageNo         pageNo
	      ----------------------------------
	            1                      1 = ((1 - 1)/10) * 10 + 1
	            2                      1 = ((2 - 1)/10) * 10 + 1
	            3                      1 = ((3 - 1)/10) * 10 + 1
	            4                      1
	            5                      1
	            6                      1
	            7                      1 
	            8                      1
	            9                      1
	            10                     1 = ((10 - 1)/10) * 10 + 1
	           
	            11                    11 = ((11 - 1)/10) * 10 + 1
	            12                    11 = ((12 - 1)/10) * 10 + 1
	            13                    11 = ((13 - 1)/10) * 10 + 1
	            14                    11
	            15                    11
	            16                    11
	            17                    11
	            18                    11 
	            19                    11 
	            20                    11 = ((20 - 1)/10) * 10 + 1
	            
	            21                    21 = ((21 - 1)/10) * 10 + 1
	            22                    21 = ((22 - 1)/10) * 10 + 1
	            23                    21 = ((23 - 1)/10) * 10 + 1
	            ..                    ..
	            29                    21
	            30                    21 = ((30 - 1)/10) * 10 + 1
	    */
		
		String pageBar = "<ul class='pagination justify-content-center' style='margin-top: 50px;'>";
		String url = "recentList.gw";
		
		// === [맨처음][이전] 만들기 ===
		if(pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link' href='"+url+"?fromDate="+fromDate+"&toDate="+toDate+"&bCategory="+bCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo=1'>[맨처음]</a></li>"; // span태그의 text-dark를 처리안하니 기본으로 파란글씨가 나온다.
			pageBar += "<li class='page-item'><a class='page-link' href='"+url+"?fromDate="+fromDate+"&toDate="+toDate+"&bCategory="+bCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
		}
		
		while( !(loop > blockSize || pageNo > totalPage) ){ // !(while문의 탈출조건)
			
			if(pageNo == currentShowPageNo) {
				pageBar += "<li class='page-item active'><a class='page-link'>"+pageNo+"<a></li>";
			}
			else {
				pageBar += "<li class='page-item'><a class='page-link' href='"+url+"?fromDate="+fromDate+"&toDate="+toDate+"&bCategory="+bCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'><span class='text-dark'>"+pageNo+"</span></a></li>";
			}
			
			loop++;
			pageNo++;
			
		}// end of while-----------------------------
		
		
		// === [다음][마지막] 만들기 ===
		if(pageNo <= totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href='"+url+"?fromDate="+fromDate+"&toDate="+toDate+"&bCategory="+bCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>";
			pageBar += "<li class='page-item'><a class='page-link' href='"+url+"?fromDate="+fromDate+"&toDate="+toDate+"&bCategory="+bCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";
		}
		
		
		pageBar += "</ul>";
		
		mav.addObject("pageBar", pageBar);
		
		
		/* === &123. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후, 
				사용자가 목록보기 버튼을 클릭했을때 돌아갈 페이지를 알려주기 위해
				현재 페이지 주소를 뷰단으로 넘겨준다. === */
		String gobackURL = MyUtil_KGH.getCurrentURL(request);	
	//	System.out.println("~~~~ 확인용 recentList.gw의 gobackURL : " + gobackURL);
		/*
			~~~~ 확인용 recentList.gw의 gobackURL : /recentList.gw																							// 처음 목록보기로 들어간 경우
			~~~~ 확인용 recentList.gw의 gobackURL : /recentList.gw?fromDate=2021-08-26&toDate=2021-11-26&bCategory=0&searchType=subject&searchWord=			// 바로 검색버튼 누른 경우
			~~~~ 확인용 recentList.gw의 gobackURL : /recentList.gw?fromDate=2021-08-18&toDate=2021-11-24&bCategory=1&searchType=name&searchWord=%EC%9C%A0	// 모든 조건 검색한 누른 경우
		*/
		
		mav.addObject("gobackURL", gobackURL);
		
		// === 페이징 처리를 한, 검색어가 있는, 전체 글목록 보여주기 끝 === //
		
		mav.addObject("totalCount", totalCount); // 게시글 총 몇 건인지 보여주는 용도
		
		mav.addObject("boardList", boardList);
		mav.setViewName("board/recentList.tiles_OHJ");
		//  /WEB-INF/views/tiles_OHJ/board/recentList.jsp 파일을 생성한다.
		
		return mav;
	}
	
	// === &62. 글1개를 보여주는 페이지 요청 === //
	@RequestMapping(value="/boardView.gw")
	public ModelAndView boardView(ModelAndView mav, HttpServletRequest request) { /* 뿌잉) 그룹웨어이므로 requiredLogin_을 추가해야한다. */
		
		getCurrentURL(request); // 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출
		
		// 조회하고자 하는 글번호 받아오기
		String boardSeq = request.getParameter("boardSeq");
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("boardSeq", boardSeq);
		
		
		// 글목록에서 검색되어진 글내용일 경우
		// 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글, 다음글이 나오도록 하기 위한 것이다. 
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String bCategory = request.getParameter("bCategory");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		// get방식으로 장난치게 되면 아래의 boardvo의 결과가 조회되지 않으므로, 해당하는 글이 없다고 알려준다. 따라서 getParameter한 값을 형식에 맞게 처리는 안해줘도된다. 그냥 null인지 아닌지만 판별한다. 글이 보여지지 않으므로 gobackURL이 이상하게 갈 것에 대한 걱정은 안해도 된다.
		if(fromDate == null) {
			fromDate = getDate("threeMonthsAgo");
		}
		if(toDate == null) {
			toDate = getDate("today");
		}
		if(bCategory == null) {
			bCategory = "0";
		}
		if(searchType == null) {
			searchType = "";
		}
		if(searchWord == null) {
			searchWord = "";
		}
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("bCategory", bCategory);
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		
		mav.addObject("fromDate", fromDate);
		mav.addObject("toDate", toDate);
		mav.addObject("bCategory", bCategory);
		mav.addObject("searchType", searchType);
		mav.addObject("searchWord", searchWord);
		
		/////////////////////////////////////////////////////////
		// === &125. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
		//           사용자가 목록보기 버튼을 클릭했을 때 돌아갈 페이지를 알려주기 위해
		//           현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = request.getParameter("gobackURL");
	//	System.out.println("~~~~ 확인용 boardView.gw의 gobackURL => " + gobackURL);
		/*
			~~~~ 확인용 boardView.gw의 gobackURL => /recentList.gw
			~~~~ 확인용 boardView.gw의 gobackURL => /recentList.gw?fromDate=2021-08-26&toDate=2021-11-26&bCategory=3&searchType=name&searchWord=%EC%9C%A0
			~~~~ 확인용 boardView.gw의 gobackURL => /recentList.gw?fromDate=2021-08-26&toDate=2021-11-26&bCategory=3&searchType=name&searchWord=%EC%9C%A0&currentShowPageNo=10
			
			// &126-4의 확인용
			~~~~ 확인용 boardView.gw의 gobackURL => /recentList.gw?fromDate=2021-08-26 toDate=2021-11-24 bCategory=0 searchType=subject searchWord=글 currentShowPageNo=2
		*/
	/*	
		// 이 부분은 그냥 boardView.jsp에서 검색된목록으로가기 버튼을 클릭할때 replace하도록 함.
		// &126-4(강사님은 없음). : 2021-11-05 블로그 참조해서 gobackURL 페이지 주소 올바르게 하기
		if(gobackURL != null && gobackURL.contains(" ")) { // gobackURL에 공백(" ")이 포함되었더라면
			gobackURL = gobackURL.replaceAll(" ", "&");
			// 이전글제목, 다음글제목을 클릭했을때 돌아갈 페이지 주소를 올바르게 만들어주기 위해서 한 것임.
		//	System.out.println("~~~~ 확인용 gobackURL => " + gobackURL);
			// ~~~~ 확인용 gobackURL => /recentList.gw?fromDate=2021-08-26&toDate=2021-11-24&bCategory=0&searchType=subject&searchWord=글&currentShowPageNo=2
		}
	*/	
		mav.addObject("gobackURL", gobackURL);
		/////////////////////////////////////////////////////////		
		
		BoardVO_OHJ boardvo = null;
		
		try {
			
			Integer.parseInt(boardSeq); // "하하하호호호", "1", "1324654"
			
			HttpSession session = request.getSession();
			EmployeeVO_KGH loginuser = (EmployeeVO_KGH) session.getAttribute("loginuser");
			
			String login_employeeId = null;
			
			if(loginuser != null) { // 로그인된 경우
				login_employeeId = loginuser.getEmployeeid();
				// login_employeeId 는 로그인 되어진 사용자의 userid 이다.
			}
			
			/* === &68. 글1개를 보여주는 페이지 요청은 select와 함께
				DML문(지금은 글조회수 증가인 update문)이 포함되어져 있다.
				이럴경우 웹브라우저에서 페이지 새로고침(F5)을 했을때 DML문이 실행되어 매번 글조회수 증가가 발생한다.
				그래서 우리는 웹브라우저에서 페이지 새로고침(F5)을 했을때는 단순히 select만 해주고, 
				DML문(지금은 글조회수 증가인 update문)은 실행하지 않도록 해주어야한다. ===
			*/
			
			// 먼저 &69에서 세션을 저장해야함.
			if("yes".equals(session.getAttribute("readCountPermission"))) {
				// 글목록보기를 클릭한 다음에 특정글을 조회해온 경우이다.
				
				boardvo = service.getView(paraMap, login_employeeId);
				// 글조회수 증가와 함께 글1개를 조회
				
				session.removeAttribute("readCountPermission");
				// session에 저장된 readCountPermission 삭제함.
			}
			else {
				// 웹브라우저에서 새로고침(F5)을 클릭한 경우이다.
				
				boardvo = service.getViewWithNoAddCount(paraMap);
				// 글조회수 증가는 없고 단순히 글1개 조회
			}
			
			
		} catch (NumberFormatException e) {
			// boardvo는 null인 상태 그대로 뷰단으로 넘어간다.
		}
		
		mav.addObject("boardvo", boardvo);
		
		mav.setViewName("board/boardView.tiles_OHJ");
		//  /WEB-INF/views/tiles_OHJ/board/boardView.jsp 파일을 생성한다.
		
		return mav;
	}
	
	
	
	// === &71. 글수정 페이지 요청 === //
	@RequestMapping(value="/boardEdit.gw")
	public ModelAndView requiredLogin_boardEdit(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		getCurrentURL(request); // AOP에 gobackURL저장하므로 원래는 이 부분 안했는데, 로그인 실패시에 gobackURL을 저장해야하므로 이 부분을 꼭 해줘야한다! // 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출
		
		
		// == 글1개 조회와 동일함 시작 ============================================
		// 수정하고자 하는 글번호 받아오기
		String boardSeq = request.getParameter("boardSeq");
		
		// 수정완료시 페이지 돌아갈때,
		// 글목록에서 검색되어진 글내용일 경우
		// 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글, 다음글이 나오도록 하기 위한 것이다. 
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String bCategory = request.getParameter("bCategory");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		// 수정해야할 글1개 내용 가져오기
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("boardSeq", boardSeq);
		
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("bCategory", bCategory);
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		
		mav.addObject("fromDate", fromDate);
		mav.addObject("toDate", toDate);
		mav.addObject("bCategory", bCategory);
		mav.addObject("searchType", searchType);
		mav.addObject("searchWord", searchWord);
		
		/////////////////////////////////////////////////////////
		// 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
		// 사용자가 목록보기 버튼을 클릭했을 때 돌아갈 페이지를 알려주기 위해
		// 현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = request.getParameter("gobackURL");
	//	System.out.println("~~~~ 확인용 boardView.gw의 gobackURL => " + gobackURL);
		// ~~~~ 확인용 boardView.gw의 gobackURL => /recentList.gw?fromDate=2021-08-26 toDate=2021-11-24 bCategory=0 searchType=subject searchWord=글 currentShowPageNo=2
		
		mav.addObject("gobackURL", gobackURL);
		/////////////////////////////////////////////////////////	
		// == 글1개 조회와 동일함 끝 ============================================
		
		BoardVO_OHJ boardvo = service.getViewWithNoAddCount(paraMap);
		
		// **** 크로스 사이트 스크립트 공격에 대응하는 안전한 코드(시큐어 코드) 해제하기 **** // 
		String content = boardvo.getContent();
		content = content.replaceAll("<br>", "\r\n"); // <br>은 엔터로 처리하기
		boardvo.setContent(content);
		
		HttpSession session = request.getSession();
		EmployeeVO_KGH loginuser = (EmployeeVO_KGH) session.getAttribute("loginuser");
		
		if( !loginuser.getEmployeeid().equals(boardvo.getFk_employeeId()) ) {
			String message = "다른 사용자의 글은 수정이 불가합니다.";
			String loc = request.getContextPath()+"/boardView.gw?boardSeq="+boardvo.getBoardSeq()+"&fromDate="+fromDate+"&toDate="+toDate+"&bCategory="+bCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&gobackURL="+gobackURL; // 로그인실패 후 성공했을때 history.back()하면 로그인창이 뜨므로, 글1개 보기 페이지로 이동하도록 바꿔줘야함!
			
			mav.addObject("message", message);
			mav.addObject("loc", loc);
			mav.setViewName("msg");
		}
		else {
			// 자신의 글을 수정하는 경우 : 가져온 글1개를 글수정 View단에 보내준다.
			mav.addObject("boardvo", boardvo);
			mav.setViewName("board/boardEdit.tiles_OHJ");
		}
		
		return mav;
	}
	
	
	// === &72. 글수정 페이지 완료하기 === //
	@RequestMapping(value="/boardEditEnd.gw", method= {RequestMethod.POST})
	public ModelAndView boardEditEnd(ModelAndView mav, BoardVO_OHJ boardvo, HttpServletRequest request) {
		
		// **** 크로스 사이트 스크립트 공격에 대응하는 안전한 코드(시큐어 코드) 작성하기 **** // 
		String content = boardvo.getContent();
		content = content.replaceAll("<", "&lt;");
		content = content.replaceAll(">", "&gt;");
		content = content.replaceAll("\r\n", "<br>"); // 입력한 엔터는 <br>처리하기
		boardvo.setContent(content);
		
		int n = service.boardEdit(boardvo);
		
		if(n==1) {
			mav.addObject("message", "글 수정 성공!!");
		}
		else {
			mav.addObject("message", "글 수정 실패..");
		}
		
		// == 글1개 조회와 동일함 시작 ============================================
		// 수정완료시 페이지 돌아갈때,
		// 글목록에서 검색되어진 글내용일 경우
		// 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글, 다음글이 나오도록 하기 위한 것이다. 
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String bCategory = request.getParameter("bCategory");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		/////////////////////////////////////////////////////////
		// 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
		// 사용자가 목록보기 버튼을 클릭했을 때 돌아갈 페이지를 알려주기 위해
		// 현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = request.getParameter("gobackURL");
	//	System.out.println("~~~~ 확인용 boardView.gw의 gobackURL => " + gobackURL);
		// ~~~~ 확인용 boardView.gw의 gobackURL => /recentList.gw?fromDate=2021-08-26 toDate=2021-11-24 bCategory=0 searchType=subject searchWord=글 currentShowPageNo=2
		
		/////////////////////////////////////////////////////////	
		// == 글1개 조회와 동일함 끝 ============================================
		
		mav.addObject("loc", request.getContextPath()+"/boardView.gw?boardSeq="+boardvo.getBoardSeq()+"&fromDate="+fromDate+"&toDate="+toDate+"&bCategory="+bCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&gobackURL="+gobackURL);		
		
		mav.setViewName("msg");
		
		return mav;
	}	
	
	
	// === &76. 글삭제 페이지 요청 === //
	@RequestMapping(value="/boardDel.gw")
	public ModelAndView requiredLogin_boardDel(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		getCurrentURL(request); // 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출
		
		
		// == 글1개 조회와 동일함 시작 ============================================
		// 삭제하고자 하는 글번호 받아오기
		String boardSeq = request.getParameter("boardSeq");
		
		// 삭제완료시 페이지 돌아갈때,
		// 글목록에서 검색되어진 글내용일 경우
		// 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글, 다음글이 나오도록 하기 위한 것이다. 
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String bCategory = request.getParameter("bCategory");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		// 삭제해야할 글1개 내용 가져와서, 글쓴이를 비교해서 다른 사람이 쓴 글은 삭제가 불가하도록 해야 한다.
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("boardSeq", boardSeq);
		
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		paraMap.put("bCategory", bCategory);
		paraMap.put("searchType", searchType);
		paraMap.put("searchWord", searchWord);
		
		mav.addObject("fromDate", fromDate);
		mav.addObject("toDate", toDate);
		mav.addObject("bCategory", bCategory);
		mav.addObject("searchType", searchType);
		mav.addObject("searchWord", searchWord);
		
		/////////////////////////////////////////////////////////
		// 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
		// 사용자가 목록보기 버튼을 클릭했을 때 돌아갈 페이지를 알려주기 위해
		// 현재 페이지 주소를 뷰단으로 넘겨준다.
		String gobackURL = request.getParameter("gobackURL");
	//	System.out.println("~~~~ 확인용 boardView.gw의 gobackURL => " + gobackURL);
		// ~~~~ 확인용 boardView.gw의 gobackURL => /recentList.gw?fromDate=2021-08-26 toDate=2021-11-24 bCategory=0 searchType=subject searchWord=글 currentShowPageNo=2
		
		mav.addObject("gobackURL", gobackURL);
		/////////////////////////////////////////////////////////	
		// == 글1개 조회와 동일함 끝 ============================================
		
		BoardVO_OHJ boardvo = service.getViewWithNoAddCount(paraMap);
		// 글조회수 증가는 없고 단순히 글1개 조회
		
		HttpSession session = request.getSession();
		EmployeeVO_KGH loginuser = (EmployeeVO_KGH) session.getAttribute("loginuser");
		
		if( !loginuser.getEmployeeid().equals(boardvo.getFk_employeeId()) ) {
			String message = "다른 사용자의 글은 삭제가 불가합니다.";
			String loc = request.getContextPath()+"/boardView.gw?boardSeq="+boardvo.getBoardSeq()+"&fromDate="+fromDate+"&toDate="+toDate+"&bCategory="+bCategory+"&searchType="+searchType+"&searchWord="+searchWord+"&gobackURL="+gobackURL; // 로그인실패 후 성공했을때 history.back()하면 로그인창이 뜨므로, 글1개 보기 페이지로 이동하도록 바꿔줘야함!
			
			mav.addObject("message", message);
			mav.addObject("loc", loc);
		}
		else {
			// 자신의 글을 삭제하는 경우
			int n = service.boardDel(paraMap);
			
			if(n==1) {
				mav.addObject("message", "글 삭제 성공!!");
				mav.addObject("loc", request.getContextPath()+"/recentList.gw");
			}
			else {
				mav.addObject("message", "글 삭제 실패..");
				mav.addObject("loc", request.getContextPath()+"/boardView.gw?boardSeq="+boardvo.getBoardSeq()); // 새로고침을 하게되면 boardDel.gw로 계속 들어오게되므로, 글 1개보기 페이지로 가도록 함.
			}
		}
		
		mav.setViewName("msg");
		
		return mav;
	}
	
	

	// === &84. 댓글쓰기(Ajax 로 처리) === //
	@ResponseBody
	@RequestMapping(value="/boardCommentWrite.gw", method = {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String boardCommentWrite(BoardCommentVO_OHJ commentvo) {
		// 댓글쓰기에 첨부파일이 없는 경우
		
		// **** 크로스 사이트 스크립트 공격에 대응하는 안전한 코드(시큐어 코드) 작성하기 **** // 
		String content = commentvo.getContent();
		content = content.replaceAll("<", "&lt;");
		content = content.replaceAll(">", "&gt;");
	//	content = content.replaceAll("\r\n", "<br>"); // 입력한 엔터는 <br>처리하기 -> 댓글쓰기의 내용은 input태그라서 엔터가 안된다.
		commentvo.setContent(content);
		
		int n = 0;
		
		try {
			// 댓글쓰기(insert) 및 원게시물(tbl_board 테이블)의 댓글의 개수 증가(update 1씩 증가)하기
			n = service.boardCommentWrite(commentvo);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n", n);
		
		return jsonObj.toString();
	}

	
	// === &90. 원게시물에 딸린 댓글들을 조회해오기(Ajax로 처리) === //
	@ResponseBody
	@RequestMapping(value="/readComment.gw", method = {RequestMethod.GET}, produces="text/plain;charset=UTF-8")
	public String readComment(HttpServletRequest request) {
		
		String fk_boardSeq = request.getParameter("fk_boardSeq");
		
		List<BoardCommentVO_OHJ> commentList = service.getCommentList(fk_boardSeq);
		
		JSONArray jsonArr = new JSONArray(); // []
		
		if(commentList != null) { // 해당하는 댓글이 존재하지 않을 수도 있음. 댓글이 존재할경우 실행함.
			for(BoardCommentVO_OHJ bcmtvo: commentList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("content", bcmtvo.getContent());
				jsonObj.put("positionName", bcmtvo.getPositionName());
				jsonObj.put("name", bcmtvo.getName());
				jsonObj.put("regDate", bcmtvo.getRegDate());
				
				jsonArr.put(jsonObj);
			}
		}
		
		return jsonArr.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// -- 어떤 것을 보여주는 페이지들은 getCurrentURL(request); 를 써줘야한다. --
	////////////////////////////////////////////////////////////////////////////////
	//  === 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 생성 === 
	public void getCurrentURL(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("goBackURL", MyUtil_KGH.getCurrentURL(request));
	}
	////////////////////////////////////////////////////////////////////////////////
	// === fromDate와 toDate를 각각 디폴트인 3달전날짜, 오늘날짜로 구해주는 메소드 ===
	public String getDate(String requiredTime) { // threeMonthsAgo 또는 today // requiredTime에는 3달전인지, 지금인지 알려주는 값이 들어옴.
		
		/* 
		    Date 클래스 보다 조금더 향상시켜서 나온 것이 Calendar 클래스이다.
		        간단한 날짜표현에는 Date 클래스를 사용하는 것이 더 나을 수 있으며,
		        두개 날짜사이의 날짜연산을 할 경우에는 메소드기능이 더 많이 추가된 
		    Calendar 클래스를 사용하는 것이 나을 수 있다.
		*/ 
		Calendar currentDate = Calendar.getInstance(); // 현재날짜와 시간
		
		if("threeMonthsAgo".equals(requiredTime)) {
			currentDate.add(Calendar.MONTH, -3); // 3달 전 날짜와 시간
		}
		
		int year = currentDate.get(Calendar.YEAR);
		
		int month = currentDate.get(Calendar.MONTH)+1; // 1~12
		String strMonth = (month<10)?"0"+month:String.valueOf(month); // 01~12
		
		// 아래의 Calendar.DATE 와 Calendar.DAY_OF_MONTH 는 같은 것이다.
	    int day = currentDate.get(Calendar.DATE);
	    String strDay = day<10?"0"+day:String.valueOf(day);
	    
	    String date = year + "-" + strMonth + "-" + strDay;
	//  System.out.println("확인용(requiredTime 날짜) : " + date);
	    /*
		    확인용(requiredTime 날짜) : 2021-08-27
		    확인용(requiredTime 날짜) : 2021-11-27
	    */
	    
	    return date;
	}
	

	
}
