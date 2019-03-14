package com.hb.acadia.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hb.acadia.model.Video;
import com.hb.acadia.service.VideoService;

/**
 * 
 * @author anis
 *
 */
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
	
	@GetMapping("/video-details")
	public ModelAndView videoDetails(@Valid Video video, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("videos");
		
		if (bindingResult.hasErrors()) {
			mav.addObject("error", "Erreur lors de l'envoi des données.");
			return mav;
		}
		
		mav.addObject("video", videoService.getByUuid(video.getUuid()));
		return mav;
	}
	
	@GetMapping("/create-video")
	public ModelAndView createVideo(@Valid Video video, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("videos");
		
		if (bindingResult.hasErrors()) {
			mav.addObject("error", "Erreur lors de l'envoi des données.");
			return mav;
		}
		
		if (!videoService.getAllVideos()
										.stream()
										.filter(v -> 
														v.getName().equalsIgnoreCase(video.getName())
														|| v.getPath().equalsIgnoreCase(video.getPath()))
										.collect(Collectors.toList())
										.isEmpty()) {
			mav.addObject("error", "Le nom ou le chemin est déjà prit.");
			return mav;
		}
		
		videoService.createVideo(video);
		return mav;
	}
	
	@GetMapping("/update-video")
	public ModelAndView updateVideo(@Valid Video video, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("video-details");
		
		if (bindingResult.hasErrors()) {
			mav.addObject("error", "Erreur lors de l'envoi des données.");
			return mav;
		}
		
		if (!videoService.getAllVideos()
 										 .stream()
	 									 .filter(v -> 
	 									 				 v.getUuid() != video.getUuid() && 
	 									 				 ( v.getName().equalsIgnoreCase(video.getName())
														 || v.getPath().equalsIgnoreCase(video.getPath())))
										 .collect(Collectors.toList())
										 .isEmpty()) {
			mav.addObject("error", "Le nom ou le chemin est déjà prit.");
			mav.addObject("video", video);
			return mav;
		}
		
		videoService.updateVideo(video);
		mav.addObject("video", video);
		return videos();
	}
	
	@GetMapping("/delete-video")
	public ModelAndView deleteVideo(@Valid Video video, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("videos");
		
		if (bindingResult.hasErrors()
				|| !videoService.getByUuid(video.getUuid()).equals(video)) {
			mav.addObject("error", "Erreur lors de l'envoi des données.");
			return mav;
		}
		
		videoService.deleteVideo(video);
		return videos();
	}
	
}
