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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.Comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
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

	@Email
	@NotNull
	@Column(unique = true)
	protected String email;
	
	@OneToOne(fetch = FetchType.EAGER)
	protected Address address;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	protected Set<Comment> comments;
	
	@ManyToOne(fetch=FetchType.EAGER)
	protected Role role;

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
		this.email = mail;
		this.address = address;
		this.comments = comments;
		this.isActif = isActif;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isActif ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (isActif != other.isActif)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
