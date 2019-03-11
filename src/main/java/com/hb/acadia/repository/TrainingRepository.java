package com.hb.acadia.repository;

import java.util.List;

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
	 * @param uuid : of the training.
	 * @return Corresponding Training
	 */
	Training findByUuid(String uuid);
	
	/**
	 * @param category
	 * @return Corresponding Trainings
	 */
	List<Training> findByCategory(Category category);
	
	/**
	 * @param trainer
	 * @return Corresponding Trainings
	 */
	List<Training> findByTrainer(Trainer trainer);
	
}
