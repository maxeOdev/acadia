package com.hb.acadia.model.user;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.hb.acadia.model.Training;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
public class PK_Customer_Training  implements Serializable {

	private static final long serialVersionUID = -4999675871252402122L;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Training training;

	@Override
	public String toString() {
		return "PK_Customer_Training [user=" + user + ", training=" + training + "]";
	}

}
