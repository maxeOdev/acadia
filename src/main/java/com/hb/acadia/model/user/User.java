package com.hb.acadia.model.user;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.Comment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	@Column(unique = true)
	@NotNull
	protected String uuid;

	@NotNull
	protected String password;

	@NotNull
	protected String name;
	@NotNull
	protected String firstName;

	@Column(unique = true)
	protected String mail;
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	protected Address address;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	protected Set<Comment> comments;

	protected boolean isActif;
	
	public User() {
		this.uuid = UUID.randomUUID().toString();
	}
	
	/**
	 * @param password
	 * @param name
	 * @param firstName
	 * @param mail
	 * @param address
	 * @param comments
	 * @param isActif
	 */
	public User(@NotNull String password, @NotNull String name, @NotNull String firstName, String mail,
			@NotNull Address address, Set<Comment> comments, boolean isActif) {
		this();
		this.password = password;
		this.name = name;
		this.firstName = firstName;
		this.mail = mail;
		this.address = address;
		this.comments = comments;
		this.isActif = isActif;
	}
	
}
