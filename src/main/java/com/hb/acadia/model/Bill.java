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

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false, unique=true)
	private String uuid;
	
	@NotNull
	private long date;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	private Address billAddress;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	private User buyer;
		
	public Bill() {}

	public Bill(String uuid, @NotNull long date, @NotNull Address billAddress,
			User buyer) {
		this.uuid = uuid;
		this.date = date;
		this.billAddress = billAddress;
		this.buyer = buyer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Address getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(Address billAddress) {
		this.billAddress = billAddress;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", uuid=" + uuid + ", date=" + date
				+ ", billAddress=" + billAddress + ", buyer=" + buyer + "]";
	}
	
	
	
}
