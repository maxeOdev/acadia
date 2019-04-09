package com.hb.acadia.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hb.acadia.model.Video;
import com.hb.acadia.repository.VideoRepository;
import com.hb.acadia.service.VideoService;
import com.hb.acadia.utils.StreamHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author anis
 *
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@GetMapping("/videos")
	public ModelAndView videos() {
		ModelAndView mav = new ModelAndView("video");
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
//		
//		if (bindingResult.hasErrors()) {
//			mav.addObject("error", "Erreur lors de l'envoi des données.");
//			return mav;
//		}
//		
//		if (!videoService.getAllVideos()
//										.stream()
//										.filter(v -> 
//														v.getName().equalsIgnoreCase(video.getName())
//														|| v.getPath().equalsIgnoreCase(video.getPath()))
//										.collect(Collectors.toList())
//										.isEmpty()) {
//			mav.addObject("error", "Le nom ou le chemin est déjà prit.");
//			return mav;
//		}
//		
//		videoService.createVideo(video);
		return mav;
	}
	
	@GetMapping("/update-video")
	public ModelAndView updateVideo(@Valid Video video, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("video-details");
		
//		if (bindingResult.hasErrors()) {
//			mav.addObject("error", "Erreur lors de l'envoi des données.");
//			return mav;
//		}
//		
//		if (!videoService.getAllVideos()
// 										 .stream()
//	 									 .filter(v -> 
//	 									 				 v.getUuid() != video.getUuid() && 
//	 									 				 ( v.getName().equalsIgnoreCase(video.getName())
//														 || v.getPath().equalsIgnoreCase(video.getPath())))
//										 .collect(Collectors.toList())
//										 .isEmpty()) {
//			mav.addObject("error", "Le nom ou le chemin est déjà prit.");
//			mav.addObject("video", video);
//			return mav;
//		}
//		
//		videoService.updateVideo(video);
//		mav.addObject("video", video);
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
	
	/**
	 * 
	 * 
	 * Dynamic implementation
	 * 
	 * 
	 */
	@Autowired
	private StreamHelper streamHelper;
	
	/**
	 * @return all videos
	 */
	@GetMapping("/all-videos")
	@ResponseBody
	public List<Video> listVideoRespBody() {
		return videoService.getAllVideos();
	}
	
	/**
	 * @param uuid
	 * @return the matching video with the param
	 */
	@GetMapping("/video-by-id")
	@ResponseBody
	public Video getByIdRespBody(@RequestParam String uuid) {
		return videoService.getByUuid(uuid);
	}
	
	/**
	 * @param video
	 * @param bindingResult
	 * @return the result of the operation w/ or w/o errors
	 */
	@PostMapping("/update-video")
	public ModelAndView update(@RequestParam("video") MultipartFile file) {
		
		log.info("Well done.");
		// in case of not video existing
		if (file.isEmpty()) {
			return new ModelAndView("video");
		}
		
		// else
		try {
			videoService.updateVideo(file);
		} catch (IOException e) {
			log.error("Erreur lors du téléversement du fichier. "+e.getMessage());
			e.printStackTrace();
		}
		
		return new ModelAndView("video");
	}
	
//	/**
//	 * @param video
//	 * @param bindingResult
//	 * @return the result of the operation w/ or w/o errors
//	 */
//	@PostMapping("/create-video")
//	public ModelAndView create(@RequestParam("video") MultipartFile file) {
//
//		log.info("Well done.");
//		// in case of not video existing
//		if (file.isEmpty()) {
//			return new ModelAndView("video");
//		}
//
//		// else
//		try {
//			videoService.createVideo(file);
//		} catch (IOException e) {
//			log.error("Erreur lors du téléversement du fichier. "+e.getMessage());
//			e.printStackTrace();
//		}
//
//		return new ModelAndView("video");
//	}

	/**
	 * @param video
	 * @param bindingResult
	 * @return the result of the operation w/ or w/o errors
	 */
	@PostMapping("/create-video")
	public ModelAndView create(@RequestParam("video") List<MultipartFile> files) {

		for (MultipartFile file : files) {
			try {
				videoService.createVideo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			


		return new ModelAndView("video");
	}
	/**
	 * @param uuid
	 * @return the result of the operation w/ or w/o errors
	 */
	@DeleteMapping("/delete-video")
	@ResponseBody
	public ResponseEntity<List<ObjectError>> delete(@RequestParam("uuid") String uuid) {
		
		// in case of not video existing
		if (videoService.getByUuid(uuid) == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// else
		videoService.deleteVideo(uuid);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * @return video list template
	 */
	@GetMapping("/list-videos-template")
	public ModelAndView getVideosTemplate() { return new ModelAndView("fragments/list-videos"); }

	/**
	 * @return video details template
	 */
	@GetMapping("/video-details-template")
	public ModelAndView getVideoDetailsTemplate() { return new ModelAndView("fragments/video-details"); }

	/**
	 * @return video creation template
	 */
	@GetMapping("/create-video-template")
	public ModelAndView getCreateVideoTemplate() { return new ModelAndView("fragments/create-video"); }
	
	/**
	 * Download video
	 * @param response
	 * @param uuid
	 */
	@GetMapping(value = "/download")
	public void produceHome(HttpServletResponse response, @RequestParam(value = "uuid") String uuid) {
		Video video = videoService.getByUuid(uuid);
		File file = new File(video.getPath());
		streamHelper.addStreamHelperToResponse(file.getPath(), "video/mp4", response);
	}
}
