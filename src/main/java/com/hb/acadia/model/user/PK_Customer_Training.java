package com.hb.acadia.model.user;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.hb.acadia.model.Training;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PK_Customer_Training  implements Serializable {

	private static final long serialVersionUID = -4999675871252402122L;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Training training;

	public PK_Customer_Training() {	}

	/**
	 * @param user
	 * @param training
	 */
	public PK_Customer_Training(User user, Training training) {
		this.user = user;
		this.training = training;
	}

}
