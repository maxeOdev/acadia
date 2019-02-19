package com.hb.acadia.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Category {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Training> trainings;

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", trainings=" + trainings + "]";
	}

}
