package com.spring.finalProject.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.finalProject.common.FileManager;
import com.spring.finalProject.common.MyUtil_KGH;
import com.spring.finalProject.model.DepartmentVO_KGH;
import com.spring.finalProject.model.EmployeeVO_KGH;
import com.spring.finalProject.model.PositionVO_KGH;
import com.spring.finalProject.service.InterKGHService;

@Controller
public class KGHController {
	
	@Autowired
	private InterKGHService service;
	
	@Autowired
	private FileManager FileManager;
	
	@RequestMapping(value = "/login.gw")
	public ModelAndView login(ModelAndView mav) {
		
		mav.setViewName("main/login.tiles1");
		
		return mav;
	}

	
	// === 로그인 처리 메서드 === //
	@RequestMapping(value = "/loginEnd.gw", method = {RequestMethod.POST})
	public ModelAndView loginEnd(HttpServletRequest request, ModelAndView mav) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Map<String, String> paraMap = new HashedMap<>();
		paraMap.put("email", email);
		paraMap.put("password", password);
		
		// === 로그인 처리 메서드 === //
		EmployeeVO_KGH loginuser = service.getLogin(paraMap);
		
		if(loginuser == null) {
			// 로그인에 실패한 경우
			String message = "이메일 또는 암호가 틀립니다.";
			String loc = "javascript:history.back()";
			
			mav.addObject("message", message);	// request.setAttribute("message", message);
			mav.addObject("loc", loc);			// request.setAttribute("loc", loc);
			
			mav.setViewName("msg");				// return "msg";	
		}
		else {
			if("1".equals(loginuser.getRetire())) {	// 로그인 한 지 1년이 경과한 경우
				String message = "귀하는 퇴사한 직원으로 로그인이 불가합니다.";
	            String loc = request.getContextPath() + "/index.gw";
	            // 원래는 위와같이 index.action 이 아니라 휴면인 계정을 풀어주는 페이지로 잡아주어야 한다.
	            
	            mav.addObject("message", message);
	            mav.addObject("loc", loc);
	            
	            mav.setViewName("msg");
	        //  /WEB-INF/views/msg.jsp 파일을 생성한다.
			}
			else {
				HttpSession session = request.getSession();
				// 메모리에 생성되어져 있는 session을 불러오는 것이다.
				
				session.setAttribute("loginuser", loginuser);
				// session(세션)에 로그인 되어진 사용자 정보인 loginuser 을 키이름을 "loginuser" 으로 저장시켜두는 것이다.
				
				String goBackURL = (String)session.getAttribute("goBackURL");
				// 예를 들면 goBackURL은 shop/prodView.up?pnum=58 이거나
				// null 이다.
				
				if(goBackURL != null) {
					mav.setViewName("redirect:/" + goBackURL);	// return "redirect:/" + goBackURL;
					
					session.removeAttribute("goBackURL");	// 세션에서 반드시 제거해주어야 한다.
				}
				else {
					mav.setViewName("redirect:/index.gw");	// return "redirect:/index.gw"
				}
				
			}
		}
		return mav;
	}
	
	
	// === #50. 로그아웃 처리하기 === //
	@RequestMapping(value="/logout.gw")
	public ModelAndView logout(ModelAndView mav, HttpServletRequest request) {
	   
	   HttpSession session = request.getSession();
	   
	   String goBackURL = (String) session.getAttribute("goBackURL");
	   
	   session.invalidate();
	   
	   String message = "로그아웃 되었습니다.";
	   
	   String loc = "";
	   if(goBackURL != null) {
	      loc = request.getContextPath() + goBackURL;
	   }
	   else {
	      loc = request.getContextPath() + "/index.gw";
	   }
	   
	   mav.addObject("message", message); 
	   mav.addObject("loc", loc);         
	   mav.setViewName("msg");
	   
	   return mav;
	}
	
	
	// === 직원 목록 보기 메서드 === //
	@RequestMapping(value = "admin/empList.gw")
	public ModelAndView empList(ModelAndView mav, HttpServletRequest request) {
		
		List<Map<String, String>> empList = null;
		
		// 직원목록 가져오기 메서드
		// empList = service.getEmpList();
		
		HttpSession session = request.getSession();
		
		String department = (String)session.getAttribute("department");
		String position = (String) session.getAttribute("position");
		String searchEmp = (String) session.getAttribute("searchEmp");
		
		System.out.println(department);
		System.out.println(position);
		// System.out.println(searchEmp);
		
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		
		if(department == null || "전체".equals(department)) {
			department = "";
		}
		
		if(position == null || "전체".equals(position)) {
			position = "";
	    }
		
		if(searchEmp == null || "".equals(searchEmp) || searchEmp.trim().isEmpty() ) {
			searchEmp = "";
	    }
		
		Map<String,String> paraMap = new HashMap<>();
	    paraMap.put("department", department);
	    paraMap.put("position", position);
	    paraMap.put("searchEmp", searchEmp);
		
	    // 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
	    // 총 게시물 건수(totalCount)는 검색조건이 있을 때와 없을 때로 나뉘어진다.
	    int totalCount = 0;			// 총 게시물 건수
	    int sizePerPage = 5;		// 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0;	// 현재 보여주는 페이지 번호로서, 초기값은 1 페이지로 설정해야 한다.
	    int totalPage = 0;			// 총 페이지 수(웹브라우저 상에서 보여줄 총 페이지 개수, 페이지바)
	    
	    int startRno = 0;			// 시작 행번호
	    int endRno = 0;				// 끝 행번호
		
		// === 총 게시물 건수(totalCount) 가져오기(select) === //
		totalCount = service.getTotalCount(paraMap);
		// System.out.println("~~~ 확인용 totalCount : " + totalCount);
		// ~~~ 확인용 totalCount : 6
		
		// 총 게시물 건수(totalCount)가 127개 일 경우
	    // 총 페이지 수(totalPage)는 13개 되어야 한다.
	    totalPage = (int)Math.ceil((double)totalCount/sizePerPage);	// (double)127 / 10 => 12.7 => Math.ceil(12.7) => 13.0 => (int)13.0
	    
	    if(str_currentShowPageNo == null) {
	    	// 게시판에 보여지는 초기화면
	    	currentShowPageNo = 1; 
	    }
	    else {
	    	try {
	    		currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
			
	    		if(currentShowPageNo < 1 || currentShowPageNo > totalPage) {
	    			currentShowPageNo = 1;
	    		}
	    	} catch (NumberFormatException e) {
				currentShowPageNo = 1;
			}
	    }
		
		// **** 가져올 게시글의 범위를 구한다.(공식임!!!) **** 
        /*
            currentShowPageNo      startRno     endRno
           --------------------------------------------
               1 page        ===>    1           10
               2 page        ===>    11          20
               3 page        ===>    21          30
               4 page        ===>    31          40
               ......                ...         ...
        */
		
	    startRno = ( (currentShowPageNo - 1) * sizePerPage ) + 1;
	    endRno = startRno + sizePerPage - 1;

	    paraMap.put("startRno", String.valueOf(startRno));
	    paraMap.put("endRno", String.valueOf(endRno));
		
		// 페이징 처리한 직원 목록 가져오기(검색이 있든지, 검색이 없든지 다 포함된 것)
		empList = service.getEmpListWithPaging(paraMap);
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
		if(!"".equals(searchEmp) && !"".equals(searchEmp)) {
			mav.addObject("paraMap", paraMap);
		}
		
		// === 페이지바 만들기 === //
		int blockSize = 10;
		// blockSize는 1개 블럭(토막)당 보여지는 페이지 번호의 개수이다.
		/*
			  	  		  1  2  3  4  5  6  7  8  9 10 [다음][마지막]  -- 1개블럭
			[맨처음][이전]  11 12 13 14 15 16 17 18 19 20 [다음][마지막]  -- 1개블럭
			[맨처음][이전]  21 22 23
		*/
		
		int loop = 1;
		// loop는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 개수[ 지금은 10개(== blockSize) ] 까지만 증가하는 용도이다.
		
		int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;
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
	            ...
	            21                    21 = ((21 - 1)/10) * 10 + 1
	            22                    21 = ((22 - 1)/10) * 10 + 1
	            23                    21 = ((23 - 1)/10) * 10 + 1
	            ..                    ..
	            29                    21
	            30                    21 = ((30 - 1)/10) * 10 + 1
	    */
		
		String pageBar = "<ul style='list-style: none;'>";
		String url = "admin/empList.gw";
		
		// === [맨처음][이전] 만들기 === //
		if(pageNo != 1) {
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='" + url + "?department=" + department + "&position=" + position + "&searchEmp=" + searchEmp + "&currentShowPageNo=1'>[맨처음]</a></li>";
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='" + url + "?department=" + department + "&position=" + position + "&searchEmp=" + searchEmp + "&currentShowPageNo=" + (pageNo-1) + "'>[이전]</a></li>";
		}
		
		while(!(loop > blockSize || pageNo > totalPage)) {
			if(pageNo == currentShowPageNo) {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt; border:solid 1px gray; color:red; padding:2px 4px;'>" + pageNo + "</li>";
			}
			else {
				pageBar += "<li style='display:inline-block; width:30px; font-size:12pt;'><a href='" + url + "?department=" + department + "&position=" + position + "&searchEmp=" + searchEmp + "&currentShowPageNo=" + pageNo + "'>" + pageNo + "</a></li>";
			}
			
			loop++;
			pageNo++;
		}
		
		// === [다음][마지막] 만들기 ===
		if(pageNo <= totalPage) {
			pageBar += "<li style='display:inline-block; width:50px; font-size:12pt;'><a href='" + url + "?department=" + department + "&position=" + position + "&searchEmp=" + searchEmp + "&currentShowPageNo=" + pageNo + "'>[다음]</a></li>";
			pageBar += "<li style='display:inline-block; width:70px; font-size:12pt;'><a href='" + url + "?department=" + department + "&position=" + position + "&searchEmp=" + searchEmp + "&currentShowPageNo=" + totalPage + "'>[마지막]</a></li>";
		}
		
		pageBar += "</ul>";
		
		mav.addObject("pageBar", pageBar);
		
		mav.addObject("empList", empList);
		mav.setViewName("admin/empList.tiles_KKH");
		
		return mav;
	}
	
	
	// === 직원등록 페이지 이동 === //
	@RequestMapping(value = "admin/empRegister.gw")
	public ModelAndView empRegister(ModelAndView mav) {
		mav.setViewName("admin/empRegister.tiles_KKH");
		
		return mav;
	}
	
	
	// === 부서목록 가져오기(ajax) === //
	@ResponseBody
	@RequestMapping(value = "/getDepartmentName.gw", method = {RequestMethod.GET}, produces = "text/plain;charset=UTF-8")
	public String getDepartmentName(HttpServletRequest request) {
		
		// === 부서목록 가져오기(select) === //
		List<DepartmentVO_KGH> departList = service.getDepartmentName();
		
		JSONArray jsonArr = new JSONArray();	// []
		
		if(departList != null) {
			for(DepartmentVO_KGH department : departList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("depart", department.getDepartmentname());
				jsonObj.put("departno", department.getDepartno());
				
				jsonArr.put(jsonObj);
			}
		}
		
		return jsonArr.toString();
	}
	
	
	// === 직급 목록 가져오기(ajax) === //
	@ResponseBody
	@RequestMapping(value = "/getPositionName.gw", method = {RequestMethod.GET}, produces = "text/plain;charset=UTF-8")
	public String getPositionName(HttpServletRequest request) {
		
		// === 직급 목록 가져오기(select) === //
		List<PositionVO_KGH> positionList = service.getPosition();
		
		JSONArray jsonArr = new JSONArray();
		
		if(positionList != null) {
			for(PositionVO_KGH position : positionList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("position", position.getPositionname());
				jsonObj.put("positionno", position.getPositionno());
				
				jsonArr.put(jsonObj);
			}
		}
		
		return jsonArr.toString();
	}
	
	// === 검색어 자동생성하기 === //
	@ResponseBody
	@RequestMapping(value = "/employeeSearch.gw", method = {RequestMethod.GET}, produces = "text/plain;charset=UTF-8")
	public String employeeSearch(HttpServletRequest request) {
		
		String searchEmployee = request.getParameter("searchEmployee");
		
		Map<String, String> paraMap = new HashedMap<>();
		paraMap.put("searchEmployee", searchEmployee);
		
		// === 검색어 결과 조회하기(select) === //
		List<String> searchList = service.employeeSearch(paraMap);
		
		JSONArray jsonArr = new JSONArray(); // []
		
		if(searchList != null) {
			for(String empName : searchList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("empName", empName);
				
				jsonArr.put(jsonObj);
			}
		}
		return jsonArr.toString();
	}
	
	
	// === 이메일 중복 여부 체크 메서드 === //
	@ResponseBody
	@RequestMapping(value = "/emailDuplicateCheck.gw", method = {RequestMethod.POST})
	public String emailDuplicateCheck(HttpServletRequest request) {
		String email = request.getParameter("email");
		
		// === 이메일 중복 여부 검사하기(select) === //
		boolean isExists = service.emailDuplicateCheck(email);

		// System.out.println(isExists);
		
		JSONObject jsonObj = new JSONObject(); // {}
		jsonObj.put("isExists", isExists);
		
		String json = jsonObj.toString();
		
		return json;
	}
	
	// === 직원 등록 완료 메서드 === //
	@RequestMapping(value = "/empRegisterEnd.gw", method = {RequestMethod.POST})
	public ModelAndView empRegisterEnd(ModelAndView mav, EmployeeVO_KGH emp, MultipartHttpServletRequest mrequest) {
		
		String departmentno = mrequest.getParameter("selectDepart");
		String positionno = mrequest.getParameter("selectPosition");
		
		System.out.println(departmentno);
		System.out.println(positionno);
		
		emp.setFk_departNo(departmentno);
		emp.setFk_positionNo(positionno);
		
		MultipartFile attach = emp.getAttach();
		
		if(!attach.isEmpty()) {
			HttpSession session = mrequest.getSession();
			
			String root = session.getServletContext().getRealPath("/");
			
			String path = root + "resources" + File.separator + "files";
			
			String profilename = "";
			
			byte[] bytes = null;
			
			long fileSize = 0;
			
			try {
				bytes = attach.getBytes();
				
				// 새로운 프로필 사진 업로드하기
				profilename = FileManager.doFileUpload(bytes, attach.getOriginalFilename(), path);
				
				emp.setProfilename(profilename);
				emp.setOrgProfilename(attach.getOriginalFilename());
				
				fileSize = attach.getSize();
				emp.setFileSize(String.valueOf(fileSize));
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 새로 생성될 사원번호 조회하기
		String employeeId = service.selectEmpId(departmentno);
		emp.setEmployeeid(employeeId);

		if(attach.isEmpty()) {
			// 프로필 사진이 없는 경우
			// 직원 정보 insert 하기
			service.empRegister(emp);
		}
		else {
			// 프로필 사진이 있는 경우
			// 프로필사진과 함께직원 정보 insert 하기
			int n = service.empRegisterWithProfile(emp);
			
			if(n == 1) {
				System.out.println("성공!");
			}
		}
		
		mav.setViewName("redirect:/index.gw");
		
		return mav;
	}
	
	
	// === 멤버 상세 정보 및 수정 페이지 이동 === //
	@RequestMapping(value = "admin/empListEdit.gw")
	public ModelAndView empListEdit(ModelAndView mav, HttpServletRequest request) {
		
		String employeeID = request.getParameter("empId");
		
		// System.out.println(employeeID);
		
		// === 특정 회원에 대한 정보 가져오기(select) === //
		Map<String, String> map = service.empListEdit(employeeID);
		
		mav.addObject("map", map);
		mav.setViewName("admin/empListEdit");
		
		return mav;
	}
	
	
	// === 멤버 정보 수정 완료 === //
	@RequestMapping(value = "/empEditEnd.gw")
	public ModelAndView empEditEnd(ModelAndView mav, HttpServletRequest request, EmployeeVO_KGH emp) {
		
		String departno = request.getParameter("selectDepart");
		String positionno = request.getParameter("selectPosition");
		
		emp.setFk_departNo(departno);
		emp.setFk_positionNo(positionno);;
		
		// === 직원 정보 수정하기(update) === //
		int n = service.empEdit(emp);
		
		if(n == 1) {
			String msg = "수정이 완료되었습니다.";
			String loc = request.getContextPath() + "/admin/empList.gw";
			
			mav.addObject("message", msg);
			mav.addObject("loc", loc);
		}
		else {
			String msg = "수정이 실패되었습니다.";
			String loc = request.getContextPath() + "/admin/empList.gw";
			
			mav.addObject("message", msg);
			mav.addObject("loc", loc);
		}
		
		mav.setViewName("msg");
		
		return mav;
	}
	
	////////////////////////////////////////////////////////
	// === 로그인 또는 로그아웃을 할 때 현재 페이지로 돌아가는 메서드 생성 === //
	public void getCurrentURL(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("goBackURL", MyUtil_KGH.getCurrentURL(request));
	}
	////////////////////////////////////////////////////////
}
