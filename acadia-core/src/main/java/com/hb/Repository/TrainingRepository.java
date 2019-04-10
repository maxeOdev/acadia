package com.hb.Repository;

import com.hb.Model.Category;
import com.hb.Model.Training;
import com.hb.Model.user.Trainer;
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
