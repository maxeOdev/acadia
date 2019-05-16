package com.hb.service;

import com.hb.model.Training;
import com.hb.model.Video;
import com.hb.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

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
	
	@Value("${application.videos.upload-folder}")
	private String uploadFolder;
	
	/**
	 * Make a video saved in the database.
	 * @param file to save.
	 * @return Video saved with its id set.
	 * @throws IOException 
	 */
	public Video createVideo(MultipartFile file) throws IOException {
		
		byte[] data = file.getBytes();
		Video video = new Video(file.getOriginalFilename(), null, new HashSet<Training>());
		
		Video savedVideo = videoRepo.save(video);
		
		String sPath = "/Users/simonaliotti/" + uploadFolder+"/"+savedVideo.getUuid()+"_"+file.getOriginalFilename();
//		String sPath = "/"+savedVideo.getUuid()+"_"+file.getOriginalFilename();
		Path path = Paths.get(sPath);
		
		Files.write(path, data);
		
		savedVideo.setPath(sPath);
		
		return videoRepo.save(savedVideo);
	}
	
	/**
	 * Update a video saved in the database.
	 * @param file to update.
	 * @return Video saved with its id set.
	 * @throws IOException 
	 */
	public Video updateVideo(MultipartFile file) throws IOException {
		byte[] data = file.getBytes();
		Video video = new Video(file.getOriginalFilename(), null, new HashSet<Training>());
		
		Video savedVideo = videoRepo.save(video);
		
		String sPath = uploadFolder+"/"+savedVideo.getUuid()+"_"+file.getOriginalFilename();
//		String sPath = "/"+savedVideo.getUuid()+"_"+file.getOriginalFilename();
		Path path = Paths.get(sPath);
		
		Files.write(path, data);
		
		savedVideo.setPath(sPath);
		
		return videoRepo.save(savedVideo);
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

	/**
	 * Delete the video matching with the given uuid
	 * @param uuid
	 */
	public void deleteVideo(String uuid) {
		videoRepo.delete(videoRepo.findByUuid(uuid));
	}

	public ResourceRegion getData(HttpHeaders headers){
		File file = new File("/Users/simonaliotti/videos/544da70c-4348-4e98-9e7c-36ddf1506441_videoplayback.mp4");
		return this.resourceRegion(file, headers);
	}

	private ResourceRegion resourceRegion(File video, HttpHeaders headers){
		long contentLength = video.getTotalSpace();
		List<HttpRange> ranges = headers.getRange();
		if(ranges != null && ranges.size()>0) {
			long start = ranges.get(0).getRangeStart(contentLength);
			long end = ranges.get(0).getRangeEnd(contentLength);
			long rangeLength = Math.min(1 * 1024 * 1024, end - start + 1);
			Resource resource = new FileSystemResource(video);
			return new ResourceRegion(resource, start, rangeLength);
		} else {
			long rangeLength = Math.min(1 * 1024 * 1024, contentLength);
			Resource resource = new FileSystemResource(video);
			return new ResourceRegion(resource, 0, rangeLength);

		}
	}
}
