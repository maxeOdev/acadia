package com.hb.acadia.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleServiceTest extends AbstractApplicationTest {

	@BeforeClass
	public static void startTest() {
		log.info("");
		log.info(
				"******************************************************* STARTING ROLE TESTS ***************************************");
		log.info("");
	}

	@Before
	public void createData() {

		try {
			roleService.deleteAll();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		log.info("");
		log.info(
				"********************************************************* CREATING DATAS ***************************************");
		log.info("");

		/* Delete every roles */
		roleRepository.deleteAll();
		
		/* Create roles in db */
		Role role1 = new Role();
		role1.setRoleName("ROLE_CUSTOMER");
		roleService.createRole(role1);

		Role role2 = new Role();
		role2.setRoleName("ROLE_ADMIN");
		roleService.createRole(role2);

	}

	@After
	public void deleteDataAfterTest() {
		log.info("");
		log.info(
				"********************************************************* DELETING DATAS ***************************************");
		log.info("");

		if (userRepository.count() != 0) {
			userService.deleteAll();
		}

		// delete all roles
		try {
			roleService.deleteAll();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_createRole() {
		/* delete roles already created */
		try {
			roleService.deleteAll();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		assertEquals(0, roleRepository.count());

		/* create roles */
		Role role1 = new Role();
		role1.setRoleName("ROLE_CUSTOMER");
		roleService.createRole(role1);

		Role role2 = new Role();
		role2.setRoleName("ROLE_ADMIN");
		roleService.createRole(role2);

		/* assertions */
		assertThat(roleService.getRoles().size(), equalTo(2));
		assertThat(roleService.getRoles().get(0), notNullValue());
		assertThat(roleService.getRoles().get(1), notNullValue());
		assertThat(roleService.getRoles().get(0).getRoleName(), equalTo("ROLE_CUSTOMER"));
		assertThat(roleService.getRoles().get(0).getId(), notNullValue());
		assertThat(roleService.getRoles().get(1).getRoleName(), equalTo("ROLE_ADMIN"));
		assertThat(roleService.getRoles().get(1).getId(), notNullValue());

	}

	/**
	 * Test method getting role by roleName
	 */
	@Test
	public void test_getRoleByRoleName() {
		String roleName1 = "ROLE_CUSTOMER";
		String roleName2 = "ROLE_ADMIN";

		assertThat((roleService.getRoleByRoleName(roleName1)).getRoleName(), equalTo(roleName1));
		assertThat((roleService.getRoleByRoleName(roleName2)).getRoleName(), equalTo(roleName2));
	}

	/**
	 * Test method getting all roles
	 */
	@Test
	public void test_getRoles() {

		assertEquals("ROLE_CUSTOMER", roleService.getRoles().get(0).getRoleName());
		assertEquals("ROLE_ADMIN", roleService.getRoles().get(1).getRoleName());

	}

	/**
	 * Test method deleting a role
	 */
	@Test
	public void test_deleteRole() {

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
		userService.createUser(user2);

		userService.deleteAll();

		int numberOfRoleBeforeDelete = (int) roleRepository.count();
		Role role1 = roleService.getRoleByRoleName("ROLE_CUSTOMER");
		try {
			roleService.deleteRole(role1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		int numberOfRoleAfterDelete = (int) roleRepository.count();

		/* verify that role "ROLE_CUSTOMER" has been deleted */
		assertTrue(numberOfRoleAfterDelete == (numberOfRoleBeforeDelete - 1));

	}

	/**
	 * Test method deleting a role
	 * 
	 * @throws IllegalAccessException
	 */
	@Test(expected = IllegalAccessException.class)
	public void test_deleteRole2() throws IllegalAccessException {

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
		userService.createUser(user2);

		int numberOfRoleBeforeDelete = (int) roleRepository.count();
		Role role1 = roleService.getRoleByRoleName("ROLE_CUSTOMER");

		roleService.deleteRole(role1);

	}

	/**
	 * Test method deleting all roles
	 * @throws IllegalAccessException 
	 */
	@Test
	public void test_deleteRoles() throws IllegalAccessException {
		int numberOfRoleBeforeDelete = (int) roleRepository.count();
		roleService.deleteAll();
		int numberOfRoleAfterDelete = (int) roleRepository.count();

		assertTrue(numberOfRoleAfterDelete != numberOfRoleBeforeDelete);
		assertTrue(numberOfRoleAfterDelete == 0);
	}
}
