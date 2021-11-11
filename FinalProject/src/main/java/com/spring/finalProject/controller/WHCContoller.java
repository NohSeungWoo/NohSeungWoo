package com.spring.finalProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.approval.model.ApcategoryVO;
import com.spring.finalProject.service.InterWHCService;

//==== #30. 컨트롤러 선언 ====
@Component
@Controller
public class WHCContoller {
	
	@RequestMapping(value = "/index.gw")
	public String index(HttpServletRequest request) {
		
		return "main/index.tiles1";
		//	/WEB-INF/views/tiles1/main/index.jsp 파일을 생성한다.
	}
	
	@Autowired
	private InterWHCService service;
	
	// === 전자결재 메인 페이지 === //
	@RequestMapping(value="/approval.gw")
	public String approvalMain(HttpServletRequest request) {
		
		return "approval/approvalMain.tiles_WHC";
		//  /WEB-INF/views/tiles_WHC/approval/approvalMain.jsp 파일을 생성한다.
	}
	
	// === 기안하기 버튼 클릭시 기안양식 페이지 === //
	@RequestMapping(value="/approvalForm.gw")
	public ModelAndView approvalForm(HttpServletRequest request, ModelAndView mav) {

		// 기안양식 카테고리 얻어오기
		List<ApcategoryVO> apcList = service.getApcategoryList();
		
		mav.addObject("apcList", apcList);
		mav.setViewName("approval/approvalForm.tiles_WHC");
		
		return mav;
		
	}
	
	// === 기안양식을 골랐을때 기안작성하는 페이지 === //
	@RequestMapping(value="/addApproval.gw")
	public ModelAndView addApproval(HttpServletRequest request, ModelAndView mav) {
		
		String apcano = request.getParameter("apcano");
		String apcaname = request.getParameter("apcaname");
		mav.addObject("apcano", apcano);
		mav.addObject("apcaname", apcaname);
		
		mav.setViewName("approval/addApproval.tiles_WHC");
		return mav;
		
	}
}
