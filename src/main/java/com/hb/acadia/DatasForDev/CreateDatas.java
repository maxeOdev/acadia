package com.hb.acadia.DatasForDev;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.Category;
import com.hb.acadia.model.Comment;
import com.hb.acadia.model.Training;
import com.hb.acadia.model.Training.Level;
import com.hb.acadia.model.Video;
import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.Trainer;
import com.hb.acadia.model.user.User;
import com.hb.acadia.repository.RoleRepository;
import com.hb.acadia.repository.VideoRepository;
import com.hb.acadia.service.CategoryService;
import com.hb.acadia.service.TrainerService;
import com.hb.acadia.service.TrainingService;
import com.hb.acadia.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe permettant de créer des jeux de données pour le développement
 */
@Slf4j
@Component
public class CreateDatas {

	@Autowired
	private TrainerService trainerService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private TrainingService trainingService;

	private Role r1 = new Role();
	private Role r2 = new Role();
	private Role r3 = new Role();

	private Address ad1 = new Address();
	private Address ad2 = new Address();
	private Address ad3 = new Address();

	private Trainer t1 = new Trainer();

	private User u1 = new User();
	private User u2 = new User();

	private Video v1 = new Video();
	private Video v2 = new Video();
	private Video v3 = new Video();

	private Category c1 = new Category();
	private Category c2 = new Category();
	private Category c3 = new Category();

	private Training ti1 = new Training();
	private Training ti2 = new Training();

	public void createRoles() {
		r1.setRoleName("ROLE_CUSTOMER");
		r2.setRoleName("ROLE_ADMIN");
		r3.setRoleName("ROLE_TRAINER");
		roleRepository.save(r1);
		roleRepository.save(r2);
		roleRepository.save(r3);
		log.info("");
		log.info("****** Roles saved in DB ******");
		log.info("");
	}

	public void createTrainers() {

		ad1.setCity("Adresse trainer");
		ad1.setCountry("France");
		ad1.setNumber(34);
		ad1.setRoad("Du chateau");
		ad1.setCp("35876");
		ad1.setRoadType("Avenue");

		t1.setEmail("trainer@gmail.com");
		t1.setFirstName("max");
		t1.setName("well");
		t1.setPassword("max");
		t1.setActif(true);
		t1.setAddress(ad1);

		t1.setExperience(8);
		t1.setIdStripe("11111");
		t1.setQualifications("master");

		trainerService.createTrainer(t1);
	}

	public void createUsers() {

		ad2.setCity("Cailloux-sur-Fontaine");
		ad2.setCountry("France");
		ad2.setNumber(34);
		ad2.setRoad("Du chateau");
		ad2.setCp("35876");
		ad2.setRoadType("Avenue");

		u1.setEmail("sim.aliotti@gmail.com");
		u1.setFirstName("simon");
		u1.setName("Aliotti");
		u1.setPassword("toto");
		u1.setActif(true);
		u1.setAddress(ad2);
		userService.createUser(u1);

		ad3.setCity("Cailloux-sur-Fontaine");
		ad3.setCountry("France");
		ad3.setNumber(34);
		ad3.setRoad("Du chateau");
		ad3.setCp("35876");
		ad3.setRoadType("Avenue");

		u2.setEmail("anis@gmail.com");
		u2.setFirstName("Anis");
		u2.setName("Lalami");
		u2.setPassword("test");
		u2.setActif(true);
		u2.setAddress(ad3);
		userService.createUser(u2);
	}

	public void createCategory() {
		c1 = new Category("informatique");
		c2 = new Category("gestion");
		c3 = new Category("bricolage");
		categoryService.createCategory(c1);
		categoryService.createCategory(c2);
		categoryService.createCategory(c3);
	}

	public void createVideos() {
		v1 = new Video("video1", "videos/video1", new LinkedHashSet<>());
		v2 = new Video("video2", "videos/video2", new LinkedHashSet<>());
		v3 = new Video("video3", "videos/video3", new LinkedHashSet<>());
		videoRepository.save(v1);
		videoRepository.save(v2);
		videoRepository.save(v3);
	}

	public void createTrainings() {
		Set<Video> vs = new LinkedHashSet();
		vs.add(v1);
		vs.add(v2);
		ti1 = new Training("titre1", t1, c1, vs, new LinkedHashSet<Comment>(), "Desc 1 blablabla", Level.INTERMEDIATE, 15,
				15d, true);
		trainingService.createTraining(ti1, ti1.getCategory(), new LinkedList<>(vs));
		vs.removeAll(vs);
		vs.add(v3);
		ti2 = new Training("titre2", t1, c2, vs, new LinkedHashSet<Comment>(), "Desc 1 héhéhé", Level.CONFIRMED, 45, 75d,
				false);
		trainingService.createTraining(ti2, ti2.getCategory(), new LinkedList<>(vs));
	}

}
