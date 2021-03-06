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
	 * @param uuid : of the Video.
	 * @return Corresponding {@linkplain Video}.
	 */
	Video findByUuid(String uuid);
	
}
