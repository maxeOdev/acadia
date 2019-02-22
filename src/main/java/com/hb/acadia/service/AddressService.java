package com.hb.acadia.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.Address;
import com.hb.acadia.repository.AddressRepository;
import com.hb.acadia.repository.UserRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * Create an address
	 * @param address to create
	 * @return the created address 
	 */
	@Transactional
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}

	/**
	 * Delete an address
	 * @param address to delete
	 */
	@Transactional
	public void deleteAddress(Address address) {
		addressRepository.delete(address);
	}


}
