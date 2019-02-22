package com.hb.acadia.repository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.Trainer;
import com.hb.acadia.model.user.User;
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
	@Transactional
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
	@Transactional
	public Trainer updateTrainer(Trainer trainer) {
		// save the address in database before saving the tainer
		trainer.setAddress(addressRepository.save(trainer.getAddress()));
		return trainerRepository.save(trainer);

	}

	/**
	 * 	This method disable a trainer. Training and address not deleted 
	 * @param the trainer to delete
	 */
	@Transactional
	public void deleteTrainer(Trainer trainer) {
		trainer.setActif(false);
		
	}
	
	/**
	 * Update a trainer's password
	 * @param trainer
	 * @return the updated trainer
	 */
	@Transactional 
	public Trainer updatePasswordTrainer(Trainer trainer) {
		trainer.setPassword(Utils.encryptAndSalt(trainer.getPassword()));
		return trainer;
	}
	
	/**
	 * Get a trainer by idStripe
	 * @param idStripe
	 * @return the given trainer
	 */
	public Trainer getTrainerByIdStripe(String idStripe) {
		return trainerRepository.findByIdStripe(idStripe);
	}

}
