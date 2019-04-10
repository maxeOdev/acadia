package com.hb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.Model.user.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

	/**
	 * 
	 * @param idStripe
	 * @return the given trainer
	 */
	Trainer findByIdStripe(String idStripe);
}
