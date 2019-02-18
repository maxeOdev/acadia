package com.hb.acadia.model.user;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Customer extends User {

	@Override
	public String toString() {
		return "Customer []";
	}

	
	
}
