package com.hb.acadia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.User;
import com.hb.acadia.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class allowing many operations on User
 * 
 * @author simonaliotti
 *
 */
@Slf4j
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressService addressService;
	@Autowired
	RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Value("${saltkey}")
	private String saltKey;

	/**
	 * Get User by Uuid
	 * 
	 * @param uuid User
	 * @return the User
	 */
	public User getUserByUuid(String uuid) {
		return userRepository.findByUuid(uuid);

	}

	/**
	 * Get a list of Users by firsname
	 * 
	 * @param firstName
	 * @return List of Users
	 */
	public List<User> getUserByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	/**
	 * Get a list of Users by name
	 * 
	 * @param name
	 * @return List of Users
	 */
	public List<User> getUserByName(String name) {
		return userRepository.findByName(name);
	}

	/**
	 * Get User by Eemail
	 * 
	 * @param eemail
	 * @return the User
	 */
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * Create a user
	 * 
	 * @param user
	 * @return the created user
	 */
	@Transactional
	public User createUser(User user) {

		// salt and hash password
		String password = saltKey + user.getPassword() + saltKey;
		user.setPassword(bCryptPasswordEncoder.encode(password));

		// save the address in database before saving the user
		user.setAddress(addressService.createAddress(user.getAddress()));

		// set role on user
		for (Role role : roleService.getRoles()) {
			if (role.getRoleName().equals("ROLE_CUSTOMER")) {
				user.setRole(role);
			}
		}
		// save the user
		return userRepository.save(user);
	}

	/**
	 * Delete user. First delete the user and then his address. ********** Will have
	 * to be aware to delete comment first when needed *************
	 * 
	 * @param user
	 */
	@Transactional
	public void deleteUser(User user) {
		// Delete user
		userRepository.delete(user);
		log.info(user.getAddress().getCity());
		// Delete the his address
		addressService.deleteAddress(user.getAddress());
	}

	/**
	 * Delete all users
	 */
	@Transactional
	public void deleteAll() {
		for (User user : userRepository.findAll()) {
			deleteUser(user);
		}
	}

	/**
	 * Update an object /  WARNING *** Do no use for update password *** WARNING
	 * @param user to update
	 * @return the updated user
	 */
	@Transactional
	public User updateUser(User user) {
		// save the address in database before saving the user
		user.setAddress(addressService.updateAddress(user.getAddress()));
		return userRepository.save(user);
		
	}
}
