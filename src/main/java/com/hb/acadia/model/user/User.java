package com.hb.acadia.model.user;

import java.util.Set;

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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class User {

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

	@Override
	public String toString() {
		return "User [id=" + id + ", uuid=" + uuid + ", password=" + password + ", name=" + name + ", firstName="
				+ firstName + ", mail=" + mail + ", address=" + address + ", comments=" + comments + ", isActif="
				+ isActif + "]";
	}

			
}
