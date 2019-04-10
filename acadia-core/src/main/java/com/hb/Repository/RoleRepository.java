package com.hb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.Model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findRoleByRoleName(String roleName);
}
