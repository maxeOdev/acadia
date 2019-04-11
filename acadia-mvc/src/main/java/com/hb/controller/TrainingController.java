package com.hb.controller;

import com.hb.model.Training;
import com.hb.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TrainingController {

	@Autowired
	private TrainingService trainingService;

	@GetMapping("/trainings")
	public ModelAndView trainings() {
		ModelAndView mav = new ModelAndView("trainings");
		Map<String, List<Training>> data = new HashMap<>();
		data.put("trainings", trainingService.getAllTrainings());
		mav.addObject("data", data);
		return mav;
	}

}
