package com.hb.repository;

import com.hb.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

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
