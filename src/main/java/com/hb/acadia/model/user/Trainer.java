package com.hb.acadia.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.Comment;
import com.hb.acadia.model.Training;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
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

	public Trainer() {}

	/**
	 * @param experience
	 * @param qualifications
	 * @param isCertified
	 * @param trainings
	 */
	public Trainer(@NotNull String password, @NotNull String name, @NotNull String firstName, String mail,
			@NotNull Address address, Set<Comment> comments, boolean isActif,
			@NotNull int experience, @NotNull String qualifications, @NotNull boolean isCertified,
			Set<Training> trainings) {
		super(password, name, firstName, mail, address, comments, isActif);
		this.experience = experience;
		this.qualifications = qualifications;
		this.isCertified = isCertified;
		this.trainings = trainings;
	}
	
}
