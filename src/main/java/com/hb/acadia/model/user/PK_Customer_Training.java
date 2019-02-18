package com.hb.acadia.model.user;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.hb.acadia.model.Training;

@Embeddable
public class PK_Customer_Training  implements Serializable {

	private static final long serialVersionUID = -4999675871252402122L;

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
