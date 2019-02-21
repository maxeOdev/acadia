package com.hb.acadia.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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
	public Trainer(@NotNull int experience, @NotNull String qualifications, @NotNull boolean isCertified,
			Set<Training> trainings) {
		this.experience = experience;
		this.qualifications = qualifications;
		this.isCertified = isCertified;
		this.trainings = trainings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + experience;
		result = prime * result + (int) (idStripe ^ (idStripe >>> 32));
		result = prime * result + (isCertified ? 1231 : 1237);
		result = prime * result + ((qualifications == null) ? 0 : qualifications.hashCode());
		result = prime * result + ((trainings == null) ? 0 : trainings.hashCode());
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
		User u = (User) obj;
		if (!u.equals(obj))
			return false;
		Trainer other = (Trainer) obj;
		if (experience != other.experience)
			return false;
		if (idStripe != other.idStripe)
			return false;
		if (isCertified != other.isCertified)
			return false;
		if (qualifications == null) {
			if (other.qualifications != null)
				return false;
		} else if (!qualifications.equals(other.qualifications))
			return false;
		if (trainings == null) {
			if (other.trainings != null)
				return false;
		} else if (!trainings.equals(other.trainings))
			return false;
		return true;
	}
	
}
