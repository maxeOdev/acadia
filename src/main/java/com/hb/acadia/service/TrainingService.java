package com.hb.acadia.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.Category;
import com.hb.acadia.model.Training;
import com.hb.acadia.model.user.Trainer;
import com.hb.acadia.repository.TrainingRepository;

/**
 * 
 * @author anis
 *
 *	Data access class for Training entity
 *
 */
@Service
public class TrainingService {

	@Autowired
	private TrainingRepository trainingRepo;
	
	/**
	 * Create the training in parameter in the database.
	 * 
	 * @param training
	 * 	Which has to be saved.
	 * @return
	 * 	the training with his id set.
	 */
	public Training createTraining(Training training) {
		return trainingRepo.save(training);
	}
	
	/**
	 * Update the training in parameter in the database.
	 * 
	 * @param training
	 * 	Which has to be updated.
	 * @return
	 * 	The updated training.
	 */
	public Training updateTraining(Training training) {
		return trainingRepo.save(training);
	}
	
	/**
	 * Delete the training in parameter in the database.
	 * 
	 * @param training
	 * 	Which has to be deleted.
	 */
	public void deleteTraining(Training training) {
		trainingRepo.delete(training);
	}
	
	
	/**
	 * Try to find the training corresponding to an id.
	 * 
	 * @param id
	 * 	Research criteria.
	 * @return
	 * 	The corresponding Training if exists or null.
	 */
	public Training getById(long id) {
		return trainingRepo.findById(id); 
	}
	
	/**
	 * Try to find every trainings corresponding to a trainer.
	 * 
	 * @param trainer
	 * 	Research criteria.
	 * @return
	 * 	The corresponding Trainings if exist or null.
	 */
	public Set<Training> getByTrainer(Trainer trainer) {
		return trainingRepo.findByTrainer(trainer);
	}
	
	/**
	 * Try to find every trainings corresponding to a category.
	 * 
	 * @param category
	 * 	Research criteria.
	 * @return
	 * 	The corresponding Trainings if exist or null.
	 */
	public Set<Training> getByCategory(Category category) {
		return trainingRepo.findByCategory(category);
	}
	
}
