package com.hb.Repository;

import com.hb.Model.Comment;
import com.hb.Model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	/**
	 * @param training
	 * @return the comment list on a training
	 */
	List<Comment> findByTraining(Training training);
	
}
