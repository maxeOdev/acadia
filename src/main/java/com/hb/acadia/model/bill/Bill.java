package com.hb.acadia.model.bill;

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

import com.hb.acadia.model.Address;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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

}
