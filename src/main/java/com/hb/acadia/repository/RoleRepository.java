package com.hb.acadia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * 
	 * @param id
	 * @return the given role
	 */
	Role findById(long id);

}
