package com.hb.acadia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.Address;
import com.hb.acadia.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	/**
	 * Create an address
	 * @param address to create
	 * @return the created address 
	 */
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}

	/**
	 * Delete an address
	 * @param address to delete
	 */
	public void deleteAddress(Address address) {
		addressRepository.delete(address);
	}

	/**
	 * Update the address
	 * @param address
	 * @return the updated Address
	 */
	public Address updateAddress(Address address) {
		return addressRepository.save(address);
	}
}
