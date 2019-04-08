package com.hb.acadia.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.Category;
import com.hb.acadia.model.Comment;
import com.hb.acadia.model.Training;
import com.hb.acadia.model.Video;
import com.hb.acadia.model.user.Trainer;
import com.hb.acadia.repository.CategoryRepository;
import com.hb.acadia.repository.CommentRepository;
import com.hb.acadia.repository.TrainingRepository;
import com.hb.acadia.repository.VideoRepository;

/**
 * 
 * @author anis
 *
 *         Data access class for Training entity
 *
 */
@Service
public class TrainingService {

	@Autowired
	private TrainingRepository trainingRepo;

	@Autowired
	private VideoRepository videoRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private CommentRepository commentRepo;

	/**
	 * Create the training in parameter in the database.
	 * 
	 * @param training Which has to be saved.
	 * @param category of the training.
	 * @param videos   of the training.
	 * @return the complete training with his id set.
	 */
	@Transactional
	public Training createTraining(Training training, Category category, List<Video> videos) {

		Category cat = categoryRepo.findByName(category.getName());

		if (cat == null)
			throw new IllegalStateException("None Category corresponding to '" + category.getName() + "'");

		if (videos == null)
			throw new IllegalStateException("None videos to save 'null' reference");

		categoryRepo.save(category);
		videoRepo.saveAll(videos);

		Set<Video> videosSet = new HashSet<>();
		videos.forEach(video -> videosSet.add(video));

		training.setCategory(category);
		training.setVideos(videosSet);
		training.setComments(null);

		return trainingRepo.save(training);
	}

	/**
	 * Update the training in parameter in the database.
	 * 
	 * @param training Which has to be updated.
	 * @param          category, set to null if none changes of the training.
	 * @param          videos, set to null if none changes of the training.
	 * @return The updated training.
	 * @throws Exception
	 */
	@Transactional
	public Training updateTraining(Training training) throws Exception {

		Category category = trainingRepo.findByUuid(training.getUuid()).getCategory();
		Set<Video> videosSet = new HashSet<>();
		List<Comment> comments = new LinkedList<>();
		Set<Comment> commentsSet = new HashSet<>();
		commentsSet.addAll(comments);

		training.setCategory(category);
		training.setVideos(videosSet);
		training.setComments(commentsSet);

		return trainingRepo.save(training);
	}

	/**
	 * Delete the training in parameter in the database.
	 * 
	 * @param training Which has to be deleted.
	 */
	@Transactional
	public void deleteTraining(Training training) {

		List<Training> trainings = trainingRepo.findAll();
		List<Video> toDelete = new LinkedList<>();
		for (Training t : trainings) {
			for (Video v : training.getVideos()) {
				if (t.getVideos().contains(v)) {
					toDelete.add(v);
				}
			}
		}
		for (Video v : toDelete) {
			videoRepo.deleteAll(toDelete);
		}

		List<Comment> comments = commentRepo.findByTraining(training);
		List<Comment> commentsToDelete = new LinkedList<>();
		for (Comment com : comments) {
			if (null != com.getParentComment()) {
				commentsToDelete.add(com);
				commentRepo.delete(com);
			}
		}
		comments.removeAll(commentsToDelete);
		for (Comment com : comments) {
			commentRepo.delete(com);
		}

		trainingRepo.delete(training);
	}

	/**
	 * Try to find the training corresponding to an id.
	 * 
	 * @param uuid Research criteria.
	 * @return The corresponding Training if exists or null.
	 */
	public Training getByUuid(String uuid) {
		return trainingRepo.findByUuid(uuid);
	}

	/**
	 * Try to find every trainings corresponding to a trainer.
	 * 
	 * @param trainer Research criteria.
	 * @return The corresponding Trainings if exist or null.
	 */
	public List<Training> getByTrainer(Trainer trainer) {
		return trainingRepo.findByTrainer(trainer);
	}

	/**
	 * Try to find every trainings corresponding to a category.
	 * 
	 * @param category Research criteria.
	 * @return The corresponding Trainings if exist or null.
	 */
	public List<Training> getByCategory(Category category) {
		return trainingRepo.findByCategory(category);
	}

	/**
	 * Get every trainings.
	 * 
	 * @return Every training saved in the database.
	 */
	public List<Training> getAllTrainings() {
		return trainingRepo.findAll();
	}

	/**
	 * Delete every trainings.
	 */
	public void deleteAll() {
		trainingRepo.deleteAll();
	}

}
