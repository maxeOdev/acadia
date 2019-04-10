package com.hb.Controller;

import com.hb.Utils.DomainConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

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
