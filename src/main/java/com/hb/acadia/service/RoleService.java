package com.hb.acadia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.user.Role;
import com.hb.acadia.repository.RoleRepository;

/**
 * Service class
 * 
 * @author simonaliotti
 *
 */
@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	
	/**
	 * Method to get a Role by Id
	 * @param id
	 * @return the given role
	 */
	public Role getRoleById(long id) {
		return roleRepository.findById(id);

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
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	/**
	 * Delete a role
	 * 
	 * @param role
	 */
	public void deleteRole(Role role) {
		roleRepository.delete(role);
	}

	/**
	 * Delete all roles. **** WARNINGS **** Be shure that no user are connected to
	 * the roles
	 */
	public void deleteAll() {
		roleRepository.deleteAll();
	}
}
