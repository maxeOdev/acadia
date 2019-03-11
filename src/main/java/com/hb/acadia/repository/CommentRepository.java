package com.hb.acadia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.Comment;
import com.hb.acadia.model.Training;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	/**
	 * @param training
	 * @return the comment list on a training
	 */
	List<Comment> findByTraining(Training training);
	
}
