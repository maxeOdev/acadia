package com.hb.acadia.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Training;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
public class Trainer extends User {

	// nombre d'année d'experience
	@NotNull
	private int experience;

	// pour le payement
	@NotNull
	private long idStripe;

	// diplôme
	@NotNull
	private String qualifications;

	// auto-publish
	@NotNull
	private boolean isCertified;
	
	@OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
	private Set<Training> trainings;

}
