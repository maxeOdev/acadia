package com.hb.acadia.controller;

import java.util.Arrays;

import com.hb.acadia.utils.DomainConst;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class DashboardController {

	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("domains", Arrays.asList(DomainConst.getValues()));
		mav.setViewName("dashboard");
		return mav;
	}

}
