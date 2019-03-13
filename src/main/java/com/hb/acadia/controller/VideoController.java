package com.hb.acadia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hb.acadia.service.VideoService;

@Controller
@RequestMapping("/admin")
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@GetMapping("/videos")
	public ModelAndView videos() {
		ModelAndView mav = new ModelAndView("videos");
		mav.addObject("videos", videoService.getAllVideos());
		return mav;
	}
	
}
