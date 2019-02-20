package com.hb.acadia.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.Category;
import com.hb.acadia.model.Training;
import com.hb.acadia.model.user.Trainer;

/**
 * 
 * @author anis
 *
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

	/**
	 * @param id
	 * @return Corresponding Training
	 */
	Training findById(long id);
	
	/**
	 * @param category
	 * @return Corresponding Trainings
	 */
	Set<Training> findByCategory(Category category);
	
	/**
	 * @param trainer
	 * @return Corresponding Trainings
	 */
	Set<Training> findByTrainer(Trainer trainer);
	
}
