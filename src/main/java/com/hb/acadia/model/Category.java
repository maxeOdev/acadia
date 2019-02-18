package com.hb.acadia.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.hb.acadia.model.user.Training;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(nullable=false, unique=true)
	private String name;
	
	@OneToMany(mappedBy="category", fetch=FetchType.LAZY)
	private List<Training> trainings;
	
	public Category() {
		
	}

	public Category(String name, List<Training> trainings) {
		this.name = name;
		this.trainings = trainings;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<Training> trainings) {
		this.trainings = trainings;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", trainings="
				+ trainings + "]";
	}
	
}
