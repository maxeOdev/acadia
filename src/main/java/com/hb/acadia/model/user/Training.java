package com.hb.acadia.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Training {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String uuid;
	@NotNull
	private String description;
	@NotNull
	private String difficulty;
	@NotNull
	private String duration;
	@NotNull
	private String price;

	private boolean isActive;

	@Override
	public String toString() {
		return "Training [id=" + id + ", uuid=" + uuid + ", description=" + description + ", difficulty=" + difficulty
				+ ", duration=" + duration + ", price=" + price + ", isActive=" + isActive + "]";
	}
	
	
	

	
}
