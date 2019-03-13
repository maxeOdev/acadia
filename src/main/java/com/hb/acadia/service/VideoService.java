package com.hb.acadia.service;

import java.util.List;

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
	 * Make a video saved in the database.
	 * @param video to save.
	 * @return Video saved with its id set.
	 */
	public Video createVideo(Video video) {
		return videoRepo.saveAndFlush(video);
	}
	
	/**
	 * Update a video saved in the database.
	 * @param video to update.
	 * @return Video saved with its id set.
	 */
	public Video updateVideo(Video video) {
		return videoRepo.saveAndFlush(video);
	}
	
	/**
	 * Delete a video in the database.
	 * @param video to delete.
	 */
	public void deleteVideo(Video video) {
		videoRepo.delete(video);
	}
	
	/**
	 * Search the Video corresponding to an uuid in the database.
	 * @param uuid
	 * @return the correponding Video.
	 */
	public Video getByUuid(String uuid) {
		return videoRepo.findByUuid(uuid);
	}

	/**
	 * Get all video existing in the database.
	 */
	public long count() {
		return videoRepo.count();
	}
	
	/**
	 * Get all video existing in the database.
	 */
	public List<Video> getAllVideos() {
		return videoRepo.findAll();
	}
	
	/**
	 * Delete all video existing in the database.
	 */
	public void deleteAllVideos() {
		videoRepo.deleteAll();
	}
}
