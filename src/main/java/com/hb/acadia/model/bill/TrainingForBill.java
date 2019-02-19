package com.hb.acadia.model.bill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TrainingForBill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	// avoir le même id que le training d'origine
	private String uuid;
	@NotNull
	private String description;
	@NotNull
	private String difficulty;
	@NotNull
	private String duration;
	@NotNull
	private String price;
	// setter manuellement le trainer
	private String trainerName;
	// setter manuellement la catégorie
	private String categoryName;
	@Override
	public String toString() {
		return "TrainingForBill [id=" + id + ", uuid=" + uuid + ", description=" + description + ", difficulty="
				+ difficulty + ", duration=" + duration + ", price=" + price + ", trainerName=" + trainerName
				+ ", categoryName=" + categoryName + "]";
	}

	
}
