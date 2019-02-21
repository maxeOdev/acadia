package com.hb.acadia.model.bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserForBill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	@Column(unique = true)
	@NotNull
	protected String uuid;

	@NotNull
	protected String name;
	@NotNull
	protected String firstName;

	@Column(unique = true)
	protected String mail;
	@NotNull
	
	@OneToOne(fetch = FetchType.EAGER)
	protected Address address;
	
	public UserForBill() {}
	
	/**
	 * @param name
	 * @param firstName
	 * @param mail
	 * @param address
	 */
	public UserForBill(User user) {
		this.uuid = user.getUuid();
		this.name = user.getName();
		this.firstName = user.getFirstName();
		this.mail = user.getMail();
		this.address = user.getAddress();
	}
	
}
