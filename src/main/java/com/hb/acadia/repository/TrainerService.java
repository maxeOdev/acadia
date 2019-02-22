package com.hb.acadia.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.Trainer;
import com.hb.acadia.utils.Utils;

/**
 * Service class allowing operations on trainer
 * 
 * @author simonaliotti
 *
 */
@Service
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Method to create a trainer
	 * 
	 * @param the trainer to create
	 * @return the created trainer
	 */
	public Trainer createTrainer(Trainer trainer) {
		// salt and hash password
		trainer.setPassword(Utils.encryptAndSalt(trainer.getPassword()));
		// save the address in database before saving the trainer
		trainer.setAddress(addressRepository.save(trainer.getAddress()));
		// set role on trainer
		Role role = roleRepository.findRoleByRoleName("ROLE_TRAINER");
		trainer.setRole(role);
		return trainerRepository.save(trainer);
	}

	/**
	 * Update a trainer/ *** WARNING *** Do no use for update password *** WARNING
	 * ***
	 * 
	 * @param trainer to update
	 * @return the updated trainer
	 */
	public Trainer updateTrainer(Trainer trainer) {
		// save the address in database before saving the tainer
		trainer.setAddress(addressRepository.save(trainer.getAddress()));
		return trainerRepository.save(trainer);

	}

}
