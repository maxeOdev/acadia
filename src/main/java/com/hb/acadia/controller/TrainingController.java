package com.hb.acadia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hb.acadia.model.Training;
import com.hb.acadia.service.TrainingService;

@Controller
@RequestMapping("/admin")
public class TrainingController {

	@Autowired
	private TrainingService trainingService;

	@GetMapping("/trainings")
	public ModelAndView trainings() {
		return new ModelAndView("training");
	}

	@GetMapping("/all-trainings")
	@ResponseBody
	public ResponseEntity<List<Training>> getAll() {
		return new ResponseEntity<List<Training>>(trainingService.getAllTrainings(), HttpStatus.OK);
	}
	
}
