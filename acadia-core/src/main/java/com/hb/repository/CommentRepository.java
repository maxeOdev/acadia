package com.hb.repository;

import com.hb.model.Comment;
import com.hb.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	/**
	 * @param training
	 * @return the comment list on a training
	 */
	List<Comment> findByTraining(Training training);
	
}
