package com.hb.acadia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.user.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

}
