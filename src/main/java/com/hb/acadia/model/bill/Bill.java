package com.hb.acadia.model.bill;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
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

	@Override
	public String toString() {
		return "Bill [id=" + id + ", uuid=" + uuid + ", date=" + date + ", billAddress=" + billAddress + ", buyer="
				+ buyer + ", training=" + training + "]";
	}

}
