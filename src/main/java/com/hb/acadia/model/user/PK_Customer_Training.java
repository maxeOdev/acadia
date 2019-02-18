package com.hb.acadia.model.user;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.hb.acadia.model.Training;

@Embeddable
public class PK_Customer_Training {

	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Training training;

	public PK_Customer_Training() {}
	
	public PK_Customer_Training(User user, Training training) {
		this.user = user;
		this.training = training;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	@Override
	public String toString() {
		return "PK_Customer_Training [user=" + user + ", training=" + training
				+ "]";
	}
	
}
