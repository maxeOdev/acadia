package com.hb.acadia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.user.User;

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
	@ManyToOne(fetch=FetchType.EAGER)
	private Address billAddress;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	private User buyer;

	@Override
	public String toString() {
		return "Bill [id=" + id + ", uuid=" + uuid + ", date=" + date + ", billAddress=" + billAddress + ", buyer="
				+ buyer + "]";
	}

}
