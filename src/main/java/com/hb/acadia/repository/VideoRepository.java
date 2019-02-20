package com.hb.acadia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.Video;

/**
 * 
 * @author anis
 *
 */
public interface VideoRepository extends JpaRepository<Video, Long>{

	/**
	 * @param uuid
	 * @return Corresponding Video
	 */
	Video findByUuid(String uuid);
	
}
