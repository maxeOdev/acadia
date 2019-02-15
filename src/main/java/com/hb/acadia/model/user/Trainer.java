package com.hb.acadia.model.user;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Trainer extends User {

	
	//nombre d'année d'experience
	@NotNull
	private int experience;
	
	//pour le payement
	@NotNull
	private String idStrip;

	//diplôme
	@NotNull
	private String qualification;
	
	//auto-publish
	@NotNull
	private boolean isCertified;

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getIdStrip() {
		return idStrip;
	}

	public void setIdStrip(String idStrip) {
		this.idStrip = idStrip;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public boolean isCertified() {
		return isCertified;
	}

	public void setCertified(boolean isCertified) {
		this.isCertified = isCertified;
	}

	@Override
	public String toString() {
		return "Trainer [experience=" + experience + ", idStrip=" + idStrip + ", qualification=" + qualification
				+ ", isCertified=" + isCertified + "]";
	}
	
	
}
