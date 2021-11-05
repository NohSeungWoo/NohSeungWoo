package com.spring.finalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KGHController {
	
	@RequestMapping(value = "/login.gw")
	public ModelAndView login(ModelAndView mav) {
		
		mav.setViewName("main/login.tiles1");
		
		return mav;
	}

	@RequestMapping(value = "/memberList.gw")
	public ModelAndView memberList(ModelAndView mav) {
		
		mav.setViewName("admin/memberList.tiles2");
		
		return mav;
	}
	
}
