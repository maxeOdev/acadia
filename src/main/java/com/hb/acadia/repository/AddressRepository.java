package com.hb.acadia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
