package com.hb.model.bill;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.hb.model.Address;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String uuid;

	@NotNull
	private long date;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Address billAddress;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserForBill buyer;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<TrainingForBill> training;

	public Bill() {
		this.uuid = UUID.randomUUID().toString();
	}

	/**
	 * @param date
	 * @param billAddress
	 * @param buyer
	 * @param training
	 */
	public Bill(@NotNull long date, @NotNull Address billAddress, UserForBill buyer, Set<TrainingForBill> training) {
		this();
		this.date = date;
		this.billAddress = billAddress;
		this.buyer = buyer;
		this.training = training;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		Bill other = (Bill) obj;
		if (date != other.date)
			return false;
		if (id != other.id)
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
