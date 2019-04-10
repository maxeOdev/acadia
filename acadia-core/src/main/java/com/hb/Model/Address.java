package com.hb.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
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
	

	public Address() {}

	/**
	 * @param number
	 * @param road
	 * @param roadType
	 * @param cp
	 * @param city
	 * @param country
	 */
	public Address(@NotNull int number, @NotNull String road, @NotNull String roadType, @NotNull String cp,
			@NotNull String city, @NotNull String country) {
		this.number = number;
		this.road = road;
		this.roadType = roadType;
		this.cp = cp;
		this.city = city;
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + number;
		result = prime * result + ((road == null) ? 0 : road.hashCode());
		result = prime * result + ((roadType == null) ? 0 : roadType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
		if (id != other.id)
			return false;
		if (number != other.number)
			return false;
		if (road == null) {
			if (other.road != null)
				return false;
		} else if (!road.equals(other.road))
			return false;
		if (roadType == null) {
			if (other.roadType != null)
				return false;
		} else if (!roadType.equals(other.roadType))
			return false;
		return true;
	}
	
}
