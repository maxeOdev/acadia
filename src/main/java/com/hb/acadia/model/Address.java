package com.hb.acadia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity

public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull 
	private int number;
	@NotNull 
	private String road;
	@NotNull 
	private String roadType;
	@NotNull 
	private String cp;
	@NotNull 
	private String city;
	@NotNull 
	private String country;
	
	
	
	@Override
	public String toString() {
		return "Adress [id=" + id + ", number=" + number + ", road=" + road + ", roadType=" + roadType + ", cp=" + cp
				+ ", city=" + city + ", country=" + country + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull 
	private int number;
	@NotNull 
	private String road;
	@NotNull 
	private String roadType;
	@NotNull 
	private String cp;
	@NotNull 
	private String city;
	@NotNull 
	private String country;
	
	
	
	@Override
	public String toString() {
		return "Adress [id=" + id + ", number=" + number + ", road=" + road + ", roadType=" + roadType + ", cp=" + cp
				+ ", city=" + city + ", country=" + country + "]";
	}
	
	
	
}
