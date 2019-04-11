package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findRoleByRoleName(String roleName);
}
