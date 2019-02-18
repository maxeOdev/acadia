package com.hb.acadia.model.user;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
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

	

	@Override
	public String toString() {
		return "Trainer [experience=" + experience + ", idStrip=" + idStrip + ", qualification=" + qualification
				+ ", isCertified=" + isCertified + "]";
	}
	
	
}
