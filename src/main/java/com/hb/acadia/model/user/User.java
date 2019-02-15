package com.hb.acadia.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Adress;

@Entity
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
	protected Adress adress;
	
	protected boolean isActif;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public boolean isActif() {
		return isActif;
	}

	public void setActif(boolean isActif) {
		this.isActif = isActif;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uuid=" + uuid + ", password=" + password + ", name=" + name + ", firstName="
				+ firstName + ", mail=" + mail + ", adress=" + adress + ", isActif=" + isActif + "]";
	}


}
