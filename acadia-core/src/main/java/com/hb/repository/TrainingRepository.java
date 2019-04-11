package com.hb.repository;

import com.hb.model.Category;
import com.hb.model.Training;
import com.hb.model.user.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
