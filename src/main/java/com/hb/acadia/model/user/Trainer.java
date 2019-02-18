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
	private String idStripe;

	//diplôme
	@NotNull
	private String qualification;
	
	//auto-publish
	@NotNull
	private boolean isCertified;

	

	@Override
	public String toString() {
		return "Trainer [experience=" + experience + ", idStrip=" + idStripe + ", qualification=" + qualification
				+ ", isCertified=" + isCertified + "]";
	}
	
	
}
