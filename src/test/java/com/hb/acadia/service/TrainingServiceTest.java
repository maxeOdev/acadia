package com.hb.acadia.service;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.Category;
import com.hb.acadia.model.Training;
import com.hb.acadia.model.Video;
import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.Trainer;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author anis
 *
 *         Test class for TrainingService's methods.
 *
 */
@Slf4j
public class TrainingServiceTest extends AbstractApplicationTest {

	private Training training;
	private Trainer trainer1;
	private Category category1;
	
	@BeforeClass
	public static void onLaunch() {
		log.info("************************************ STARTING TRAINING TEST ***************************************");
	}

	@AfterClass
	public static void onExit() {
		log.info("************************************ DELETING DATAS ***************************************");
	}

	@Before
	public void initDataset() {

		/* Create Role in db */
		Role role = new Role();
		role.setRoleName("ROLE_TRAINER");
		roleService.createRole(role);

		/* Create User in database */
		Address address = new Address();
		address.setCity("Toulouse");
		address.setCountry("France");
		address.setCp("31000");
		address.setNumber(24);
		address.setRoad("République");
		address.setRoadType("Avenue");
		trainer1 = new Trainer();
		trainer1.setActif(true);
		trainer1.setFirstName("Simon");
		trainer1.setName("Aliotti");
		trainer1.setEmail("simone.aliot@gmail.com");
		trainer1.setPassword("toto");
		trainer1.setAddress(address);
		trainer1.setComments(null);
		trainer1.setIdStripe("Flkjdf76385");
		trainer1.setExperience(4);
		trainer1.setQualifications("master");
		trainer1.setCertified(false);
		trainer1.setTrainings(null);
		trainerService.createTrainer(trainer1);

		training = new Training();
		training.setTitle("Titre test");
		training.setDescription("je suis la description");
		training.setDifficulty(Training.Level.CONFIRMED);
		training.setDuration(300);
		training.setPrice(99.99d);
		training.setActive(true);
		training.setComments(null);
		training.setDescription("Toto va à la plage");
		training.setTrainer(trainer1);

		Set<Training> trainings = new LinkedHashSet<>();
		trainings.add(training);
		Video video = new Video("video1", "/d/eclipse/test", trainings);
		videoService.createVideo(video);

		category1 = new Category();
		category1.setName("info");
		category1 = categoryService.createCategory(category1);

		trainingService.createTraining(training, categoryRepository.findByName(category1.getName()), new LinkedList<>());
	}

	@After
	public void deleteDataset() {
		trainingRepository.deleteAll();
		categoryRepository.deleteAll();
		videoRepository.deleteAll();
		userService.deleteAll();
		roleRepository.deleteAll();
	}

	@Test
	public void create() {
		/* Create User in database */
		Address address = new Address();
		address.setCity("Sainté");
		address.setCountry("France");
		address.setCp("42100");
		address.setNumber(42);
		address.setRoad("des Arbres");
		address.setRoadType("Rue");
		Trainer trainer1 = new Trainer();
		trainer1.setActif(true);
		trainer1.setFirstName("Anis");
		trainer1.setName("Lalami");
		trainer1.setEmail("llmanis@gmail.com");
		trainer1.setPassword("titi");
		trainer1.setAddress(address);
		trainer1.setComments(null);
		trainer1.setIdStripe("ngk47kxuv7ebv53sawa9");
		trainer1.setExperience(1);
		trainer1.setQualifications("siturba");
		trainer1.setCertified(false);
		trainer1.setTrainings(null);
		trainerService.createTrainer(trainer1);

		training.setDescription("je suis la description");
		training.setDifficulty(Training.Level.CONFIRMED);
		training.setDuration(300);
		training.setPrice(99.99d);
		training.setActive(true);
		training.setComments(null);
		training.setDescription("Toto va à la plage");
		training.setTrainer(trainer1);

		Set<Training> trainings = new LinkedHashSet<>();
		trainings.add(training);
		Video video = new Video("video2", "/d/eclipse/test", trainings);
		videoService.createVideo(video);

		String categoryName = "tuto";
		categoryService.createCategory(new Category(categoryName));

		trainingService.createTraining(training, categoryRepository.findByName(categoryName), new LinkedList<>());

		assertTrue(training.equals(trainingService.getByUuid(training.getUuid())));
	}

	@Test
	public void update() {
		Training trainingWithChanges = trainingService.getByUuid(training.getUuid());
		trainingWithChanges.setDescription(trainingWithChanges.getDescription() + "changessss");
		trainingWithChanges.setActive(false);
		trainingWithChanges.setComments(new LinkedHashSet<>());

		try {
			trainingService.updateTraining(trainingWithChanges);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Training gotTrainingWithChanges = trainingRepository.findByUuid(training.getUuid());

		assertTrue(gotTrainingWithChanges.equals(trainingWithChanges));
	}

	@Test
	public void delete() {
		Training t1 = trainingService.getByUuid(training.getUuid());
		assertTrue(t1 != null);

		trainingService.deleteTraining(t1);

		t1 = trainingService.getByUuid(training.getUuid());
		assertTrue(t1 == null);
	}

	@Test
	public void getByUuid() {
		assertTrue(training.equals(trainingService.getByUuid(training.getUuid())));
	}

	@Test
	public void getByTrainer() {
		List<Training> trainingsOfTrainer1 = trainingService.getByTrainer(trainer1);
		List<Training> all = trainingService.getAllTrainings();
		for (Training training:all) {
			if (training.getTrainer().equals(trainer1)) {
				assertTrue(trainingsOfTrainer1.contains(training));
			}
		}
	}

	@Test
	public void getByCategory() {
		List<Training> trainingsOfCategory1 = trainingService.getByCategory(category1);
		List<Training> all = trainingService.getAllTrainings();
		for (Training training:all) {
			if (training.getCategory().equals(category1)) {
				assertTrue(trainingsOfCategory1.contains(training));
			}
		}
	}
	
}
