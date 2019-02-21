package com.hb.acadia.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Test class on User Service
 * 
 * @author simonaliotti
 *
 */
@Slf4j
public class UserServiceTest extends AbstractApplicationTest {

	private User user1;
	private User user2;

	@Value("${saltkey}")
	private String saltKey;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@BeforeClass
	public static void startTest() {
		log.info("");
		log.info(
				"******************************************************* STARTING USER TEST ***************************************");
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
		role.setRoleName("ROLE_CUSTOMER");
		roleService.createRole(role);

		/* Create User in database */
		Address address = new Address();
		address.setCity("Toulouse");
		address.setCountry("France");
		address.setCp("31000");
		address.setNumber(24);
		address.setRoad("République");
		address.setRoadType("Avenue");
		User user1 = new User();
		user1.setActif(true);
		user1.setFirstName("Simon");
		user1.setName("Aliotti");
		user1.setEmail("simone.aliot@gmail.com");
		user1.setPassword("toto");
		user1.setAddress(address);
		user1.setComments(null);
		BeanUtils.copyProperties(user1, (this.user1 = new User()));
		userService.createUser(user1);

		/* Create User in database */
		Address address2 = new Address();
		address2.setCity("Clermont");
		address2.setCountry("France");
		address2.setCp("63000");
		address2.setNumber(67);
		address2.setRoad("Dupont");
		address2.setRoadType("rue");
		User user2 = new User();
		user2.setActif(true);
		user2.setFirstName("Anis");
		user2.setName("Lalami");
		user2.setEmail("alalami@gmail.com");
		user2.setPassword("titi");
		user2.setAddress(address2);
		user1.setComments(null);
		BeanUtils.copyProperties(user2, (this.user2 = new User()));
		userService.createUser(user2);

	}

	@After
	public void deleteDataAfterTest() {
		log.info("");
		log.info(
				"********************************************************* DELETING DATAS ***************************************");
		log.info("");

		// deleteAllUsers

		userService.deleteAll();
		roleService.deleteAll();

		this.user1 = null;
		this.user2 = null;

	}

	/**
	 * Test method getting User by Uuid
	 */
	@Test
	public void test_getUserByUuid() {

		log.debug("");
		log.debug(
				"******************************************************** Testing getUserByUuid ***************************************");
		log.debug("");
		// test getUserbByUuid
		User userFromGet = userService.getUserByUuid(this.user1.getUuid());
		assertEquals(this.user1.getAddress().getCity(), userFromGet.getAddress().getCity());
		assertEquals(this.user1.getAddress().getCountry(), userFromGet.getAddress().getCountry());
		assertEquals(this.user1.getAddress().getCp(), userFromGet.getAddress().getCp());
		assertEquals(this.user1.getAddress().getNumber(), userFromGet.getAddress().getNumber());
		assertEquals(this.user1.getAddress().getRoad(), userFromGet.getAddress().getRoad());
		assertEquals(this.user1.getAddress().getRoadType(), userFromGet.getAddress().getRoadType());
		assertEquals(this.user1.getFirstName(), userFromGet.getFirstName());
		assertEquals(this.user1.getName(), userFromGet.getName());
		assertEquals(this.user1.getUuid(), userFromGet.getUuid());
		assertEquals(this.user1.getEmail(), userFromGet.getEmail());
		assertTrue(bCryptPasswordEncoder.matches((saltKey + this.user1.getPassword() + saltKey),
				userFromGet.getPassword()));
		assertNotNull(userFromGet.getId());

		User userFromGet2 = userService.getUserByUuid(this.user2.getUuid());
		assertEquals(this.user2.getAddress().getCity(), userFromGet2.getAddress().getCity());
		assertEquals(this.user2.getAddress().getCountry(), userFromGet2.getAddress().getCountry());
		assertEquals(this.user2.getAddress().getCp(), userFromGet2.getAddress().getCp());
		assertEquals(this.user2.getAddress().getNumber(), userFromGet2.getAddress().getNumber());
		assertEquals(this.user2.getAddress().getRoad(), userFromGet2.getAddress().getRoad());
		assertEquals(this.user2.getAddress().getRoadType(), userFromGet2.getAddress().getRoadType());
		assertEquals(this.user2.getFirstName(), userFromGet2.getFirstName());
		assertEquals(this.user2.getName(), userFromGet2.getName());
		assertEquals(this.user2.getUuid(), userFromGet2.getUuid());
		assertEquals(this.user2.getEmail(), userFromGet2.getEmail());
		assertTrue(bCryptPasswordEncoder.matches((saltKey + this.user2.getPassword() + saltKey),
				userFromGet2.getPassword()));
		assertNotNull(userFromGet2.getId());

	}

	/**
	 * Test method getting Users by firstName
	 */
	@Test
	public void test_getUserByFirstName() {

		log.debug("");
		log.debug(
				"******************************************************** Testing getUserByFirstName ***************************************");
		log.debug("");
		List<User> usersFromGet = userService.getUserByFirstName(this.user1.getFirstName());
		for (User userFromGet : usersFromGet) {
			assertEquals(this.user1.getAddress().getCity(), userFromGet.getAddress().getCity());
			assertEquals(this.user1.getAddress().getCountry(), userFromGet.getAddress().getCountry());
			assertEquals(this.user1.getAddress().getCp(), userFromGet.getAddress().getCp());
			assertEquals(this.user1.getAddress().getNumber(), userFromGet.getAddress().getNumber());
			assertEquals(this.user1.getAddress().getRoad(), userFromGet.getAddress().getRoad());
			assertEquals(this.user1.getAddress().getRoadType(), userFromGet.getAddress().getRoadType());
			assertEquals(this.user1.getFirstName(), userFromGet.getFirstName());
			assertEquals(this.user1.getName(), userFromGet.getName());
			assertEquals(this.user1.getUuid(), userFromGet.getUuid());
			assertEquals(this.user1.getEmail(), userFromGet.getEmail());
			assertTrue(bCryptPasswordEncoder.matches((saltKey + this.user1.getPassword() + saltKey),
					userFromGet.getPassword()));
			assertNotNull(userFromGet.getId());
		}

		List<User> usersFromGet2 = userService.getUserByFirstName(this.user2.getFirstName());
		for (User userFromGet2 : usersFromGet2) {
			assertEquals(this.user2.getAddress().getCity(), userFromGet2.getAddress().getCity());
			assertEquals(this.user2.getAddress().getCountry(), userFromGet2.getAddress().getCountry());
			assertEquals(this.user2.getAddress().getCp(), userFromGet2.getAddress().getCp());
			assertEquals(this.user2.getAddress().getNumber(), userFromGet2.getAddress().getNumber());
			assertEquals(this.user2.getAddress().getRoad(), userFromGet2.getAddress().getRoad());
			assertEquals(this.user2.getAddress().getRoadType(), userFromGet2.getAddress().getRoadType());
			assertEquals(this.user2.getFirstName(), userFromGet2.getFirstName());
			assertEquals(this.user2.getName(), userFromGet2.getName());
			assertEquals(this.user2.getUuid(), userFromGet2.getUuid());
			assertEquals(this.user2.getEmail(), userFromGet2.getEmail());
			assertTrue(bCryptPasswordEncoder.matches((saltKey + this.user2.getPassword() + saltKey),
					userFromGet2.getPassword()));
			assertNotNull(userFromGet2.getId());
		}
	}

	/**
	 * Test method getting Users by Name
	 */
	@Test
	public void test_getUserByName() {
		log.debug("");
		log.debug(
				"******************************************************** Testing getUserByName ***************************************");
		log.debug("");
		List<User> usersFromGet = userService.getUserByName(this.user1.getName());
		for (User userFromGet : usersFromGet) {
			assertEquals(this.user1.getAddress().getCity(), userFromGet.getAddress().getCity());
			assertEquals(this.user1.getAddress().getCountry(), userFromGet.getAddress().getCountry());
			assertEquals(this.user1.getAddress().getCp(), userFromGet.getAddress().getCp());
			assertEquals(this.user1.getAddress().getNumber(), userFromGet.getAddress().getNumber());
			assertEquals(this.user1.getAddress().getRoad(), userFromGet.getAddress().getRoad());
			assertEquals(this.user1.getAddress().getRoadType(), userFromGet.getAddress().getRoadType());
			assertEquals(this.user1.getFirstName(), userFromGet.getFirstName());
			assertEquals(this.user1.getName(), userFromGet.getName());
			assertEquals(this.user1.getUuid(), userFromGet.getUuid());
			assertEquals(this.user1.getEmail(), userFromGet.getEmail());
			assertTrue(bCryptPasswordEncoder.matches((saltKey + this.user1.getPassword() + saltKey),
					userFromGet.getPassword()));
			assertNotNull(userFromGet.getId());
		}

		List<User> usersFromGet2 = userService.getUserByName(this.user2.getName());
		for (User userFromGet2 : usersFromGet2) {
			assertEquals(this.user2.getAddress().getCity(), userFromGet2.getAddress().getCity());
			assertEquals(this.user2.getAddress().getCountry(), userFromGet2.getAddress().getCountry());
			assertEquals(this.user2.getAddress().getCp(), userFromGet2.getAddress().getCp());
			assertEquals(this.user2.getAddress().getNumber(), userFromGet2.getAddress().getNumber());
			assertEquals(this.user2.getAddress().getRoad(), userFromGet2.getAddress().getRoad());
			assertEquals(this.user2.getAddress().getRoadType(), userFromGet2.getAddress().getRoadType());
			assertEquals(this.user2.getFirstName(), userFromGet2.getFirstName());
			assertEquals(this.user2.getName(), userFromGet2.getName());
			assertEquals(this.user2.getUuid(), userFromGet2.getUuid());
			assertEquals(this.user2.getEmail(), userFromGet2.getEmail());
			assertTrue(bCryptPasswordEncoder.matches((saltKey + this.user2.getPassword() + saltKey),
					userFromGet2.getPassword()));
			assertNotNull(userFromGet2.getId());
		}
	}

	/**
	 * Test method getting User by mail
	 */
	@Test
	public void test_getUserByMail() {
		log.debug("");
		log.debug(
				"******************************************************** Testing getUserByMail ***************************************");
		log.debug("");
		User userFromGet = userService.getUserByEmail(this.user1.getEmail());
		assertEquals(this.user1.getAddress().getCity(), userFromGet.getAddress().getCity());
		assertEquals(this.user1.getAddress().getCountry(), userFromGet.getAddress().getCountry());
		assertEquals(this.user1.getAddress().getCp(), userFromGet.getAddress().getCp());
		assertEquals(this.user1.getAddress().getNumber(), userFromGet.getAddress().getNumber());
		assertEquals(this.user1.getAddress().getRoad(), userFromGet.getAddress().getRoad());
		assertEquals(this.user1.getAddress().getRoadType(), userFromGet.getAddress().getRoadType());
		assertEquals(this.user1.getFirstName(), userFromGet.getFirstName());
		assertEquals(this.user1.getName(), userFromGet.getName());
		assertEquals(this.user1.getUuid(), userFromGet.getUuid());
		assertEquals(this.user1.getEmail(), userFromGet.getEmail());
		assertTrue(bCryptPasswordEncoder.matches((saltKey + this.user1.getPassword() + saltKey),
				userFromGet.getPassword()));
		assertNotNull(userFromGet.getId());

		User userFromGet2 = userService.getUserByEmail(this.user2.getEmail());
		assertEquals(this.user2.getAddress().getCity(), userFromGet2.getAddress().getCity());
		assertEquals(this.user2.getAddress().getCountry(), userFromGet2.getAddress().getCountry());
		assertEquals(this.user2.getAddress().getCp(), userFromGet2.getAddress().getCp());
		assertEquals(this.user2.getAddress().getNumber(), userFromGet2.getAddress().getNumber());
		assertEquals(this.user2.getAddress().getRoad(), userFromGet2.getAddress().getRoad());
		assertEquals(this.user2.getAddress().getRoadType(), userFromGet2.getAddress().getRoadType());
		assertEquals(this.user2.getFirstName(), userFromGet2.getFirstName());
		assertEquals(this.user2.getName(), userFromGet2.getName());
		assertEquals(this.user2.getUuid(), userFromGet2.getUuid());
		assertEquals(this.user2.getEmail(), userFromGet2.getEmail());
		assertTrue(bCryptPasswordEncoder.matches((saltKey + this.user2.getPassword() + saltKey),
				userFromGet2.getPassword()));
		assertNotNull(userFromGet2.getId());
	}

	/**
	 * Method test creating a user
	 */
	@Test
	public void test_createUser() {
		userRepository.deleteAll();
		addressRepository.deleteAll();
		this.user1 = null;
		this.user2 = null;

		/* User */
		Address address = new Address();
		address.setCity("Toulouse");
		address.setCountry("France");
		address.setCp("31000");
		address.setNumber(24);
		address.setRoad("République");
		address.setRoadType("Avenue");

		User user1 = new User();
		user1.setActif(true);
		user1.setFirstName("Simon");
		user1.setName("Aliotti");
		user1.setEmail("simone.aliot@gmail.com");
		user1.setPassword("toto");
		user1.setAddress(address);
		user1.setComments(null);

		// * Create Roles in database
		Role role = new Role();
		role.setRoleName("ROLE_CUSTOMER");
		roleService.createRole(role);

		BeanUtils.copyProperties(user1, (this.user1 = new User()));
		User createdUser = userService.createUser(user1);

		// Assertions
		assertEquals(this.user1.getAddress().getCity(), createdUser.getAddress().getCity());
		assertEquals(this.user1.getAddress().getCountry(), createdUser.getAddress().getCountry());
		assertEquals(this.user1.getAddress().getCp(), createdUser.getAddress().getCp());
		assertEquals(this.user1.getAddress().getId(), createdUser.getAddress().getId());
		assertEquals(this.user1.getAddress().getNumber(), createdUser.getAddress().getNumber());
		assertEquals(this.user1.getAddress().getRoad(), createdUser.getAddress().getRoad());
		assertEquals(this.user1.getAddress().getRoadType(), createdUser.getAddress().getRoadType());
		assertEquals(this.user1.getFirstName(), createdUser.getFirstName());
		assertEquals(this.user1.getName(), createdUser.getName());
		assertEquals(this.user1.getEmail(), createdUser.getEmail());
		assertEquals(this.user1.getUuid(), createdUser.getUuid());
		assertEquals("ROLE_CUSTOMER", createdUser.getRole().getRoleName());
		assertNotNull(createdUser.getUuid());
		assertTrue(bCryptPasswordEncoder.matches((saltKey + "toto" + saltKey), createdUser.getPassword()));

	}

	@Test
	public void test_deleteUser() {

		int numberOfUserBeforeDelete = (int) userRepository.count();

		User userToDelete = userService.getUserByUuid(this.user1.getUuid());
		userService.deleteUser(userToDelete);

		int numberOfUserAfterDelete = (int) userRepository.count();

		assertEquals((numberOfUserBeforeDelete - 1), numberOfUserAfterDelete);

	}

	@Test
	public void test_updateUser() {
		User userToUpdate = userService.getUserByUuid(this.user1.getUuid());

		Address addressToUpdate = new Address();
		addressToUpdate.setCity("Toulouse-updated");
		addressToUpdate.setCountry("France-updated");
		addressToUpdate.setCp("31000-updated");
		addressToUpdate.setNumber(99);
		addressToUpdate.setRoad("République-updated");
		addressToUpdate.setRoadType("Avenue-updated");
		userToUpdate.setActif(false);
		userToUpdate.setFirstName("Simon-updated");
		userToUpdate.setName("Aliotti-updated");
		userToUpdate.setEmail("simone.aliot@gmail.com-updated");
		userToUpdate.setAddress(addressToUpdate);
		userToUpdate.setComments(null);

		userToUpdate = userService.updateUser(userToUpdate);
		
		assertEquals("Toulouse-updated", userToUpdate.getAddress().getCity());
		assertEquals("France-updated", userToUpdate.getAddress().getCountry());
		assertEquals("31000-updated", userToUpdate.getAddress().getCp());
		assertEquals(99, userToUpdate.getAddress().getNumber());
		assertEquals("République-updated", userToUpdate.getAddress().getRoad());
		assertEquals("Avenue-updated", userToUpdate.getAddress().getRoadType());
		assertEquals(false, userToUpdate.isActif());
		assertEquals("Simon-updated", userToUpdate.getFirstName());
		assertEquals("Aliotti-updated", userToUpdate.getName());
		assertEquals("simone.aliot@gmail.com-updated", userToUpdate.getEmail());
		assertEquals(null, userToUpdate.getComments());

		
		
		
		
		
		
		
	}
}
