package com.hb.acadia.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.Comment;
import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.Trainer;
import com.hb.acadia.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrainerServiceTest extends AbstractApplicationTest {

	private Trainer trainer1;
	private Trainer trainer2;

	@BeforeClass
	public static void startTest() {
		log.info("");
		log.info(
				"******************************************************* STARTING TRAINER TESTS ***************************************");
		log.info("");
	}

	/**
	 * Create a User with address
	 */
	@Before
	public void createData() {

		log.info("");
		log.info(
				"********************************************************* CREATING DATAS ***************************************");
		log.info("");

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
		Trainer trainer1 = new Trainer();
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
		BeanUtils.copyProperties(trainer1, (this.trainer1 = new Trainer()));
		trainerService.createTrainer(trainer1);

		/* Create User in database */
		Address address2 = new Address();
		address2.setCity("Clermont");
		address2.setCountry("France");
		address2.setCp("63000");
		address2.setNumber(67);
		address2.setRoad("Dupont");
		address2.setRoadType("rue");
		Trainer trainer2 = new Trainer();
		trainer2.setActif(true);
		trainer2.setFirstName("Anis");
		trainer2.setName("Lalami");
		trainer2.setEmail("alalami@gmail.com");
		trainer2.setPassword("titi");
		trainer2.setAddress(address2);
		trainer2.setComments(null);
		trainer2.setIdStripe("dmfgkjdfvnjkf6853764978");
		trainer2.setExperience(8);
		trainer2.setQualifications("phd");
		trainer2.setCertified(true);
		trainer2.setTrainings(null);
		BeanUtils.copyProperties(trainer2, (this.trainer2 = new Trainer()));
		trainerService.createTrainer(trainer2);

	}

	@After
	public void deleteDataAfterTest() {
		log.info("");
		log.info(
				"********************************************************* DELETING DATAS ***************************************");
		log.info("");

		// deleteAllUsers
		userService.deleteAll();
		try {
			roleService.deleteAll();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		this.trainer1 = null;
		this.trainer2 = null;

	}

	/**
	 * Test method getting User by idStripe
	 */
	@Test
	public void test_getTrainerByIdStripe() {
		log.info("");
		log.info(
				"******************************************************** Testing getTrainerByIdStripe ***************************************");
		log.info("");
		Trainer userFromGet = trainerService.getTrainerByIdStripe(this.trainer1.getIdStripe());
		Set<Comment> comments = userFromGet.getComments();
		assertEquals(this.trainer1.getAddress().getCity(), userFromGet.getAddress().getCity());
		assertEquals(this.trainer1.getAddress().getCountry(), userFromGet.getAddress().getCountry());
		assertEquals(this.trainer1.getAddress().getCp(), userFromGet.getAddress().getCp());
		assertEquals(this.trainer1.getAddress().getNumber(), userFromGet.getAddress().getNumber());
		assertEquals(this.trainer1.getAddress().getRoad(), userFromGet.getAddress().getRoad());
		assertEquals(this.trainer1.getAddress().getRoadType(), userFromGet.getAddress().getRoadType());
		assertEquals(this.trainer1.getFirstName(), userFromGet.getFirstName());
		assertEquals(this.trainer1.getName(), userFromGet.getName());
		assertEquals(this.trainer1.getUuid(), userFromGet.getUuid());
		assertEquals(this.trainer1.getEmail(), userFromGet.getEmail());
		assertEquals(this.trainer1.getIdStripe(), userFromGet.getIdStripe());
		assertEquals(this.trainer1.getExperience(), userFromGet.getExperience());
		assertEquals(this.trainer1.getQualifications(), userFromGet.getQualifications());
		assertEquals(this.trainer1.isCertified(), userFromGet.isCertified());
		assertThat(userFromGet.getComments().isEmpty());
		assertThat(userFromGet.getRole().getRoleName(), equalTo("ROLE_TRAINER"));

		assertTrue(Utils.verifyPassword(this.trainer1.getPassword(), userFromGet.getPassword()));
		assertNotNull(userFromGet.getId());

		Trainer userFromGet2 = trainerService.getTrainerByIdStripe(this.trainer2.getIdStripe());
		assertEquals(this.trainer2.getAddress().getCity(), userFromGet2.getAddress().getCity());
		assertEquals(this.trainer2.getAddress().getCountry(), userFromGet2.getAddress().getCountry());
		assertEquals(this.trainer2.getAddress().getCp(), userFromGet2.getAddress().getCp());
		assertEquals(this.trainer2.getAddress().getNumber(), userFromGet2.getAddress().getNumber());
		assertEquals(this.trainer2.getAddress().getRoad(), userFromGet2.getAddress().getRoad());
		assertEquals(this.trainer2.getAddress().getRoadType(), userFromGet2.getAddress().getRoadType());
		assertEquals(this.trainer2.getFirstName(), userFromGet2.getFirstName());
		assertEquals(this.trainer2.getName(), userFromGet2.getName());
		assertEquals(this.trainer2.getUuid(), userFromGet2.getUuid());
		assertEquals(this.trainer2.getEmail(), userFromGet2.getEmail());
		assertEquals(this.trainer2.getIdStripe(), userFromGet2.getIdStripe());
		assertEquals(this.trainer2.getExperience(), userFromGet2.getExperience());
		assertEquals(this.trainer2.getQualifications(), userFromGet2.getQualifications());
		assertEquals(this.trainer2.isCertified(), userFromGet2.isCertified());
		assertThat(userFromGet.getComments().isEmpty());
		assertThat(userFromGet.getRole().getRoleName(), equalTo("ROLE_TRAINER"));
		
		assertTrue(Utils.verifyPassword(this.trainer2.getPassword(), userFromGet2.getPassword()));
		assertNotNull(userFromGet2.getId());

	}

}
