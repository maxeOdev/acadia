package com.hb.acadia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.Video;
import com.hb.acadia.repository.VideoRepository;

/**
 * 
 * @author anis
 *
 * Data access class for Video entity.
 *
 */
@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepo;
	
	/**
	 * Search the Video corresponding to an uuid in the database.
	 * @param uuid
	 * @return the correponding Video.
	 */
	public Video getByUuid(String uuid) {
		return videoRepo.findByUuid(uuid);
	}
	
}
