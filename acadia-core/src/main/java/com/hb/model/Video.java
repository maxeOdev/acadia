package com.hb.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	private String uuid;
	
	//@NotNull(message = "La video doit avoir un titre.")
	@NotEmpty(message = "La video doit avoir un titre.")
	@Column(length=255)
	private String name;
	
	//@NotNull(message = "Le chemin d'accès à la vidéo doit être renseigné.")
	//@NotEmpty(message = "Le chemin d'accès à la vidéo doit être renseigné.")
	@Column(unique = true)
	private String path;
	
	//@NotNull(message = "Au moins une formation doit être associée.")
	//@NotEmpty(message = "Au moins une formation doit être associée.")
	@ManyToMany(mappedBy = "videos", fetch = FetchType.LAZY)
	private Set<Training> trainings;

	public Video() {
		this.uuid = UUID.randomUUID().toString();
	}

	public Video(@NotNull @NotEmpty String name, String path,
			Set<Training> trainings) {
		this();
		this.name = name;
		this.path = path;
		this.trainings = trainings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		Video other = (Video) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
