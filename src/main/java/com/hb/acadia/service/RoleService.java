package com.hb.acadia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.User;
import com.hb.acadia.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class
 * 
 * @author simonaliotti
 *
 */
@Slf4j
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;

	/**
	 * Method to get a role by roleName
	 * 
	 * @param roleName
	 * @return the given role
	 */
	public Role getRoleByRoleName(String roleName) {
		return roleRepository.findRoleByRoleName(roleName);
	}

	/**
	 * Method to get all roles
	 * 
	 * @return a list of roles
	 */
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	/**
	 * Method to create role
	 * 
	 * @param role
	 * @return the created Role
	 */
	@Transactional
	public Role createRole(Role role) {
		return roleRepository.save(role);

	}

	/**
	 * Delete a role Delete all roles. Set users's role to null before deleting role
	 * 
	 * @param role
	 * @throws IllegalAccessException
	 */
	@Transactional
	public void deleteRole(Role role) throws IllegalAccessException {
		List<User> users = userService.getUserByRole(role);
		if (users.isEmpty()) {
			roleRepository.delete(role);
		} else {
			throw new IllegalAccessException();
		}

	}

	/**
	 * Delete all roles. the roles
	 * 
	 * @throws IllegalAccessException
	 */
	@Transactional
	public void deleteAll() throws IllegalAccessException {
		for (Role role : getRoles()) {
			deleteRole(role);
		}
	}
}
