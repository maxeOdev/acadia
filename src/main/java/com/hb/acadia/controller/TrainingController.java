package com.hb.acadia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hb.acadia.service.TrainingService;

@Controller
@RequestMapping("/admin")
public class TrainingController {

	@Autowired
	private TrainingService trainingService;
	
	@GetMapping("/trainings")
	public ModelAndView trainings() {
		ModelAndView mav = new ModelAndView("trainings");
		mav.addObject("trainings", trainingService.getAllTrainings());
		return mav;
	}
	
}
