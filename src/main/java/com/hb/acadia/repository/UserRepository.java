package com.hb.acadia.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.user.User;


public interface UserRepository extends JpaRepository<User, Long>{

}
