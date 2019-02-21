package com.hb.acadia.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Training> trainings;

	public Category() {	}

	public Category(String name, Set<Training> trainings) {
		this.name = name.toLowerCase();
		this.trainings = trainings;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
