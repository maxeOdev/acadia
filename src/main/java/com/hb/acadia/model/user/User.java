package com.hb.acadia.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Adress;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
	@OneToOne
	protected Adress adress;
	
	protected boolean isActif;

	

	@Override
	public String toString() {
		return "User [id=" + id + ", uuid=" + uuid + ", password=" + password + ", name=" + name + ", firstName="
				+ firstName + ", mail=" + mail + ", adress=" + adress + ", isActif=" + isActif + "]";
	}


}
