package com.spring.finalProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.finalProject.model.EmployeeVO_KGH;
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

	@RequestMapping(value = "/empList.gw")
	public ModelAndView empList(ModelAndView mav) {
		
		// 직원목록 가져오기 메서드
		List<EmployeeVO_KGH> empList =  service.getEmpList();
		
		mav.setViewName("admin/empList.tiles_KKH");
		
		return mav;
	}
	
}
