package com.spring.finalProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String approvalForm(HttpServletRequest request) {
		
		return "approval/approvalForm.tiles_WHC";
		
	}
	
	// === 기안양식을 골랐을때 기안작성하는 페이지 === //
	@RequestMapping(value="/addApproval.gw")
	public String addApproval(HttpServletRequest request) {
		
		return "approval/addApproval.tiles_WHC";
		
	}
}
