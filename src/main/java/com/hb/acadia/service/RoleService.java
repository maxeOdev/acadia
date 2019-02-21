package com.hb.acadia.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
	 * Delete all roles. **** WARNINGS **** Be shure that no user are linked 
	 * @param role
	 */
	public void deleteRole(Role role) {
		roleRepository.delete(role);
	}

	/**
	 * Delete all roles. **** WARNINGS **** Be shure that no user are linked 
	 * the roles
	 */
	public void deleteAll() {
		roleRepository.deleteAll();
	}
}
