package com.hb.acadia.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.Comment;
import com.hb.acadia.model.Training;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Trainer extends User {


	// nombre d'année d'experience
	@NotNull
	private int experience;

	// pour le payement
	@NotNull
	private String idStripe;

	// diplôme
	@NotNull
	private String qualifications;

	// auto-publish
	@NotNull
	private boolean isCertified;

	@OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
	private Set<Training> trainings;

	public Trainer() {
		this.isCertified=false;
	}



	/**
	 *
	 * @param password
	 * @param name
	 * @param firstName
	 * @param mail
	 * @param address
	 * @param comments
	 * @param isActif
	 * @param experience
	 * @param qualifications
	 * @param trainings
	 */


	public Trainer(@NotNull String password, @NotNull String name, @NotNull String firstName, String mail,
			@NotNull Address address, Set<Comment> comments, boolean isActif,
			@NotNull int experience, @NotNull String qualifications,
			Set<Training> trainings) {
		super(password, name, firstName, mail, address, comments, isActif);
		this.experience = experience;
		this.qualifications = qualifications;
		this.isCertified = false;
		this.trainings = trainings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + experience;
		result = prime * result + (isCertified ? 1231 : 1237);
		result = prime * result + ((qualifications == null) ? 0 : qualifications.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainer other = (Trainer) obj;
		if (experience != other.experience)
			return false;
		if (isCertified != other.isCertified)
			return false;
		if (qualifications == null) {
			if (other.qualifications != null)
				return false;
		} else if (!qualifications.equals(other.qualifications))
			return false;
		return true;
	}


	

	
	
}
