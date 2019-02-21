package com.hb.acadia.model.bill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Training;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
	private Training.Level difficulty;
	
	@NotNull
	private int duration;
	
	@NotNull
	private double price;
	
	// setter manuellement le trainer
	private String trainerName;
	
	// setter manuellement la catégorie
	private String categoryName;

	public TrainingForBill() {}
	
	public TrainingForBill(Training training) {
		this.uuid = training.getUuid();
		this.description = training.getDescription();
		this.difficulty = training.getDifficulty();
		this.duration = training.getDuration();
		this.price = training.getPrice();
		this.trainerName = training.getTrainer().getName();
		this.categoryName = training.getCategory().getName();
	}
	
	
	
}
