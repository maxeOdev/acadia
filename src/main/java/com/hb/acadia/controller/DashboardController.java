package com.hb.acadia.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hb.acadia.utils.DomainConst;

@Controller
@RequestMapping("/admin")
public class DashboardController {

	@GetMapping("/dashboard")
	public ModelAndView dashboard(@RequestParam(required = false) String domain) {
		ModelAndView mav = new ModelAndView();

		/* according to the selected domain */
		if (null != domain) {
			switch (domain) {
			case DomainConst.CATEGORIES:
				
				break;
			case DomainConst.FORMATEURS:

				break;
			case DomainConst.FORMATIONS:

				break;
			case DomainConst.UTILISATEURS:

				break;
			case DomainConst.VIDEOS:

				break;
			default:
				;
			}
		} /* else for simple dashboard menu page */

		mav.addObject("domains", Arrays.asList(DomainConst.getValues()));
		mav.setViewName("/dashboard");
		return mav;
	}

}
