package com.spring.finalProject.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.finalProject.model.PositionVO_KGH;
import com.spring.finalProject.service.InterKGHService;

@Controller
public class KGHController {
	
	@Autowired
	private InterKGHService service;
	
	@RequestMapping(value = "/login.gw")
	public ModelAndView login(ModelAndView mav) {
		
		mav.setViewName("main/login.tiles1");
		
		return mav;
	}

	@RequestMapping(value = "admin/empList.gw")
	public ModelAndView empList(ModelAndView mav, HttpServletRequest request) {
		
		List<Map<String, String>> empList = null;
		
		// 직원목록 가져오기 메서드
		// empList = service.getEmpList();
		
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		String searchEmp = request.getParameter("searchEmp");
		
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
		
		// 먼저 총 게시물 건수(totalCount)를 구한다.
		// 총 게시물 건수는(totalCount)는 검색조건이 있을 때와 없을 때로 나뉘어진다.
		int totalCount = 0;			// 총 게시물 건수
		int sizePerPage = 5;		// 한 페이지당 보여지는 게시물 건수
		int currentShowPageNo = 0;	// 현재 보여주는 페이지, 초기값은 1페이지
		int totalPage = 0;			// 총 페이지 수(웹에서 보여줄 총 페이지 개수)
		
		int startRno = 0;			// 페이지바 시작 행번호
		int endRno = 0;				// 페이지바 끝 행번호
		
		// === 총 게시물 건수(totalCount) 가져오기(select) === //
		totalCount = service.getTotalCount(paraMap);
		// System.out.println("~~~ 확인용 totalCount : " + totalCount);
		// ~~~ 확인용 totalCount : 6
		
		// 총 게시물 건수(totalCount)가 127개일 경우
		// 총 페이지 수(totalPage)는 26개가 되어야한다.
		totalPage = (int) Math.ceil(((double)totalCount/sizePerPage));
		
		if(str_currentShowPageNo == null) {
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
		
		startRno = ((currentShowPageNo - 1) * sizePerPage) + 1;
		endRno = startRno + sizePerPage - 1;
		
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		
		// 페이징 처리한 직원 목록 가져오기(검색이 있든지, 검색이 없든지 다 포함된 것)
		empList = service.getEmpListWithPaging(paraMap);
		
		// 아래는 검색대상 컬럼과 검색어를 유지시키기 위한 것임.
//		if(!"".equals(department) && !"".equals(position)) {
//			mav.addObject("paraMap", paraMap);
//		}
		
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
		
		String pageBar = "<ul>";
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
		
		pageBar = "/<ul>";
		
		mav.addObject("pageBar", pageBar);
		
		mav.addObject("empList", empList);
		mav.setViewName("admin/empList.tiles_KKH");
		
		return mav;
	}
	
	// === 부서목록 가져오기(ajax) === //
	@ResponseBody
	@RequestMapping(value = "/getDepartmentName.gw", method = {RequestMethod.GET}, produces = "text/plain;charset=UTF-8")
	public String getDepartmentName(HttpServletRequest request) {
		
		// === 부서목록 가져오기(select) === //
		List<String> departList = service.getDepartmentName();
		
		JSONArray jsonArr = new JSONArray();	// []
		
		if(departList != null) {
			for(String departmentName : departList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("departmentName", departmentName);
				
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
		List<String> positionList = service.getPosition();
		
		JSONArray jsonArr = new JSONArray();
		
		if(positionList != null) {
			for(String position : positionList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("position", position);
				
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
	
	
}
